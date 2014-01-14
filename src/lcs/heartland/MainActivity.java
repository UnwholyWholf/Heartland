package lcs.heartland;

import lcs.heartland.drawing.TileButton;
import lcs.heartland.drawing.TileGrid;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

//	Ryan Haskell

public class MainActivity extends Activity 
{
	public static final int VISIBLE_TILE_WIDTH = 20;
	public static final int VISIBLE_TILE_HEIGHT = 11;
	
	private TileGrid grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		//Request Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		makeButtonArray();	
	}
	
	public void makeButtonArray()
	{
		int tileSize = getResources().getDimensionPixelSize(R.dimen.tileDimension);
		grid = new TileGrid(VISIBLE_TILE_WIDTH, VISIBLE_TILE_HEIGHT, this);
		LayoutParams params = new RelativeLayout.LayoutParams(tileSize*VISIBLE_TILE_WIDTH, tileSize*VISIBLE_TILE_HEIGHT);
		this.addContentView(grid, params);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
