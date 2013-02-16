/*package planner.gui.domain;

public class FoodMenu {
	private String menuName;
	private MenuType menuType;

	
     public FoodMenu(String menuName, MenuType menuType)
     {
    	 this.menuName=menuName;
    	 this.menuType=menuType;
     }
	
	public String toString() {
		return "";
	}
}
*/

package planner.gui.domain;
import java.util.HashMap;

public class FoodMenu extends FoodComposite
{
	private String menuName;
	private MenuType menuType;
	public static HashMap<String, FoodMenu> hashMap = new HashMap<String, FoodMenu>();
	
	public String getMenuName()
	{
		return menuName;
	}
	
	public MenuType getMenuType()
	{
		return menuType;
	}
	
	public FoodMenu(String menuName, MenuType menuType)
	{		
		this.menuName = menuName;
		this.menuType = menuType;
	    hashMap.put(menuName, this); 
	    
	}
	
	public String toString()
	{
		String s = "Menu: " + menuName + " (" + menuType + ")\n\n";
		s += super.toString();
		return s;
	}	
}
