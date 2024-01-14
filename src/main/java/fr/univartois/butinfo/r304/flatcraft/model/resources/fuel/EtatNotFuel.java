package fr.univartois.butinfo.r304.flatcraft.model.resources.fuel;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;

/**
 * Le type EtatNotFuel
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public class EtatNotFuel implements IResourceFuel{

	@Override
	public Inventoriable combustible(Inventoriable ressource) {
		return null;
	}

}
