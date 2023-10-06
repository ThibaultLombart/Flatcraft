package fr.univartois.butinfo.r304.flatcraft.model;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Player {

	private IntegerProperty healthPoints;
	
	private IntegerProperty xpPoints;
	
	private ObservableMap<Resource, Integer> inventory = FXCollections.observableHashMap();

	public int getHealthPoints() {
		return healthPoints.getValue();
	}

	public void setHealthPoints(IntegerProperty healthPoints) {
		this.healthPoints.setValue(healthPoints.getValue());
	}

	public int getXpPoints() {
		return xpPoints.getValue();
	}

	public void setXpPoints(IntegerProperty xpPoints) {
		this.xpPoints.setValue(xpPoints.getValue());
	}

	public void addItem(Resource res) {
		if (this.inventory.containsKey(res)) {
			this.inventory.put(res, this.inventory.get(res)+1);
		}
		else {
			this.inventory.put(res, 1);
		}
	}
	
	public void removeItem(Resource res) {
		if (this.inventory.containsKey(res)) {
			if (this.inventory.get(res)==1) {
				this.inventory.remove(res);
			}
			this.inventory.put(res, this.inventory.get(res)-1);
		}
	}
	
	
	
}
