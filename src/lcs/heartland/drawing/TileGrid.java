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
	private static final int BUFFER_TILE_SIZE = 2;
	
	private int num_cols;
	private int num_rows;
	
	private int originX;
	private int originY;
	
	private int tileSize;
	
	private World world;
	
	private TileButton[][] tileButtons;
	
	public TileGrid(int w, int h, Context a, OnClickListener listener, World world)
	{
		super(a);
		
		tileSize = getContext().getResources().getDimensionPixelSize(R.dimen.tileDimension);
		
		num_cols = w+(2*BUFFER_TILE_SIZE);
		num_rows = h+(2*BUFFER_TILE_SIZE);
		this.world = world;
		
		TileButton.generateResources();
		
		createGrid(listener);
		
		//temporaryFunGenerator();
		
		updateTileImages();
	}
	
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
	
	public void move(int dir)
	{
		if (dir == 0)
			originY=(originY-1+num_rows)%num_rows;
		if (dir == 1)
			originX=(originX+1)%num_cols;
		if (dir == 2)
			originY=(originY+1)%num_rows;
		if (dir == 3)
			originX=(originX-1+num_cols)%num_cols;
		
		//TODO: Get outer edge/buffer tiles to update image from World 
		
		updateTileLocations();
	}
	
	public void updateTileImages()
	{
		for (int x = 0; x < num_cols; x++)
			for (int y = 0; y < num_rows; y++)
				tileButtons[x][y].updateImage();
	}
	
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
	
	public int getVisibleWidth()
	{
		return num_cols-(2*BUFFER_TILE_SIZE);
	}
	public int getVisibleHeight()
	{
		return num_rows-(2*BUFFER_TILE_SIZE);
	}
	
	public int[] getButtonVisibleLoc(TileButton tb)
	{
		return convertGridXYtoVisibleXY(tb.getGridXY());
	}
	private int[] convertGridXYtoVisibleXY(int[] Gloc)
	{
		int[] Vloc = new int[2];
		
		Vloc[0] = ((Gloc[0]-originX+num_cols)%num_cols)+BUFFER_TILE_SIZE;
		Vloc[1] = ((Gloc[1]-originY+num_rows)%num_rows)+BUFFER_TILE_SIZE;
		
		return Vloc;
	}

}
