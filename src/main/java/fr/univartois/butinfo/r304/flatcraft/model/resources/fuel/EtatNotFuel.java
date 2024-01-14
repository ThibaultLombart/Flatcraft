package fr.univartois.butinfo.r304.flatcraft.model.resources.fuel;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;

public class EtatNotFuel implements IResourceFuel{

	@Override
	public Inventoriable combustible(Inventoriable ressource) {
		return null;
	}

}
