package lcs.heartland.drawing;

import lcs.heartland.R;
import lcs.heartland.gameworld.Tile;
import android.content.Context;
import android.widget.ImageButton;

public class TileButton extends ImageButton 
{
	private Tile myTile;
	
	private static int[] foregroundResources = null;
	private static int[] backgroundResources = null;
	
	public TileButton(Context context) 
	{
		super(context);
		
		if (foregroundResources == null)
			setForegroundResources();
		if (backgroundResources == null)
			setBackgroundResources();
		
		initTileButton();
	}
	
	private void initTileButton()
	{
		this.setBackgroundColor(getResources().getColor(R.color.grass_green));
		this.setScaleType(ImageButton.ScaleType.FIT_CENTER);
		this.setPadding(0, 0, 0, 0);
	}
	
	private void setForegroundResources()
	{
		foregroundResources = new int[]
			{-1, 
			 R.drawable.tree, 
			 R.drawable.rock,
			 R.drawable.red_flower,
			 R.drawable.yellow_flower};
	}
	private void setBackgroundResources()
	{
		backgroundResources = new int[]
			{R.color.grass_green, 
			 R.color.sand_tan,
			 R.color.water_blue};
	}
	
	public void updateImage()
	{
		this.setBackgroundColor(getContext().getResources().getColor(backgroundResources[myTile.getBackground().ordinal()]));
		this.setImageResource(foregroundResources[myTile.getForeground().ordinal()]);
	}

}
