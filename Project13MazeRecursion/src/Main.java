import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves any maze in the "Maze.txt" file, given that the starting
 * point is (0, 1), and prints out whether the maze is solvable, and if so the
 * solution.
 * @author johnbalson
 * @version 4/8/2021
 */
public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new File("Maze.txt"));
		String firstLine = inFile.nextLine();
		int width = firstLine.length();
		int height = 1;
		
		while(inFile.hasNextLine())
		{
			height ++;
			inFile.nextLine();
		}
		char[][] maze = new char[height][width];

		inFile = new Scanner(new File("Maze.txt"));
		for(int i = 0; i < height; i ++)
		{
			String inLine = inFile.nextLine();
			maze[i] = inLine.toCharArray();
		}
		
		MazeSolver m = new MazeSolver(maze);
		if(m.solveMaze(maze, 0, 1))
		{
			System.out.println("true");
			for(int i = 0; i < height; i++)
			{
				System.out.println(m.getSolution()[i]);
			}
		}
		else
		{
			System.out.println("false");
		}
		
	}
}
