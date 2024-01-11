package fr.univartois.butinfo.r304.flatcraft.model.resources.fuel;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;

public class EtatFuel implements IResourceFuel{

	@Override
	public void coock(Cell full) {
		// TODO Auto-generated method stub
		full.replaceBy(null);
	}

}
