package lcs.heartland.gameworld;

public class World 
{
	private static final int WORLD_WIDTH = 50;
	private static final int WORLD_HEIGHT = 50;
	
	private Tile[][] tiles;
	
	public World()
	{
		initializeTiles();
		//	generateWorld();
	}
	
	private void initializeTiles()
	{
		for(int x = 0; x < WORLD_WIDTH; x++)
			for(int y = 0; y <WORLD_HEIGHT; y++)
				tiles[x][y] = new Tile();
	}
	
	private void generateWorld()
	{
		//	Generates random world
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
		
		for(int x = 0; x < WORLD_WIDTH; x++)
			for(int y = 0; y < WORLD_HEIGHT; y++)
			{
				rand = (int)(Math.random()*total);
				
				if(rand < bear) 		foreground = Tile.Foreground.BEAR;
				else if(rand < rock)	foreground = Tile.Foreground.ROCK;
				else if(rand < flower)	foreground = Tile.Foreground.FLOWER_RED;
				else if(rand < tree)	foreground = Tile.Foreground.TREE;
				else 					foreground = Tile.Foreground.EMPTY;
				
				tiles[x][y] = new Tile(foreground);
			}
	}
	
	public void save()
	{
		
	}
}
