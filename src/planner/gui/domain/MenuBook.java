package planner.gui.domain;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Edward Billard
 *
 * This loads an initial set of data for the recipe application
 *
 */

public class MenuBook extends FoodComposite{
  public static void main(String[] args) {
	  
	  
	  
    MenuBook mb = new MenuBook();
    // UNCOMMENT for Ex7b-Test1:
    
    System.out.println("Total Calories: " + mb.getCalories());
    

    // UNCOMMENT for Ex7b-Test2:
    
    System.out.println(mb.toString());
    

    // UNCOMMENT for Ex7b-Test3:
    
    System.out.println("\nKeys to HashMaps");
    System.out.println("----------------------");
    printHash("FoodMenu",FoodMenu.hashMap);
    printHash("Recipe",Recipe.hashMap);
    printHash("Item",Item.hashMap);
    printHash("Unit",Unit.hashMap);
    System.out.println("----------------------");
    

} 

  public MenuBook() {
    // UNCOMMENT for Ex7a-Test1:
	 
		
    FoodMenu m1 = new FoodMenu("Birthday Dinner", MenuType.DINNER);

    Recipe r1 = new Recipe("Hamburgers");

    Unit u1 = new Unit("oz", 1.0);
    Unit u2 = new Unit("lb", 16.0);
    Unit u3 = new Unit("slice",1.0);
    Unit u4 = new Unit("regular",1.0);

    Item item1 = new Item("Hamburger Meat", u1, 200);
    Item item2 = new Item("Hamburger Bun", u1, 100);
    Item item3 = new Item("American Cheese", u3, 50);

    Ingredient ing1 = new Ingredient(item1, 0.25, u2);
    Ingredient ing2 = new Ingredient(item2, 1.0, u4);
    Ingredient ing3 = new Ingredient(item3, 1.0, u3);
  
    // this is the example from the notes, but is not used in our recipe:
    Unit u5 = new Unit("ten",10);             
    Unit u6 = new Unit("cup",8);
    Item y = new Item("Sugar", u5,  100);
    Ingredient x = new Ingredient(y, 4, u6);
  
    System.out.println("x   : " + x.getCalories());
    System.out.println("ing1: " + ing1.getCalories());
    System.out.println("ing2: " + ing2.getCalories());
    System.out.println("ing3: " + ing3.getCalories());
  
    
 
    
    // UNCOMMENT for Ex7b-Test1:
    
    add(m1);
    m1.add(r1);

    r1.add(ing1);
    r1.add(ing2);
    r1.add(ing3);

    r1.add(new Step("Grill hamburger meat for 10 minutes"));
    r1.add(new Step("After 5 minutes, place on cheese on hamburger"));
    r1.add(new Step("After 7 minutes, place top/bottom of bun on grill"));
    r1.add(new Step("When done, serve hamburger in bun"));
    
  }
  /*public String toString() {
    return "Here is an example Menu Book\n";
  }*/
  public String toString() 
  {
	  String s = "Menu Book:\n\n";
	  s += super.toString();
	  s += "Total Calories: " + getCalories();
	  return s;	  
  }

  public static void printHash(String className, HashMap hashMap) 
  {
    System.out.println(className + ":");
    Iterator it = hashMap.keySet().iterator();
    while (it.hasNext())
      System.out.println("  " + it.next());
  } 
 

}

