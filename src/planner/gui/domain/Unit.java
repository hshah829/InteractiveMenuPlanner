package planner.gui.domain;

import java.util.HashMap;

public class Unit {
	
	private String unitName;
	private double factor;
	public static HashMap<String, Unit> hashMap = new HashMap<String, Unit>();
	
	public Unit(String unitName, double factor){
		this.unitName = unitName;
		this.factor = factor;
		hashMap.put(unitName, this);
	
	}
	
	public String getName(){
		return unitName;
	}

	public double getFactor(){
		return factor;
	}
}
