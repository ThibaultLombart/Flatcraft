/**
 * Ce fichier fait partie du projet flatcraft.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.movables;


/**
 * Le type DeplacementLineaire
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class DeplacementLineaire implements IDeplacementMob {

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.movables.IDeplacementMob#move(fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable)
     */
    @Override
    public void deplacement(AbstractMovable entity) {
        // TODO Auto-generated method stub.
        entity.setHorizontalSpeed(100);
    }

}

