

package planner.gui.domain;
import java.util.HashMap;

public class Item implements FoodElement
{
	private String itemName;
	private Unit baseUnit;
	private long baseCalories;
	public static HashMap<String, Item> hashMap = new HashMap<String, Item>();;
	
	public Item(String itemName, Unit baseUnit, long baseCalories)
	{
		this.itemName = itemName;
		this.baseUnit = baseUnit;
		this.baseCalories = baseCalories;	
	    hashMap.put(itemName, this);
	}
	
	public String getName(){return itemName;}
	public Unit getUnit(){return baseUnit;}
	public long getCalories()
	{
		return baseCalories;
	}

}
