package planner.gui.domain;

import java.util.ArrayList;


class FoodComposite implements FoodElement 
{    
	protected ArrayList<FoodElement> elements = new ArrayList<FoodElement>();
	public void add(FoodElement e)
	{
	    elements.add(e); 	    
	}
	

	public void remove(FoodElement e) 
	{
	    elements.remove(e);                          
	}			
	
	public String toString()
	{
		String s = "";
		for (FoodElement e : elements)
		{
			s += e.toString() + "\n";			 
		}
		return(s); 
	}
  
	public long getCalories() 
	{                        
		int total = 0;                               
    
	    // the simpler way to iterate using for each: 
		for (FoodElement e : elements)
		{
			total += e.getCalories(); // tree accumalation
		}	     
		return total;
	}
}