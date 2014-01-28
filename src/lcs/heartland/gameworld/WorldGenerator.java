package lcs.heartland.gameworld;

public class WorldGenerator 
{
	//The types of worlds
	public enum Type{ISLAND,OCEAN,FOREST,TOWN;} //etc
	
	//Assigns the tiles of the world to a randomized set of a given type
	public static void generateWorld(World world, Type type)	
	{
		switch(type)
		{
			case ISLAND:
				generateIsland(world);
				break;
			case OCEAN:
				//generateOcean(world);
				break;
			case FOREST:
				//generateForest(world);
				break;
			case TOWN:
				//generateTown(world);
				break;
			default:
				//generateNothing(world);
		}
		
	}

	//Randomly create the world
	private static void generateIsland(World w)
	{
		//	Generates random world
		int bearWeight 		= 1;
		int rockWeight 		= 1;
		int flowerWeight 	= 1;
		int treeWeight 		= 15;
		int emptyWeight		= 20;
		
		
		/////////////////////////////////////////////////////////
		int total = emptyWeight + treeWeight + rockWeight + flowerWeight + bearWeight;		//	10
	
		int bear = bearWeight;	//	1
		int rock = rockWeight + bear; //	3
		int flower = flowerWeight + rock;	//	6
		int tree = treeWeight + flower;	//	10
		
		int rand = 0;
		Tile.Foreground foreground;
		
		for(int x = 0; x < w.WORLD_WIDTH; x++)
			for(int y = 0; y < w.WORLD_HEIGHT; y++)
			{
				rand = (int)(Math.random()*total);
				
				if(rand < bear) 		foreground = Tile.Foreground.BEAR;
				else if(rand < rock)	foreground = Tile.Foreground.ROCK;
				else if(rand < flower)	foreground = Tile.Foreground.FLOWER_RED;
				else if(rand < tree)	foreground = Tile.Foreground.TREE;
				else 					foreground = Tile.Foreground.EMPTY;
				
				//w.tiles[x][y] = new Tile(foreground);
			}
	}
}
