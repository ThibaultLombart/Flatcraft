/**
 * Ce fichier fait partie du projet flatcraft.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.movables;

import java.util.Objects;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

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
    
    private IDeplacementMob strategie;
    
    public Mob(FlatcraftGame game, double xPosition, double yPosition, Sprite spriteDroit,Sprite spriteGauche, EMob type, IDeplacementMob strategie) {
        super(game, xPosition, yPosition, spriteDroit);
        this.healthPoints = type.getHealth();
        this.strategie = strategie;
    }
    
    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints=healthPoints;
    }
    
    @Override
    public boolean move(long delta) {
        strategie.deplacement(this);
        return super.move(delta);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(healthPoints, strategie);
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
		Mob other = (Mob) obj;
		return healthPoints == other.healthPoints && Objects.equals(strategie, other.strategie);
	}

    
}

