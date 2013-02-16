

package planner.gui.domain;
import java.util.ArrayList;
import java.util.HashMap;
public class Recipe extends FoodComposite
{
	private String recipeName;
	public static HashMap<String, Recipe> hashMap = new HashMap<String, Recipe>();
	private ArrayList<Step> steps = new ArrayList<Step>();
	public void add(Step s) 
	{
		steps.add(s);
	}
	public void remove(Step s) 
	{
		steps.remove(s);                         
	}
	
	
	public Recipe(String recipeName)
	{
		this.recipeName = recipeName;
		
		//hashMap.put(recipeName,this.getClass());
		hashMap.put(recipeName, this);
	}
	
	public String toString()
	{
		String s = "Recipe: " + recipeName + "\n";
		s+= "Ingredients:\n";
		
		//loop arraylist
		for (FoodElement i : elements)
		{
			s += i.toString() + "\n";
		}
		
	    
		s+= "Steps:\n";
		int j = 1;
		for (Step e : steps)
		{
		      s += j++ + ". " + e.toString() + "\n";
		}
		
		return s;
	}
}
