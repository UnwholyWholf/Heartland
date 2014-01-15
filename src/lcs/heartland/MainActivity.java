package lcs.heartland;

import lcs.heartland.drawing.TileButton;
import lcs.heartland.drawing.TileGrid;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

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
		
		initTileGrid();	
	}
	
	public void initTileGrid()
	{
		int tileSize = getResources().getDimensionPixelSize(R.dimen.tileDimension);
		grid = new TileGrid(VISIBLE_TILE_WIDTH, VISIBLE_TILE_HEIGHT, this,
							new tileButtonListener());
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

	public class tileButtonListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) 
		{
			if (!(arg0 instanceof TileButton))
				return;
			
			TileButton tb = (TileButton) arg0;
			int[] loc = grid.getButtonVisibleLoc(tb);
			
			int[] Gloc = tb.getGridXY();
			
			System.out.println("Tapped Grid ("+Gloc[0]+","+Gloc[1]+")");
			System.out.println("Tapped Vis  ("+loc[0]+","+loc[1]+")");
			
			if (loc[0] == 0)
				grid.move(3); //Left
			if (loc[1] == 0)
				grid.move(0); //Up
			if (loc[0] == grid.getVisibleWidth()-1)
				grid.move(1); //Right
			if (loc[1] == grid.getVisibleHeight()-1)
				grid.move(2); //Down
		}
		
	}
}
