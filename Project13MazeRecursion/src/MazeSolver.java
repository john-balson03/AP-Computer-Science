import java.util.ArrayList;

/**
 * This class provides methods to solve rectangular mazes (2-dimensional char
 * arrays with walls made of '*' characters paths made of spaces, and an exit 
 * labeled 'E'), and access their solutions.
 * @author johnbalson
 * @version 4/8/2021
 */
public class MazeSolver
{
	private char[][] solvedMaze;
	private ArrayList<Integer> moves;
	
	/**
	 * Creates an object of type MazeSolver, given the maze to be solved.
	 * @param maze the maze to be solved by this MazeSolver
	 */
	public MazeSolver(char[][] maze)
	{
		solvedMaze = new char[maze.length][maze[1].length];
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze[i].length; j++)
			{
				solvedMaze[i][j] = maze[i][j];
			}
		}
		moves = new ArrayList<>();
	}
	
	/**
	 * This method solves a rectangular maze of any size, given an
	 * array and the coordinate of the starting place of the maze.
	 * @param maze the maze to be solved
	 * @param down the number of rows down of the starting place
	 * @param across the number of rows across of the staring place
	 * @return whether or not the maze is solvable
	 */
	public boolean solveMaze (char[][] maze, int down, int across)
	{	
		//if we are at the end of the maze
		if(maze[down][across] == 'E')
		{
			solvedMaze[down][across] = 'X';
			return true;
		}
		
		maze[down][across] = 'X';
		solvedMaze[down][across] = 'X';
		
		//if the position below is clear
		if(down < maze.length - 1 && (maze[down + 1][across] == ' ' || maze[down + 1][across] == 'E'))
		{
			moves.add(1);
			return solveMaze(maze, down + 1, across);
		}
		//if the position to the right is clear
		if(across < maze[1].length - 1 && (maze[down][across + 1] == ' ' || maze[down][across + 1] == 'E'))
		{
			moves.add(2);
			return solveMaze(maze, down, across + 1);
		}
		//if the position above is clear
		if(down > 0 && (maze[down - 1][across] == ' ' || maze[down - 1][across] == 'E'))
		{
			moves.add(3);
			return solveMaze(maze, down - 1, across);
		}		
		//if the position to the left is clear
		if(across > 0 && (maze[down][across - 1] == ' ' || maze[down][across - 1] == 'E'))
		{
			moves.add(4);
			return solveMaze(maze, down, across - 1);
		}
		
		solvedMaze[down][across] = ' ';
		
		//if the last move was down
		if(moves.size() > 0 && moves.get(moves.size() - 1) == 1)
		{
			moves.remove(moves.size() - 1);
			return solveMaze(maze, down - 1, across);
		}
		//if the last move was to the right
		if(moves.size() > 0 && moves.get(moves.size() - 1) == 2)
		{
			moves.remove(moves.size() - 1);
			return solveMaze(maze, down, across -1);
		}
		//if the last move was up
		if(moves.size() > 0 && moves.get(moves.size() - 1) == 3)
		{
			moves.remove(moves.size() - 1);
			return solveMaze(maze, down + 1, across);
		}		
		//if the last moves was to the left
		if(moves.size() > 0 && moves.get(moves.size() - 1) == 4)
		{
			moves.remove(moves.size() - 1);
			return solveMaze(maze, down, across + 1);
		}
		return false;
		
		
		
		
	}
	
	/**
	 * This method provides access to the solution of the maze, given that the
	 * solveMaze method has been run and the maze is solvable.
	 * @return an array with the solution to the maze
	 */
	public char[][] getSolution()
	{
		return solvedMaze;
	}
	
}
