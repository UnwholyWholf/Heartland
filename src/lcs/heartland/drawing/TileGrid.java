package lcs.heartland.drawing;

import lcs.heartland.R;
import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class TileGrid extends RelativeLayout
{	
	private int num_cols;
	private int num_rows;
	
	private int curX;
	private int curY;
	
	private int tileSize;
	
	private TileButton[][] tileButtons;
	
	public TileGrid(int w, int h, Context a)
	{
		super(a);
		
		tileSize = getContext().getResources().getDimensionPixelSize(R.dimen.tileDimension);
		
		num_cols = w+1;
		num_rows = h+1;
		
		createGrid();
		
		//temporaryFunGenerator();
	}
	
	private void createGrid()
	{
		tileButtons = new TileButton[num_cols][num_rows];
		
		for (int x = 0; x < num_cols; x++)
		{
			for (int y = 0; y < num_rows; y++)
			{				
		        TileButton bt = new TileButton(getContext());

		        LayoutParams params = new RelativeLayout.LayoutParams(tileSize, tileSize);
		        params.leftMargin = (tileSize*x);
				params.topMargin  = (tileSize*y);
				params.height = tileSize;
				params.width = tileSize;
				
        		this.addView(bt, params);
			}
		}
	}
	
	private void temporaryFunGenerator()
	{
		int variancePercentage = 25;	//	Chance that the next tile will differ from it's majority surrounding.
		int neighborsAveraged = 1;		//	Radial distance from tile that is checked for majority.
		
		int x = (int)(Math.random()*num_cols);	//	Random starting position is
		int y = (int)(Math.random()*num_rows);	//	calculated.
		
		int tilesLeft = num_rows*num_cols;
		
		while(tilesLeft > 0)
		{
			//if(tileButtons[x][y])
		}
	}
}
