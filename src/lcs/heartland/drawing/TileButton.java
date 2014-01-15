package lcs.heartland.drawing;

import lcs.heartland.R;
import lcs.heartland.gameworld.Tile;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageButton;

@SuppressLint("ViewConstructor")
public class TileButton extends ImageButton 
{
	private static int[] foregroundResources = null;
	private static int[] backgroundResources = null;
	private static final int EMPTY_FOREGROUND = -1;
	
	private Tile myTile;
	private int x;
	private int y;
	
	public TileButton(Context context, Tile t, int x, int y) 
	{
		super(context);
		
		myTile = t;
		this.x = x;
		this.y = y;
		
		initTileButton();
	}
	
	public static void generateResources()
	{
		if (foregroundResources == null)
			setForegroundResources();
		if (backgroundResources == null)
			setBackgroundResources();
	}
	
	private void initTileButton()
	{
		this.setBackgroundColor(getResources().getColor(R.color.grass_green));
		this.setScaleType(ImageButton.ScaleType.FIT_CENTER);
		this.setPadding(0, 0, 0, 0);
	}
	
	private static void setForegroundResources()
	{
		foregroundResources = new int[]
			{EMPTY_FOREGROUND, 
			 R.drawable.tree, 
			 R.drawable.rock,
			 R.drawable.red_flower,
			 R.drawable.yellow_flower,
			 R.drawable.bear};
	}
	private static void setBackgroundResources()
	{
		backgroundResources = new int[]
			{R.color.grass_green, 
			 R.color.sand_tan,
			 R.color.water_blue};
	}
	
	public void updateImage()
	{
		this.setBackgroundColor(getContext().getResources().getColor(backgroundResources[myTile.getBackground().ordinal()]));
		int foreResource = foregroundResources[myTile.getForeground().ordinal()];
		if (foreResource != EMPTY_FOREGROUND)
			this.setImageResource(foreResource);
	}

	public void setTile(Tile t)
	{
		myTile = t;
	}
	public Tile getTile()
	{
		return myTile;
	}

	public void setGridXY(int[] loc)
	{
		x = loc[0];
		y = loc[1];
	}
	public void setGridXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void setGridX(int x)
	{
		this.x = x;
	}
	public void setGridY(int y)
	{
		this.y = y;
	}
	public int getGridX()
	{
		return x;
	}
	public int getGridY()
	{
		return y;
	}
	public int[] getGridXY()
	{
		int[] loc = new int[2];
		loc[0] = x;
		loc[1] = y;
		return loc;
	}
}
