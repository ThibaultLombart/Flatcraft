/**
 * Ce fichier fait partie du projet flatcraft.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.decorator;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

/**
 * Le type GenerateDecorator
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public abstract class GenerateDecorator implements IGenerate{
    
    /**
     * L'attribut decorated...
     */
    private IGenerate decorated;
    
    /**
     * Crée une nouvelle instance de GenerateDecorator.
     * @param decorated décoré
     */
    protected GenerateDecorator(IGenerate decorated) {
        this.decorated = decorated;
    }
    
    
    @Override
    public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
        return decorated.createMapGen(hauteur,largeur, cellFactory);
    }
    

}

