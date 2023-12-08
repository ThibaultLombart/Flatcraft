package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.ArrayList;
import java.util.Optional;

import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Player extends AbstractMovable{

	private IntegerProperty healthPoints;
	
	private IntegerProperty xpPoints;
	
	private ObservableMap<Resource, Integer> inventory = FXCollections.observableHashMap();

	
	public Player(FlatcraftGame game, int x, int y, Sprite sprite) {
		super(game,x,y,sprite);
		this.healthPoints = new SimpleIntegerProperty(20);
		this.xpPoints = new SimpleIntegerProperty(0);
	}

	public IntegerProperty getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(IntegerProperty healthPoints) {
		this.healthPoints=healthPoints;
	}

	public IntegerProperty getXpPoints() {
		return xpPoints;
	}

	public void setXpPoints(IntegerProperty xpPoints) {
		this.xpPoints=xpPoints;
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
			if (this.inventory.get(res)<=1) {
				this.inventory.remove(res);
			}
			this.inventory.put(res, this.inventory.get(res)-1);
		}
	}
	
	public Optional<Resource> getItem(String nom) {
		Object[] inventory = this.inventory.keySet().toArray();
		for (int i = 0; i < inventory.length; i++) {
			Resource tmp = (Resource) inventory[i];
			if (tmp.getName() == nom) {
				return Optional.of(tmp);
			}
		}
		return Optional.empty();
	}

	public ObservableMap<Resource, Integer> getInventory() {
		return inventory;
	}
	
	
}
