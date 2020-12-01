//Kippum Joseph Chang kjc190001
package project1;

public class Beetle extends Creature {
	public Beetle(int positionx, int positiony) {
		super(positionx, positiony);	//using the constructor from the super class to assign x and y position to each of the beetles
	}


	int beetlelife = 0;
	int starve = 0;
	int eat = 0;
	
	@Override
	public void Move(Creature[][] grid) {
		int NA = 10, EA = 10, SA = 10, WA = 10;				//assigning int 10 to each variables that represent each location and distance of ants
		int nd = 10;
		int ndnumba = 0;
		int ndneighnum = 10;
		for (int x = 0; x < 10; x++) {							//finding distance of nearest ant in orthogonal direction
			for (int y = 0; y < 10; y++) {
				if (grid[x][y] instanceof Ant) {
					if ((grid[x][y].positionx != this.positionx) && (grid[x][y].positiony != this.positiony)) {		// if none of the x nor y position matches, value of distances stays the same 

					} else if (grid[x][y].positionx == this.positionx) {	//if there is a ant with same x position
						int dy = grid[x][y].positiony - this.positiony;		//find the difference in y value to find the distance between
						if (dy > 0) {										//if difference is >0, means south ant 
							if (SA > dy) {				//loop through array and if the value is smaller assign it to SA to find closest ant
								SA = dy;
							}
						} else if (dy < 0) {			// if difference is under 0
							if (NA > Math.abs(dy)) {			// finding lowest value, and abs for dy since its negative
								NA = Math.abs(dy);				
							}
						}
					} else if (grid[x][y].positiony == this.positiony) {			// if y value of beetle and ant matches
						int dx = grid[x][y].positionx - this.positionx;				// find difference in x value of beetle and ant
						if (dx > 0) {				// if difference >0, means east ant
							if (EA > (dx)) {		//assign lowest value to EA
								EA = dx;
							}
						} else if (dx < 0) {		// if difference<0, means west ant
							if (WA > Math.abs(dx)) {	// assign lowest value to WA
								WA = Math.abs(dx);
							}
						}
					}


				}
			}
		}
		int distances[] = {NA, EA, SA, WA};			// array with the distance of the closest ant of all 4 direction, if there was no ant in orthogonal direction,then the value=10
		int ND[] = new int[4];					
		for (int e = 0; e < 4; e++) { // loop through distance to find lowest value
			if (nd > distances[e] && distances[e] != 10) { // if the value of distance is lower than nd, and the value is not 10 assign it to nd
				nd = distances[e];
			} else if (nd == distances[e] && distances[e] != 10) {// if the value is same as lowest value, +1 to ndnumba
				ndnumba++;
			}
		}

		if (nd == 10) {                    //when there is no ant in orthogonal direction of beetle 
			int Nedge = this.positiony;
			int Eedge = 10 - this.positionx;
			int Sedge = 10 - this.positiony;
			int Wedge = this.positionx;
			int edges[] = {Nedge, Eedge, Sedge, Wedge};
			int furthestedge = 0;
			for (int v = 0; v < 4; v++) {                            //finding the farthest edge from the beetle 
				if (furthestedge < edges[v]) {
					furthestedge = edges[v];
				}
			}
			if (furthestedge == Nedge && !(grid[this.positionx][this.positiony - 1] instanceof Beetle)) {
					grid[this.positionx][this.positiony - 1] = this;  //moving up 1
					grid[this.positionx][this.positiony] = null;		// erasing the grid that 
					this.positiony = this.positiony - 1;
					return;// assigning new y value to position y
				}

			if (furthestedge == Eedge && !(grid[this.positionx + 1][this.positiony] instanceof Beetle)) {                        //if farthest edge is east edge move beetle right side 1
					grid[this.positionx + 1][this.positiony] = this;
					grid[this.positionx][this.positiony] = null;
					this.positionx = this.positionx + 1;
					return;
				}

			 if (furthestedge == Sedge && !(grid[this.positionx][this.positiony + 1] instanceof Beetle)) {                        //if farthest edge is south edge move beetle down side 1
					grid[this.positionx][this.positiony + 1] = this;
					grid[this.positionx][this.positiony] = null;
					this.positiony = this.positiony + 1;
					return;
				}
			 
			 if (furthestedge == Wedge && !(grid[this.positionx - 1][this.positiony] instanceof Beetle)) {                        //if farthest edge is west edge move beetle left side 1
					grid[this.positionx - 1][this.positiony] = this;
					grid[this.positionx][this.positiony] = null;
					this.positionx = this.positionx - 1;
					return;
				}

		} else if (nd != 10) {                //if there's at least one ant in orthogonal direction of Ant, run below
			if (ndnumba == 0) {                                                        //if there is only one ant in orthogonal direction run below
				if (nd == distances[0]) {                                                //if the nearest ant is on the north run below
					if (this.positiony == 0) {
					}                                            //The beetles on the first row of the grid cannot move up
					else if (grid[this.positionx][this.positiony - 1] instanceof Beetle) {
					}    //if the grid is already occupied by beetle do nothing
					else {                                            //if the grid moving to is empty and the beetle is not on the first row of the grid, run below
						if (grid[this.positionx][this.positiony - 1] instanceof Ant) {
							starve = 0;
						}        // if the space that beetle is moving to is occupied by ant the turns that beetle starved resets to 0
						grid[this.positionx][this.positiony] = null;        //After moving, assign null to the grid that the beetle was
						grid[this.positionx][this.positiony - 1] = this;        //moving grid up 1
						this.positiony = this.positiony - 1;                    //After moving reassign the new y-position value to y=position variable

					}
				} else if (nd == distances[1]) {
					if (this.positionx == 9) {			
					} else if (grid[this.positionx + 1][this.positiony] instanceof Beetle) {
					} else {
						if (grid[this.positionx + 1][this.positiony] instanceof Ant) {
							starve = 0;
						}
						grid[this.positionx][this.positiony] = null;
						grid[this.positionx + 1][this.positiony] = this;
						this.positionx = this.positionx + 1;

					}
				} else if (nd == distances[2]) {
					if (this.positiony == 9) {
					} else if (grid[this.positionx][this.positiony + 1] instanceof Beetle) {
					} else {
						if (grid[this.positionx][this.positiony + 1] instanceof Ant) {
							starve = 0;
						}
						grid[this.positionx][this.positiony] = null;
						grid[this.positionx][this.positiony + 1] = this;
						this.positiony = this.positiony + 1;

					}
				} else if (nd == distances[3]) {
					if (this.positionx == 0) {
					}
					if (grid[this.positionx - 1][this.positiony] instanceof Beetle) {
					} else {
						if (grid[this.positionx - 1][this.positiony] instanceof Ant) {
							starve = 0;
						}
						grid[this.positionx][this.positiony] = null;
						grid[this.positionx - 1][this.positiony] = this;
						this.positionx = this.positionx - 1;
					}
				}


			}
		}
		if (ndnumba > 0) {
			Ant nant = null;
			Ant eant = null;
			Ant sant = null;
			Ant want = null;
			int NAneb = 0;
			int EAneb = 0;
			int SAneb = 0;
			int WAneb = 0;
			if (NA != 10) {
				nant = (Ant) grid[this.positionx][this.positiony - NA];	//finding location on grid of nearest ant on north 
				NAneb = nant.numofneighbor(grid);		// calling function that counts the numberofneighbor and assign the value to NAneb
			}
			if (EA != 10) {
				eant = (Ant) grid[this.positionx + EA][this.positiony];//finding location on grid of nearest ant on east 
				EAneb = eant.numofneighbor(grid);// calling function that counts the numberofneighbor and assign the value to EAneb
			}
			if (SA != 10) {
				sant = (Ant) grid[this.positionx][this.positiony + SA];//finding location on grid of nearest ant on south 
				SAneb = sant.numofneighbor(grid);// calling function that counts the numberofneighbor and assign the value to SAneb
			}
			if (WA != 10) {
				want = (Ant) grid[this.positionx - WA][this.positiony];//finding location on grid of nearest ant on west 
				WAneb = want.numofneighbor(grid);// calling function that counts the numberofneighbor and assign the value to WAneb
			}

			int maxantneighbor = -1;
			int nearestants[] = {NAneb, EAneb, SAneb, WAneb};
			for (int q = 0; q < 4; q++) {	// loop to find nearest distance
				if (nd == distances[q]) {
					ND[q] = nd;
				}
				if (maxantneighbor < nearestants[q] && nd == distances[q]) {	// if the number of neighbor is greater, and the value belongs to the right direction
					maxantneighbor = nearestants[q];		//assign neighbor value to maxantneighbor
				} else if (maxantneighbor == nearestants[q]) {	// if the value are the same , +1 ndneighnum to see if there's any ant with same numberofneighbor
					ndneighnum++;
				}
			}

			if ((ND[0] == nd) && (NAneb == maxantneighbor)) {	//if north ant is nearest and has the most neighbors
				if (grid[this.positionx][this.positiony - 1] instanceof Beetle) {// checking if north is occupied by beetle before moving
				} else {
					if (grid[this.positionx][this.positiony - 1] instanceof Ant) {// if the ant is on the space that beetles moving to reset starve value to 0
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx][this.positiony - 1] = this;
					this.positiony = this.positiony - 1;
				}
			} else if ((ND[1] == nd) && (EAneb == maxantneighbor)) {//if east ant is nearest and has the most neighbors
				if (grid[this.positionx + 1][this.positiony] instanceof Beetle) {// checking if east is occupied by beetle before moving
				} else {
					if (grid[this.positionx + 1][this.positiony] instanceof Ant) {// if the ant is on the space that beetles moving to reset starve value to 0
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx + 1][this.positiony] = this;
					this.positionx = this.positionx + 1;
				}
			} else if ((ND[2] == nd) && (SAneb == maxantneighbor)) {//if south ant is nearest and has the most neighbors
				if (grid[this.positionx][this.positiony + 1] instanceof Beetle) {// checking if south is occupied by beetle before moving
				} else {
					if (grid[this.positionx][this.positiony + 1] instanceof Ant) {// if the ant is on the space that beetles moving to reset starve value to 0
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx][this.positiony + 1] = this;
					this.positiony = this.positiony + 1;
				}
			} else if ((ND[3] == nd) && (WAneb == maxantneighbor)) {//if west ant is nearest and has the most neighbors
				if (grid[this.positionx - 1][this.positiony] instanceof Beetle) {// checking if west is occupied by beetle before moving
				} else {
					if (grid[this.positionx - 1][this.positiony] instanceof Ant) {// if the ant is on the space that beetles moving to reset starve value to 0
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx - 1][this.positiony] = this;
					this.positionx = this.positionx - 1;
				}
			}
		} else if (ndneighnum > 0) {		// if there are more than one ants in nearest distance, and if the numberofneighbor that they have are the same
			if (ND[0] == nd) {	// prioritize the movement in order of N,E,S,W
				if (grid[this.positionx][this.positiony - 1] instanceof Beetle) {
				} else {
					if (grid[this.positionx][this.positiony - 1] instanceof Ant) {
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx][this.positiony - 1] = this;
					this.positiony = this.positiony - 1;
				}
			} else if (ND[1] == nd) {
				if (grid[this.positionx + 1][this.positiony] instanceof Beetle) {
				} else {
					if (grid[this.positionx + 1][this.positiony] instanceof Ant) {
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx + 1][this.positiony] = this;
					this.positionx = this.positionx + 1;
				}
			} else if (ND[2] == nd) {
				if (grid[this.positionx][this.positiony + 1] instanceof Beetle) {
				} else {
					if (grid[this.positionx][this.positiony + 1] instanceof Ant) {
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx][this.positiony + 1] = this;
					this.positiony = this.positiony + 1;
				}
			} else if (ND[3] == nd) {
				if (grid[this.positionx - 1][this.positiony] instanceof Beetle) {
				} else {
					if (grid[this.positionx - 1][this.positiony] instanceof Ant) {
						starve = 0;
					}
					grid[this.positionx][this.positiony] = null;
					grid[this.positionx - 1][this.positiony] = this;
					this.positionx = this.positionx - 1;
				}
			}
		}
	}


	public void Breed(Creature[][] grid) {
		if (((this.beetlelife % 8) == 0) && (this.beetlelife != 0)) {	// every 8 turns, beetle breed
			try {
				if (grid[this.positionx][this.positiony - 1] == null) {
					grid[this.positionx][this.positiony - 1] = new Beetle(this.positionx, this.positiony - 1);
					return;
				}
			} catch (ArrayIndexOutOfBoundsException e) {	// ignoring if its out of the 10x10 grid
				;
			}
			try {
				if (grid[this.positionx + 1][this.positiony] == null) {
					grid[this.positionx + 1][this.positiony] = new Beetle(this.positionx + 1, this.positiony);
					return;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				;
			}
			try {
				if (grid[this.positionx][this.positiony + 1] == null) {
					grid[this.positionx][this.positiony + 1] = new Beetle(this.positionx, this.positiony + 1);
					return;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				;
			}
			try {
				if (grid[this.positionx - 1][this.positiony] == null) {
					grid[this.positionx - 1][this.positiony] = new Beetle(this.positionx - 1, this.positiony);
					return;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				;
			}
		}
	}


	public void Starve(Creature[][] grid) {
		this.starve++;	//+1 to starve
		if (this.starve == 5) {	// if beetle starves for 5 turns
			grid[this.positionx][this.positiony] = null;	// it dies
		}
	}
}