/**
 * Ce fichier fait partie du projet projet-2023-2024.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.resources.state_inventory;

import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * Le type IState
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public interface IState {
    
    /**
     * @return IState new State
     */
    IState nextState();
    
    /**
     * @return Sprite
     */
    Sprite getSprite();
    
    /**
     * @return Name
     */
    String getName();

}

