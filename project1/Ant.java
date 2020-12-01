//Kippum Joseph Chang kjc190001
package project1;


public class Ant extends Creature {
public Ant(int positionx, int positiony) {
		super(positionx, positiony);
	}

int antlife=0;

public void Move(Creature[][] grid) {
  int NB=10,EB=10,SB=10,WB=10;
  int furthestdistance = 1;
  int nd=10; int ndnum=0;
  for(int x=0;x<10;x++){		// same logic used to find nearest beetle at orthogonal direction of beetle
    for(int y=0;y<10;y++) {
     if(grid[x][y] instanceof Beetle) {					// if the space is beetle
	  if((grid[x][y].positionx != this.positionx)&&(grid[x][y].positiony!=this.positiony)) {	// no orthogonal beetles
				
		}
		
	  else if(grid[x][y].positionx == this.positionx) {	// if x position are the same 
		int dy=grid[x][y].positiony-this.positiony;			// find difference
		 if(dy>0) {					
		  if(SB>dy) {SB=dy;}				// assign lowest value to SB
		  		}
		   else if(dy<0) {
			if(NB>Math.abs(dy)) {NB=Math.abs(dy);}	// assign lowest value to NB
				}
														}
	 
	  else if(grid[x][y].positiony==this.positiony) {	// if y position are the same 
		int dx=grid[x][y].positionx-this.positionx;	
		 if(dx>0) {	
		  if(EB>dx) {EB=dx;}				// assign lowest value to SB
				}	
		  else if(dx<0) {
			if(WB>Math.abs(dx)) {WB=Math.abs(dx);}		// assign lowest value to SB
				}			
	  								}
			
		}
    }
   }		
  
			int distances[] = {NB,EB,SB,WB};
			for(int e=0;e<4;e++) {
			 if(nd>distances[e]&&distances[e]!=10) {	// if distances are lower than previous value, and its not 10 
				 nd=distances[e];		// assign it to nd to find the lowest
				 }
			 else if(nd==distances[e]&&distances[e]!=10) {
				 ndnum++;							// checking if there is any other ants with the same distance as the closest beetle, if there is add 1 to ndnum
			 }
				   				 }
			if(NB==10&&EB==10&&SB==10&&WB==10) {}		// if there is is no orthogonal beetle
			else if(ndnum==0) {			// if there is only one beetle nearest
				if(nd==distances[0]) {	// if its north beetle
					   if(this.positiony==9) {}  // checking if its at the bound before moving
					  else if((grid[this.positionx][this.positiony+1]instanceof Ant)||(grid[this.positionx][this.positiony+1]instanceof Beetle)) {}//checking if the space is occupied
					  else { 
					  grid[this.positionx][this.positiony+1]=this;
					  grid[this.positionx][this.positiony]=null;
					  this.positiony=this.positiony+1;
					  
						 }
					   }
						
				  	
				   else if(nd==distances[1]) {
					   if(this.positionx==0) {}
					   else if((grid[this.positionx-1][this.positiony]instanceof Ant)||(grid[this.positionx-1][this.positiony]instanceof Beetle)) {}
					   else {
					   grid[this.positionx-1][this.positiony]=this; 
					   grid[this.positionx][this.positiony]=null;
					   this.positionx=this.positionx-1;
					  
								}
						}
				   else if(nd==distances[2]) {
					  if(this.positiony==0) {}
					  else if((grid[this.positionx][this.positiony-1]instanceof Ant)||(grid[this.positionx][this.positiony-1]instanceof Beetle)) {}
					  else { 
					  grid[this.positionx][this.positiony-1]=this; 
					  grid[this.positionx][this.positiony]=null;
					  this.positiony=this.positiony-1;
					  
							}
						}
				   else if(nd==distances[3]) {
					  if(this.positionx==9) {}
					  else if((grid[this.positionx+1][this.positiony]instanceof Ant)||(grid[this.positionx+1][this.positiony]instanceof Beetle)) {}
					  else {
					  grid[this.positionx][this.positiony]=null;
					  grid[this.positionx+1][this.positiony]=this; 
					  this.positionx=this.positionx+1;
							}
						}
				}
			else if(ndnum>0) {// if there is more than one nearest ants
				for(int t=0;t<4;t++) {
					if(distances[t]==10) {	// find which direction ants are nearest
						if(t==0) {			// if north go north
							if(this.positiony==0) {}
							else if((grid[this.positionx][this.positiony-1]instanceof Ant)||(grid[this.positionx][this.positiony-1]instanceof Beetle)) {}
							else {
							grid[this.positionx][this.positiony]=null;
							grid[this.positionx][this.positiony-1]=this; 
							this.positiony=this.positiony-1;
								}
							break;// break so the below codes doesn't execute if the north beetle is the nearest, because the movement is prioritized clockwise
						}
						else if(t==1) {
							if(this.positionx==9) {}
							else if((grid[this.positionx+1][this.positiony]instanceof Ant)||(grid[this.positionx+1][this.positiony]instanceof Beetle)) {}
							else {
							grid[this.positionx][this.positiony]=null;
							grid[this.positionx+1][this.positiony]=this; 
							this.positionx=this.positionx+1;
							}
							break;
						}
						else if(t==2) {
							if(this.positiony==9) {}
							else if((grid[this.positionx][this.positiony+1]instanceof Ant)||(grid[this.positionx][this.positiony+1]instanceof Beetle)) {}
							else {
							grid[this.positionx][this.positiony]=null;
							grid[this.positionx][this.positiony+1]=this; 
							this.positiony=this.positiony+1;
								}
							break;
						}
						else if(t==3) {
							if(this.positionx==0) {}
							else if((grid[this.positionx-1][this.positiony]instanceof Ant)||(grid[this.positionx-1][this.positiony]instanceof Beetle)) {}
							else {
							grid[this.positionx][this.positiony]=null;
							grid[this.positionx-1][this.positiony]=this; 
							this.positionx=this.positionx-1;
								}
							break;
							}
								}
				  				}
				  	
		if(NB!=10&&EB!=10&&SB!=10&&WB!=10) { // if surround by beetle in all direction
			for(int r=0;r<4;r++) {
				if(furthestdistance<distances[r]) {	// find the furthest beetle
					furthestdistance=distances[r];}
						}
				  		
				  		
					if(furthestdistance==distances[0]) { // if furthes is north, go north 1
						 if((grid[this.positionx][this.positiony-1]instanceof Ant)||(grid[this.positionx][this.positiony-1]instanceof Beetle)) {}
						 	else if(this.positiony==0) {}
						 	else {
							grid[this.positionx][this.positiony]=null;
							grid[this.positionx][this.positiony-1]=this; 
							this.positiony=this.positiony-1;
							}
							}
						  
						else if(furthestdistance==distances[1]) {
						 if((grid[this.positionx+1][this.positiony]instanceof Ant)||(grid[this.positionx+1][this.positiony]instanceof Beetle)) {}
						 else if(this.positionx==9) {}
						 else {
							 grid[this.positionx][this.positiony]=null;
							 grid[this.positionx+1][this.positiony]=this; 
							 this.positionx=this.positionx+1;					 
						 }
						  }
						else if(furthestdistance==distances[2]) {
							if((grid[this.positionx][this.positiony+1]instanceof Ant)||(grid[this.positionx][this.positiony+1]instanceof Beetle)) {}
							else if(this.positiony==9) {}
							else {
								grid[this.positionx][this.positiony]=null;
								grid[this.positionx][this.positiony+1]=this; 
								this.positiony=this.positiony+1;
							}
						  }
						else if(furthestdistance==distances[3]) {
							if((grid[this.positionx-1][this.positiony]instanceof Ant)||(grid[this.positionx-1][this.positiony]instanceof Beetle)) {}
							else if(this.positionx==0) {}
							else {
								grid[this.positionx][this.positiony]=null;
								grid[this.positionx-1][this.positiony]=this; 
								this.positionx=this.positionx-1;
							}
						}
				  		}
				 		}
			
		
		
					}
 public void Breed(Creature[][]grid) {
	if(this.antlife%3==0&&this.antlife!=0) {	// ants breed every 3 turns
		try {
			if (grid[this.positionx][this.positiony - 1] == null) {
				grid[this.positionx][this.positiony - 1] = new Ant(this.positionx, this.positiony - 1);
				return;
			}
		} catch (ArrayIndexOutOfBoundsException e) { // ignoring even if it scans outside the grid
			;
		}
		try {
			if (grid[this.positionx + 1][this.positiony] == null) {
				grid[this.positionx + 1][this.positiony] = new Ant(this.positionx + 1, this.positiony);
				return;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
		try {
			if (grid[this.positionx][this.positiony + 1] == null) {
				grid[this.positionx][this.positiony + 1] = new Ant(this.positionx, this.positiony + 1);
				return;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
		try {
			if (grid[this.positionx - 1][this.positiony] == null) {
				grid[this.positionx - 1][this.positiony] = new Ant(this.positionx - 1, this.positiony);
				return;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
	}
	}

 public int numofneighbor(Creature[][] grid) {
	 int numofneighbor = 0;
	 for(int x=this.positionx-1;x<this.positionx+2;x++) {// scanning all grid orthogonal and diagonal spaces to find number of neighbors
		 for(int y=this.positiony-1;y<this.positiony+2;y++) {
		 	try {
				if(grid[x][y]instanceof Ant) {
					numofneighbor++;
				}
			} catch (ArrayIndexOutOfBoundsException e) { //ignoring even if it scans outside of 10x10 grid
		 		;
			}
		 }
	 }
	 return numofneighbor-1; // -1 since it also includes it self 
 }

}
