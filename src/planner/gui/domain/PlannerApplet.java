package planner.gui.domain;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
//import planner.gui.domain.*;     

// Applet is an ActionListener to handle the Domain Logic events
// In this sense, Applet is both View and Controller in the MVC architecture 
public class PlannerApplet extends JApplet implements ActionListener 
{
	  FoodMenu temp; 
	  Recipe rtemp;
	  
	  private GridBagLayout layout;                // allows us to specify placement of widgets
	  private GridBagConstraints constraints;      // controls the actions of the layout manager
	  
	  private JTextField menuName       = new JTextField(15);
	  private JTextField RecipeName     = new JTextField(14);
	  private JTextField StepName       = new JTextField(15);
	  private JTextField QuantityName   = new JTextField(4);
	 
	  private JComboBox menuList    = new JComboBox();
	  private JComboBox RecipeList  = new JComboBox(); 
	  private JComboBox ItemList    = new JComboBox(); 
	  private JComboBox UnitList    = new JComboBox();
	  
	  ButtonGroup bg = new ButtonGroup();
	  private JRadioButton dinner   = new JRadioButton("Dinner",true);
	  private JRadioButton breakfast= new JRadioButton("Breakfast",false);
	  private JRadioButton lunch    = new JRadioButton("Lunch",false);
	  	  
	  private JTextArea ta = new JTextArea(20,40);  // just one TextArea for entire application	  
	  private MenuBook mb = new MenuBook();         // root object of the hierarchy
	
	  //this variable is used to control which data fills the  
	  //menu list (depending on the menu type chosen) 
	  MenuType chosenMenuType = MenuType.DINNER;
	 
	  JLabel menu,step,quantity,unit,item; 
	  
	  JButton showMenuBook,newMenu ,showMenu ,removeMenu,
	          newRecipe,showRecipe,removeRecipe,addRecipe,
	          addIngredient,addStep;
	  	    	  
	  private String buttonNames[] =
	  {
			  "Show Book ", 
	          "New  Menu ", 
	          "Show Menu ",
	          "Rem  Menu ",
	          "New  Recipe",
	          "Show Recipe",
	          "Rem   Recipe",
	          "Add   Recipe",
	          "Add  Ingredient",
	          "   Add Step  "
	  };
	  
	  //-----------------------------------------------------------------------
	  public void init() 
	  {  		  		
		  layout = new GridBagLayout();               // new layout manager, one of the more powerful
		  setLayout(layout);                          // this manager will be used for applet
		  constraints = new GridBagConstraints();     // to control the manager
		  constraints.insets = new Insets(4,2,4,2);   // to add spacing around the widgets       

						
		  int WEST = GridBagConstraints.WEST;
		  int CENTER = GridBagConstraints.CENTER;
	   	    
		  //Define all components...	   
		  bg.add(dinner);
		  bg.add(breakfast);
		  bg.add(lunch);
	    
		  breakfast.setActionCommand("breakfast");
		  lunch.setActionCommand("lunch");
		  dinner.setActionCommand("dinner");
	       	   	    	    
		  showMenuBook   = new JButton(buttonNames[0]); 
		  newMenu        = new JButton(buttonNames[1]);
		  showMenu       = new JButton(buttonNames[2]);
		  removeMenu     = new JButton(buttonNames[3]);
		  newRecipe      = new JButton(buttonNames[4]);
		  showRecipe     = new JButton(buttonNames[5]);
		  removeRecipe   = new JButton(buttonNames[6]);
		  addRecipe      = new JButton(buttonNames[7]);   
		  addIngredient  = new JButton(buttonNames[8]);
		  addStep        = new JButton(buttonNames[9]);	 
		  menu           = new JLabel("MENU BOOK");
		  step           = new JLabel("step: ");
		  quantity       = new JLabel("quantity: ");
		  unit           = new JLabel("unit: ");
		  item           = new JLabel("item: ");
	    
		  // TECHNIQUE #1: Respond to an Event using Applet's actionPerformed()
		  showMenuBook.addActionListener(this);  
		  newMenu.addActionListener(this);
		  showMenu.addActionListener(this);
		  removeMenu.addActionListener(this);
		  newRecipe.addActionListener(this);
		  showRecipe.addActionListener(this);
		  removeRecipe.addActionListener(this);
		  addRecipe.addActionListener(this);
		  addIngredient.addActionListener(this);
		  addStep.addActionListener(this);
	    
		  menuList.addActionListener(this);
		  RecipeList.addActionListener(this);
		  ItemList.addActionListener(this);
		  UnitList.addActionListener(this);
	    
		  dinner.addActionListener(this);
		  breakfast.addActionListener(this);
		  lunch.addActionListener(this);
	    	   
		  populateComboBox(menuList, FoodMenu.hashMap);
		  populateComboBox(RecipeList, Recipe.hashMap);
		  populateComboBox(ItemList, Item.hashMap);
		  populateComboBox(UnitList, Unit.hashMap);
	   	  
		  //now start adding the components to the main pane
		  int ROW = 0;
		  addComponent(showMenuBook,  ROW, 0, 1, 1, CENTER);              
		  addComponent(menu,          ROW, 1, 1, 1, CENTER);  	    		  		    
		  addComponent(ConstructMenuPanel(),  ++ROW,0,5,1,WEST);                                                                               		  
		  addComponent(ConstructRecipePanel(),++ROW,0,5,1,WEST);
	       
		  JPanel p = new JPanel();                           // need a JPanel for a simpler layout manager
		  p.setLayout(new FlowLayout());                     // this layout manager seems to work better with TextAreas
		  // this will always generate scroll bars, however, we'll let the Pane do it only if necessary
		  //JScrollPane scroll = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		  JScrollPane scroll = new JScrollPane(ta);          // Pane will determine scrolling, give it the TextArea
		  p.add(scroll);                                     // give scroll to the Panel
		  p.setSize(300,300);                                // set size of Panel
		  addComponent(p,0,5,3,16,CENTER);                           // add panel to the grid, row 5, col 1, width 5 cells, height 1 cell
	  }
	  //-----------------------------------------------------------------------
	  JPanel ConstructRecipePanel()
	  {
		  //TODO: I'm sure there's an easier way to layout the panel, but
		  //I'm just gonna brute force it for now....
		  
		  JPanel s = new JPanel();
		  s.setLayout(new GridLayout(6, 2, 5, 5)); 
		  TitledBorder title;
	    
		  title = BorderFactory.createTitledBorder("Recipe");
	    
		  JPanel rowPanel = new JPanel();
		  rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		  
		  rowPanel.add(showRecipe);
		  rowPanel.add(Box.createHorizontalStrut(2));
		  rowPanel.add(removeRecipe);
		  rowPanel.add(Box.createHorizontalStrut(2));
		  rowPanel.add(newRecipe);
		  rowPanel.add(Box.createHorizontalStrut(2));
		  rowPanel.add(RecipeName);
		
		  JPanel rowPanel2 = new JPanel();
		  rowPanel2.setLayout(new BoxLayout(rowPanel2, BoxLayout.X_AXIS));
		  rowPanel2.add(addRecipe);
		  rowPanel2.add(Box.createHorizontalStrut(2));
		  rowPanel2.add(addIngredient);
		  rowPanel2.add(Box.createHorizontalStrut(2));
		  rowPanel2.add(addStep);
		  rowPanel2.add(Box.createHorizontalStrut(2));
		  rowPanel2.add(RecipeList);
	
		  FlowLayout f = new FlowLayout();
		  f.setAlignment(FlowLayout.LEFT);
		
		  JPanel rowPanel3 = new JPanel();		
		  rowPanel3.setLayout(f);	
		  rowPanel3.add(step);
		  rowPanel3.add(Box.createHorizontalStrut(2));
		  rowPanel3.add(StepName);
		
		  JPanel rowPanel4 = new JPanel();
		  rowPanel4.setLayout(f);	
		  rowPanel4.add(quantity);
		  rowPanel4.add(Box.createHorizontalStrut(2));
		  rowPanel4.add(QuantityName);
				
		  JPanel rowPanel5 = new JPanel();
		  rowPanel5.setLayout(f);
		  rowPanel5.add(item);
		  rowPanel5.add(Box.createHorizontalStrut(2));
		  rowPanel5.add(ItemList);
		  rowPanel5.add(Box.createHorizontalStrut(2));
		
		  JPanel rowPanel6 = new JPanel();
		  rowPanel6.setLayout(f);
		  rowPanel6.add(unit);
		  rowPanel6.add(Box.createHorizontalStrut(2));
		  rowPanel6.add(UnitList);
		  rowPanel6.add(Box.createHorizontalStrut(2));
		
		  s.add(rowPanel);
		  s.add(rowPanel2);
		  s.add(rowPanel3);
		  s.add(rowPanel4);
		  s.add(rowPanel5);
		  s.add(rowPanel6);
	    
		  s.setBorder(title);
		  return s;		
	  }
	  
	  //-----------------------------------------------------------------------
	  JPanel ConstructMenuPanel()
	  {
		  JPanel s = new JPanel();
		  s.setLayout(new GridLayout(2, 2, 5, 5)); 
		  TitledBorder title;
		  title = BorderFactory.createTitledBorder("Menu");
	    	    
		  JPanel NWPanel = new JPanel();
		  NWPanel.setLayout(new BorderLayout());
		  JPanel rowPanel = new JPanel();
		  rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		  rowPanel.add(breakfast);
		  rowPanel.add(lunch);
		  rowPanel.add(dinner);
		  rowPanel.add(menuList);
		  NWPanel.add(rowPanel, BorderLayout.NORTH);
		  
		  JPanel SWPanel = new JPanel();
		  SWPanel.setLayout(new BorderLayout());
		  JPanel rowPanel2 = new JPanel();
		  rowPanel2.setLayout(new BoxLayout(rowPanel2, BoxLayout.X_AXIS));
		  rowPanel2.add(showMenu);
		  rowPanel2.add(Box.createHorizontalStrut(2));      
		  rowPanel2.add(removeMenu);
		  rowPanel2.add(Box.createHorizontalStrut(2));	      
		  rowPanel2.add(newMenu);
		  rowPanel2.add(Box.createHorizontalStrut(2));
		  rowPanel2.add(menuName);	      
		  SWPanel.add(rowPanel2, BorderLayout.NORTH);
	  	      
		  s.add(NWPanel);
		  s.add(SWPanel);
		  s.setBorder(title);
		  return s;
	  }
	
	  //-----------------------------------------------------------------------
	  private void populateComboBox(JComboBox j, HashMap h)
	  {		
		  Iterator<String> it = h.keySet().iterator();
     	
		  while (it.hasNext())
		  {
			  String s = it.next().toString();
			  if(j.equals(menuList))
			  {
				  FoodMenu t = (FoodMenu) FoodMenu.hashMap.get(s);				  
				  if( t.getMenuType().equals(chosenMenuType ))
				  {
					  j.addItem(s);
				  }     		
			  }
			  else
			  {
				  //System.out.println(s);
				  j.addItem(s);
			  }      	
		  }       	
	  }
	  //-----------------------------------------------------------------------
	  //helper function to add components to the grid
	  private void addComponent(Component component, int row, int column,                
	                            int width, int height,int anchor)                                                                     
	  {               
		  constraints.gridx = column;                        
		  constraints.gridy = row;                        
		  constraints.gridwidth = width;
		  constraints.gridheight = height;
		  constraints.anchor = anchor;
		  // set the constraints on the layout manager
		  layout.setConstraints(component, constraints);      
		  add(component);
		  // before add the actual component to the Applet
	  }
  	   
	  //-----------------------------------------------------------------------
	  // PlannerApplet implements ActionListener 
	  public void actionPerformed(ActionEvent e) 
	  {
		  String cmd = (String)e.getActionCommand();
	        
		  if(cmd.equals("breakfast"))
		  {
			  chosenMenuType = MenuType.BREAKFAST;
			  menuList.removeAllItems();
			  populateComboBox(menuList, FoodMenu.hashMap);
			  //System.out.println("breakfast");
		  }
		  if(cmd.equals("lunch"))
		  {
			  chosenMenuType = MenuType.LUNCH;
			  menuList.removeAllItems();
			  populateComboBox(menuList, FoodMenu.hashMap);
			  //System.out.println("lunch");
		  }
		  if(cmd.equals("dinner"))
		  {
			  chosenMenuType = MenuType.DINNER;
			  menuList.removeAllItems();
			  populateComboBox(menuList, FoodMenu.hashMap);
			  //System.out.println("dinner");
		  }
	    	 
		 //SHOW BOOK --------------------------------------------------------
		  if (cmd.equals(buttonNames[0])) 
		  {
			  ta.append(mb.toString());        
		  }
	  		  
		  //NEW MENU --------------------------------------------------------
		  if (cmd.equals(buttonNames[1])) 
		  {
			  String name = menuName.getText();		  			  
			  menuList.addItem(name);
			  mb.add( new FoodMenu(name, chosenMenuType) );    	
		  } 
		  
		  //SHOW MENU --------------------------------------------------------
		  if (cmd.equals(buttonNames[2])) 
		  {		
			  String s = (String)menuList.getSelectedItem();
			  if(FoodMenu.hashMap.containsKey(s))
			  {
				  ta.append(FoodMenu.hashMap.get(s) + "\n");	
			  }		  
		  } 
		  
		  //REMOVE MENU --------------------------------------------------------
		  if (cmd.equals(buttonNames[3])) 
		  {			  
			  String name = (String) menuList.getSelectedItem();
			  
			  if(FoodMenu.hashMap.containsKey(name))
			  {			 
				 //how to remove menu from MB?
				 mb.remove((FoodElement) FoodMenu.hashMap.get(name));				 
				 FoodMenu.hashMap.remove(name);
				 menuList.removeItem(name);				 
			  }
		  } 
		  
		  //NEW RECIPE --------------------------------------------------------
		  if (cmd.equals(buttonNames[4])) 
		  {
	    	 System.out.println("new?");
	    	 String name = RecipeName.getText();		  
			  
			  rtemp = new Recipe(name);
			  Recipe.hashMap.put(name, rtemp);
			  RecipeList.addItem(name);	  
		  } 
		  
		  //SHOW RECIPE --------------------------------------------------------
		  if (cmd.equals(buttonNames[5])) //show recipe
		  {
			  System.out.println("show recipe?");
			  String recipename = (String)RecipeList.getSelectedItem();
			  if(Recipe.hashMap.containsKey(recipename))
			  {
				  ta.append(Recipe.hashMap.get(recipename).toString());
			  }		 
		  } 
		  
		  //REMOVE RECIPE -----------------------------------------------------
		  if (cmd.equals(buttonNames[6]))
		  {			  
			  String menuname = (String)menuList.getSelectedItem();
			  String recipename = (String)RecipeList.getSelectedItem();
			  		  			  			 
			  if(Recipe.hashMap.containsKey(recipename))
			  {
				  System.out.println("removed " +  recipename);
				  ((FoodMenu)FoodMenu.hashMap.get(menuname)).remove((FoodElement) Recipe.hashMap.get(recipename));
				  FoodMenu.hashMap.remove(recipename);
				  RecipeList.removeItem(recipename);
			  }  
		  } 
		  
		  //ADD RECIPE --------------------------------------------------------
		  if (cmd.equals(buttonNames[7]))
		  {		  
			  String menuname = (String)menuList.getSelectedItem();
			  String recipename = (String)RecipeList.getSelectedItem();
              			 				
			  if(FoodMenu.hashMap.containsKey(menuname))
			  {
				  System.out.println("added recipe: " + recipename + " to Menu:" + menuname);			  
				  ((FoodMenu)FoodMenu.hashMap.get(menuname)).add((FoodElement) Recipe.hashMap.get(recipename));
			  }		   			 			  				  
		  } 
		  
		  //NEW INGREDIENT -----------------------------------------------------
		  if (cmd.equals(buttonNames[8]))
		  {
			  String recipename = (String)RecipeList.getSelectedItem();
			  String item = (String)ItemList.getSelectedItem();
			  String unit = (String)UnitList.getSelectedItem();
			  
			  if(Recipe.hashMap.containsKey(recipename) )
			  {
				  if(Item.hashMap.containsKey(item))
				  {
					  if(Unit.hashMap.containsKey(unit) )
					  {						  
						  String q = QuantityName.getText();
						  Double d = Double.parseDouble(q) ;
						  Item i = (Item)Item.hashMap.get(item);
						  
						  ((Recipe)Recipe.hashMap.get(recipename)).add(new Ingredient((Item) i, d,  (Unit) Unit.hashMap.get(unit)));
						  System.out.println("new ingredient: " + Double.parseDouble(q) +" " + unit +" "+ item);
					  }
				  }
			  }
		  } 
		  
		  //NEW STEP-----------------------------------------------------------
		  if (cmd.equals(buttonNames[9]))
		  {
			  String recipename = (String)RecipeList.getSelectedItem();
			  if(Recipe.hashMap.containsKey(recipename) )
			  {			 
				  ((Recipe)Recipe.hashMap.get(recipename)).add(new Step(StepName.getText()));		 
			  }
		  } 
	 }
}


