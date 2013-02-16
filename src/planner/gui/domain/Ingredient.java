package planner.gui.domain;




public class Ingredient implements FoodElement  
{
	/** specific info about each ingredient item */
	private Item item;
	
	/** amount which will be scaled by unit */
	private double quantity; 
	
	/** unit of measure (cup, oz, etc..) */
	private Unit unit;        
	
	/** standard constructor
	* @param item			
	* @param quantity	    
	* @param unit	
	* */		
	public Ingredient(Item item, double quantity, Unit unit)
	{
		this.item = item;
		this.quantity = quantity;
		this.unit = unit;	
	}
	
	/** calculate the total calories for a particular ingredient
	* @return the scaled scaled count of calories
	*/
	public long getCalories() 
	{
		double denominator = item.getUnit().getFactor();
		return (long)(((quantity * unit.getFactor()) / denominator)* item.getCalories());		
	}
	
	public String toString()
	{
		String s = quantity + " " + unit.getName() + " " + item.getName();
		return s;
	}
}
