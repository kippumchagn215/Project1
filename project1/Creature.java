//Kippum Joseph Chang kjc190001
package project1;

 abstract class Creature {								//abstract class for beetle and ant since they share characteristics.
	public Creature(int positionx,int positiony) {		//constructor function of superclass, so the subclasses can utilize it.
		this.positionx=positionx;						//assigning the position x from the parameter to the attribute
		this.positiony=positiony;						//assigning the position y from the parameter to the attribute
	};
	public abstract void Move(Creature[][] grid);		//abstract function move and breed, both beetle and ant has move and breed function but the content should be distinct therefore make it a abstract class
	public abstract void Breed(Creature[][] grid);		
	int positionx;
	int positiony;
}
