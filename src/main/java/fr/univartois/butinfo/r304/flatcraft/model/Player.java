package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Objects;
import java.util.Optional;

import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.MultipleResource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * La classe Player
 *
 * @author Thibault Lombart
 *
 * @version 0.1.0
 */
public class Player extends AbstractMovable{
    
	/**
	 * L'attribut Points de vie
	 */
	private IntegerProperty healthPoints;
	
	/**
	 * L'attribut Points d'xp
	 */
	private IntegerProperty xpPoints;
	
	/**
	 * L'attribut Inventaire
	 */
	private ObservableMap<Inventoriable, Integer> inventory = FXCollections.observableHashMap();
	
	/**
	 * L'attribut Item porté dans la main
	 */
	private Inventoriable wearItem;

	
	/**
	 * Crée une nouvelle instance de Player.
	 * @param game jeu
	 * @param x position x
	 * @param y position y
	 * @param sprite sprite
	 */
	public Player(FlatcraftGame game, int x, int y, Sprite sprite) {
		super(game,x,y,sprite);
		this.healthPoints = new SimpleIntegerProperty(20);
		this.xpPoints = new SimpleIntegerProperty(0);
	}

	/**
	 * @return points de vie
	 */
	public IntegerProperty getHealthPoints() {
		return healthPoints;
	}

	/**
	 * @param healthPoints points de vie
	 */
	public void setHealthPoints(IntegerProperty healthPoints) {
		this.healthPoints=healthPoints;
	}

	/**
	 * @return xp
	 */
	public IntegerProperty getXpPoints() {
		return xpPoints;
	}

	/**
	 * @param xpPoints xp
	 */
	public void setXpPoints(IntegerProperty xpPoints) {
		this.xpPoints=xpPoints;
	}

	/**
	 * Ajouter un item dans l'inventaire
	 * 
	 * @param product item
	 */
	public void addItem(Inventoriable product) {
		Inventoriable actualProduct = product;
		if (product instanceof MultipleResource) {
	       actualProduct = ((MultipleResource) product).getResource();
	    }

	    if (this.inventory.containsKey(actualProduct)) {
	        this.inventory.put(actualProduct, this.inventory.get(actualProduct) + product.getQuantity());
	    } else {
	        this.inventory.put(actualProduct, product.getQuantity());
	    }
	}
	
	/**
	 * Retirer un item de l'inventaire
	 * 
	 * @param resources item
	 */
	public void removeItem(Inventoriable resources) {
		if (this.inventory.containsKey(resources)) {
			if (this.inventory.get(resources)==1) {
				this.inventory.remove(resources);
			}
			else {
			this.inventory.put(resources, this.inventory.get(resources)-1);
			}
		}
	}
	
	/**
	 * Récuperer un item avec le nom
	 * 
	 * @param nom de l'item
	 * @return item
	 */
	public Optional<Inventoriable> getItem(String nom) {
		Object[] inventoryTmp = this.inventory.keySet().toArray();
		for (int i = 0; i < inventoryTmp.length; i++) {
			Resource tmp = (Resource) inventoryTmp[i];
			if (tmp.getName().equals(nom)) {
				return Optional.of(tmp);
			}
		}
		return Optional.empty();
	}
	
	
    /**
     * Donne l'attribut wearItem de cette instance de Player.
     *
     * @return L'attribut wearItem de cette instance de Player.
     */
    public Inventoriable getWearItem() {
        return wearItem;
    }

    
    /**
     * Modifie l'attribut wearItem de cette instance de Player.
     *
     * @param wearItem La nouvelle valeur de l'attribut wearItem pour cette instance de Player.
     */
    public void setWearItem(Inventoriable wearItem) {
        this.wearItem = wearItem;
        this.setSprite(wearItem.getSprite());
    }

    /**
     * Récuperer inventaire
     * 
     * @return inventaire
     */
    public ObservableMap<Inventoriable, Integer> getInventory() {
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
