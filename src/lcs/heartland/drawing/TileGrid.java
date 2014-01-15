package lcs.heartland.drawing;

import lcs.heartland.R;
import lcs.heartland.gameworld.Tile;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class TileGrid extends RelativeLayout
{
	private static final int BUFFER_TILE_SIZE = 5;
	
	private int num_cols;
	private int num_rows;
	
	private int originX;
	private int originY;
	
	private int tileSize;
	
	private TileButton[][] tileButtons;
	
	public TileGrid(int w, int h, Context a, OnClickListener listener)
	{
		super(a);
		
		tileSize = getContext().getResources().getDimensionPixelSize(R.dimen.tileDimension);
		
		num_cols = w+BUFFER_TILE_SIZE;
		num_rows = h+BUFFER_TILE_SIZE;
		
		TileButton.generateResources();
		
		createGrid(listener);
		
		temporaryFunGenerator();
		
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
		return num_cols-BUFFER_TILE_SIZE;
	}
	public int getVisibleHeight()
	{
		return num_rows-BUFFER_TILE_SIZE;
	}
	
	public int[] getButtonVisibleLoc(TileButton tb)
	{
		return convertGridXYtoVisibleXY(tb.getGridXY());
	}
	private int[] convertGridXYtoVisibleXY(int[] Gloc)
	{
		int[] Vloc = new int[2];
		
		Vloc[0] = (Gloc[0]-originX+num_cols)%num_cols;
		Vloc[1] = (Gloc[1]-originY+num_rows)%num_rows;
		
		return Vloc;
	}
	
	private void temporaryFunGenerator()
	{
		int bearWeight 		= 1;
		int rockWeight 		= 1;
		int flowerWeight 	= 1;
		int treeWeight 		= 15;
		int emptyWeight		= 20;
		
		
		/////////////////////////////////////////////////////////
		int total = emptyWeight + treeWeight + rockWeight + flowerWeight + bearWeight;		//	10
	
		int bear = bearWeight;													//	1
		int rock = rockWeight + bear;										//	3
		int flower = flowerWeight + rock;					//	6
		int tree = treeWeight + flower;			//	10
		
		int rand = 0;
		Tile.Foreground foreground;
		
		for(int x = 0; x < num_cols; x++)
			for(int y = 0; y < num_rows; y++)
			{
				rand = (int)(Math.random()*total);
				
				if(rand < bear) 		foreground = Tile.Foreground.BEAR;
				else if(rand < rock)	foreground = Tile.Foreground.ROCK;
				else if(rand < flower)	foreground = Tile.Foreground.FLOWER_RED;
				else if(rand < tree)	foreground = Tile.Foreground.TREE;
				else 					foreground = Tile.Foreground.EMPTY;
				
				tileButtons[x][y].setTile(new Tile(foreground));
			}
	}
}
