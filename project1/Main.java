//Kippum Joseph Chang kjc190001
package project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;



	


public class Main {
	
	public static Creature[][] deepCopy(Creature[][] original) {		// creating a copy of a grid
	    if (original == null) {
	        return null;
	    }

	    final Creature[][] result = new Creature[original.length][];
	    for (int i = 0; i < original.length; i++) {
	        result[i] = Arrays.copyOf(original[i], original[i].length);
	        
	    }
	    return result;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String inputfilename;
		char characterant;
		char characterbeetle;
		String line;
		char[] c1;
		Creature[][] grid = new Creature[10][10];
		int numberofturns;
		//inputfilename = scan.nextLine();
		characterant=scan.next().charAt(0);
		characterbeetle=scan.next().charAt(0);
		numberofturns = scan.nextInt();
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\kippumchang\\Desktop\\computer science1\\Project1\\project1\\sample.txt"));	//scanning inputfile
			try {
				int height=0;
				while ((line=br.readLine())!=null){			// reading line by line
					c1=line.toCharArray();
					for(int length=0;length<10;length++) {		
						if(c1[length]==' ') {					// if the space is empty  
							grid[length][height]=null;			// assign null value
						}
						else if(c1[length]=='B') {			// if the space is B create new beetle
							Creature beetle = new Beetle(length,height);		
							grid[length][height]= beetle;
						}
						else if(c1[length]=='a') {			// if the space is a create new Ant
							Creature ant = new Ant(length,height);
							grid[length][height]=ant;
						}
						
						
					}
					height ++;							
					}
				
				
		}finally {
		try {br.close();
		}catch (FileNotFoundException e){					//catching an error if the file has not been founded
			e.printStackTrace();
		}
		}
			}catch (IOException e){							
				e.printStackTrace();
				}
		for(int loop=1;loop<=numberofturns;loop++) {		// running the program for number of turns that the user entered
			
			
			
			Creature[][] grid_copy = deepCopy(grid);		//making a copy of original grid, because the movement should be based on the original grid not the current grid.
			for(int x=0;x<10;x++) {							// iterating though whole grid
			for(int y=0;y<10;y++) {
				if(grid_copy[x][y]instanceof Beetle) {		// if the space from the copied grid is Beetle run below
					Beetle bee = (Beetle) grid[x][y];		
					bee.Move(grid);							//execute move function of beetle class
					bee.beetlelife++;						//adding 1 to beetle life at the end of every turn
				}
			}
			}
			grid_copy = deepCopy(grid);
			
			for(int x=0;x<10;x++) {								// iterating through grid to find ant
			for(int y=0;y<10;y++) {
						if(grid_copy[x][y]instanceof Ant) {		// if the space is ant run below
							Ant anty = (Ant) grid[x][y];		
							anty.Move(grid);					// executing move function from the ant subclass
							anty.antlife++;						// adding one to antlife after move function

						}
					}
					}
			for(int x=0;x<10;x++) {
			for(int y=0;y<10;y++) {
						if(grid[x][y] instanceof Beetle) {		//iterating through grid to find beetle
							Beetle bee = (Beetle) grid[x][y];
							bee.Starve(grid);					//running starve function
						}
					}
					}
			for(int x=0;x<10;x++) {
			for(int y=0;y<10;y++) {								//iterating through grid to find ant
						if(grid[x][y]instanceof Ant) {
							Ant anty = (Ant) grid[x][y];		
							anty.Breed(grid);					//executing breed function
						}
					}
					}
			
			for(int x=0;x<10;x++) {								//iterating through grid to find beetle
			for(int y=0;y<10;y++) {
						if(grid[x][y]instanceof Beetle) {		
							Beetle bee = (Beetle) grid[x][y];
							bee.Breed(grid);					// if beetle execute breed function
						}
					}
					}
			StringBuffer output = new StringBuffer();			//using string buffer named output to append lines
			System.out.println("TURN"+" "+loop);				
			for(int y=0;y<10;y++) {								//scanning grid
			for(int x=0;x<10;x++) {
					if(grid[x][y]instanceof Beetle) {			//if beetle, append the character the user inputed to output
						output.append(characterbeetle);
					}
					else if(grid[x][y]instanceof Ant) {			//if Ant, append the character the user inputed for ant to output
						output.append(characterant);
					}	
					else if(grid[x][y]==null) {					//if the grid equals null, append blank space to the output
						output.append(" ");
					}
				}
				output.append("\n");							
			}	
			System.out.println(output);							// displaying everything that was appended to the output
			}

		
		
	}
		
		
		}
	