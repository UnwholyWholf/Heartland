package lcs.heartland.gameworld;

public class World 
{
	//############################### Constants ########################################
	
	//The Width of the World
	public static final int WORLD_WIDTH = 50;
	
	//The Height of the world
	public static final int WORLD_HEIGHT = 50;
	
	//############################### Fields ########################################
	
	//The array of tiles that make up the World
	//Is of size WORLD_WIDTH x WORLD_HEIGHT
	private Tile[][] tiles;
	
	//############################### Constructors ########################################
	public World()
	{
		initializeTiles();
		//	generateWorld();
	}
	
	//############################### Initializers ########################################
	
	//Create the tiles to fill the tile array
	private void initializeTiles()
	{
		for(int x = 0; x < WORLD_WIDTH; x++)
			for(int y = 0; y <WORLD_HEIGHT; y++)
				tiles[x][y] = new Tile();
	}
	
	//############################### Basic Get/Sets ########################################
	public Tile getTileAt(int[] loc)
	{
		if (loc[0] < 0 || loc[0] > WORLD_WIDTH)
			return null;
		if (loc[1] < 0 || loc[1] > WORLD_HEIGHT)
			return null;
		return tiles[loc[0]][loc[1]];
	}
	
	public Tile getTileAt(int[] loc, int[] origin)
	{
		int[] offsetLocation = new int[]{
				loc[0]+origin[0],
				loc[1]+origin[1]
		};
		return getTileAt(offsetLocation);
	}
	
	
	
	//############################### Misc ########################################
	public void save()
	{
		
	}
}
