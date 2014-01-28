package lcs.heartland.drawing;

import lcs.heartland.R;
import lcs.heartland.gameworld.Tile;
import lcs.heartland.gameworld.World;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class TileGrid extends RelativeLayout
{
	//####################### Constants ##############################################
	
	//The amount of additional buttons on each edge of the screen
	//This is added to all four sides of the array
	private static final int BUFFER_TILE_SIZE = 2;
	
	//A direction enum used for moving the tilebutton array
	public enum Dir{NORTH, SOUTH, EAST, WEST;}
	
	//####################### Fields ##############################################
	
	//Size of the grid including edge buffers
	private int num_cols;
	private int num_rows;
	
	//Origin of visible TileButtons in memory
	private int[] origin;
	
	//The visible size of the tiles in dp
	private int tileSize;
	
	//The world object currently associated with the TileGrid
	//This is used to determine the drawings on the buttons
	private World world;
	
	//The 2D array of TileButtons that are displayed on the screen
	private TileButton[][] tileButtons;
	
	//####################### Constructors ##############################################
	
	public TileGrid(int w, int h, Context a, OnClickListener listener, World world)
	{
		super(a);
		
		tileSize = getContext().getResources().getDimensionPixelSize(R.dimen.tileDimension);
		
		num_cols = w+(2*BUFFER_TILE_SIZE);
		num_rows = h+(2*BUFFER_TILE_SIZE);
		this.world = world;
		
		origin = new int[]{0,0};
		
		TileButton.generateResources();
		
		createGrid(listener);
		
		//temporaryFunGenerator();
		
		updateTileImages();
	}
	
	//####################### Initializers ##############################################
	
	//Initialize the TileButton array with default buttons
	private void createGrid(OnClickListener listener)
	{
		tileButtons = new TileButton[num_cols][num_rows];
		
		for (int x = 0; x < num_cols; x++)
		{
			for (int y = 0; y < num_rows; y++)
			{				
		        TileButton bt = new TileButton(getContext(), new Tile(Tile.Foreground.EMPTY, Tile.Background.GRASS), x, y);

		        LayoutParams params = new RelativeLayout.LayoutParams(tileSize, tileSize);
		        params.leftMargin = (tileSize*x);
				params.topMargin  = (tileSize*y);
				params.height = tileSize;
				params.width = tileSize;
				
				bt.setOnClickListener(listener);
				
        		this.addView(bt, params);
        		
          		tileButtons[x][y] = bt;
			}
		}
	}
	
	//####################### Updating/Moving ##############################################
	
	//Move the virtual origin to pan the screen in the specified direction
	//Updates the buffered tiles to their correct images
	public void move(Dir dir)
	{
		if (dir == Dir.NORTH)
			origin[1]=(origin[1]-1+num_rows)%num_rows;
		if (dir == Dir.EAST)
			origin[0]=(origin[0]+1)%num_cols;
		if (dir == Dir.SOUTH)
			origin[1]=(origin[1]+1)%num_rows;
		if (dir == Dir.WEST)
			origin[0]=(origin[0]-1+num_cols)%num_cols;
		
		//TODO: Get outer edge/buffer tiles to update image from World 
		
		updateTileLocations();
	}
	
	//Updates all TileButtons to reflect the correct background/Foreground
	//This should only be called when the world changes or the character
	// moves in a non-step fashion
	public void updateTileImages()
	{
		for (int x = 0; x < num_cols; x++)
			for (int y = 0; y < num_rows; y++)
				tileButtons[x][y].updateImage();
	}
	
	//Updates the tiles in memory to be in their correct physical location
	//This should be called after the screen has panned so the buffer tiles
	// cycle correctly
	public void updateTileLocations()
	{
		for (int x = 0; x < num_cols; x++)
		{
			for (int y = 0; y < num_rows; y++)
			{
				int[] loc = getButtonVisibleLoc(tileButtons[x][y]);
				
				if (y == 0 && x == 0)
					System.out.println("VLoc: "+loc[0]+","+loc[1]);
				
				LayoutParams params = new RelativeLayout.LayoutParams(tileSize, tileSize);
		        params.leftMargin = (tileSize*loc[0]);
				params.topMargin  = (tileSize*loc[1]);
				params.height = tileSize;
				params.width = tileSize;
				
				tileButtons[x][y].setLayoutParams(params);
			}
		}
	}
	
	//####################### Set/Gets ##############################################
	
	//Returns the amount of tiles displayed on the phone screen
	public int getVisibleWidth()
	{
		return num_cols-(2*BUFFER_TILE_SIZE);
	}
	public int getVisibleHeight()
	{
		return num_rows-(2*BUFFER_TILE_SIZE);
	}
	
	//####################### Virtual to Physical XY ##############################################
	
	//Returns the x and y of the provided button as on the screen
	public int[] getButtonVisibleLoc(TileButton tb)
	{
		return convertGridXYtoVisibleXY(tb.getGridXY());
	}
	
	//Converts the "Grid Location" into a "Visible Location"
	private int[] convertGridXYtoVisibleXY(int[] Gloc)
	{
		int[] Vloc = new int[2];
		
		Vloc[0] = ((Gloc[0]-origin[0]+num_cols)%num_cols)+BUFFER_TILE_SIZE;
		Vloc[1] = ((Gloc[1]-origin[1]+num_rows)%num_rows)+BUFFER_TILE_SIZE;
		
		return Vloc;
	}

}
