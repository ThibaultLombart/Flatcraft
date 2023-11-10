/**
 * Ce fichier fait partie du projet flatcraft.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.movables;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.IntegerProperty;

/**
 * Le type Mob
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class Mob extends AbstractMovable {

    /**
     * Crée une nouvelle instance de Mob.
     * @param game
     * @param xPosition
     * @param yPosition
     * @param sprite
     */
    private int healthPoints;
    
    private EMob type;
    
    private Sprite spriteDroit;
    
    private Sprite spriteGauche;
    
    private IDeplacementMob strategie;
    
    public Mob(FlatcraftGame game, double xPosition, double yPosition, Sprite spriteDroit,Sprite spriteGauche, EMob type, IDeplacementMob strategie) {
        // TODO Auto-generated constructor stub.
        super(game, xPosition, yPosition, spriteDroit);
        this.type = type;
        this.spriteDroit = spriteDroit;
        this.spriteGauche = spriteGauche;
        this.healthPoints = type.getHealth();
        this.strategie = strategie;
    }
    
    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints=healthPoints;
    }
    
    public boolean move(long delta) {
        strategie.deplacement(this);
        return super.move(delta);
    }

}

