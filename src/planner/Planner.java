package planner;

import javax.swing.*;
import planner.gui.*;

/*
Recipe: R1
Ingredients:
4.0 slice American Cheese
Steps:
1. S1
Menu: M1 (LUNCH)

Recipe: R1
Ingredients:
4.0 slice American Cheese
Steps:
1. S1


Menu Book:

Menu: Birthday Dinner (DINNER)

Recipe: Hamburgers
Ingredients:
0.25 lb Hamburger Meat
1.0 regular Hamburger Bun
1.0 slice American Cheese
Steps:
1. Grill hamburger meat for 10 minutes
2. After 5 minutes, place on cheese on hamburger
3. After 7 minutes, place top/bottom of bun on grill
4. When done, serve hamburger in bun


Menu: M1 (LUNCH)

Recipe: R1
Ingredients:
4.0 slice American Cheese
Steps:
1. S1


Total Calories: 1150 
*/
 
public class Planner { 
  public static void main(String args[]) {
    JApplet applet = new PlannerApplet();                  // create the applet
    JFrame frame = new JFrame("Menu Planner");             // create a frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // frame closes File->Exit
    frame.getContentPane().add(applet);                    // add applet to fram
    frame.setSize(1000,500);                                // make it large enough   
    applet.init();                                         // similar to constructor
    applet.start();                                        // run the applet
    frame.setVisible(true);                                // make it visible
  }
}


