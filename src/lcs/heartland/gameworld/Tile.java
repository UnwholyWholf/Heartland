package lcs.heartland.gameworld;

public class Tile 
{
	//	PUBLIC TILE VARIABLES
	public enum Foreground
	{	EMPTY,TREE,ROCK,FLOWER_RED,FLOWER_YELLOW;	}
	
	public enum Background
	{	GRASS,SAND,WATER;	}
	
	Foreground foreground;
	Background background;
	
	public Tile()
	{
		foreground = Foreground.EMPTY;
		background = Background.GRASS;
	}
	
	public Tile(Foreground f, Background b)
	{
		foreground = f;
		background = b;
	}
	
	public Tile(Foreground f)
	{
		foreground = f;
		background = Background.GRASS;
	}
	
	public Tile(Background b)
	{
		foreground = Foreground.EMPTY;
		background = b;
	}
	
	public Foreground getForeground()
	{
		return foreground;
	}
	public Background getBackground()
	{
		return background;
	}
}
