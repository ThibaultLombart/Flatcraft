/**
 * Ce fichier fait partie du projet projet-2023-2024.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory;

import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * Le type ResourceInInventory
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class ResourceInInventory implements IState {
    
    /**
     * L'attribut sprite...
     */
    private Sprite sprite;
    
    /**
     * L'attribut name...
     */
    private String name;
    
    
    /**
     * Crée une nouvelle instance de ResourceInInventory.
     * @param sprite Sprite
     * @param name Name
     */
    public ResourceInInventory(Sprite sprite, String name) {
        this.sprite = sprite;
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState#nextState()
     */
    @Override
    public IState nextState() {
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState#getSprite()
     */
    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState#getName()
     */
    @Override
    public String getName() {
        return name;
    }
    
    
}

