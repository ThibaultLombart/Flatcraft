package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Objects;
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
			else {
			this.inventory.put(res, this.inventory.get(res)-1);
			}
		}
	}
	
	public Optional<Resource> getItem(String nom) {
		Object[] inventoryTmp = this.inventory.keySet().toArray();
		for (int i = 0; i < inventoryTmp.length; i++) {
			Resource tmp = (Resource) inventoryTmp[i];
			if (tmp.getName().equals(nom)) {
				return Optional.of(tmp);
			}
		}
		return Optional.empty();
	}

	
	public ObservableMap<Resource, Integer> getInventory() {
		return inventory;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(healthPoints, inventory, xpPoints);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(healthPoints, other.healthPoints) && Objects.equals(inventory, other.inventory)
				&& Objects.equals(xpPoints, other.xpPoints);
	}
}
