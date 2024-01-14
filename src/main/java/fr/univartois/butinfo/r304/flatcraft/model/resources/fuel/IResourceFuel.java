package fr.univartois.butinfo.r304.flatcraft.model.resources.fuel;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;

/**
 * Le type IResourceFuel
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public interface IResourceFuel {
	/**
	 * @param ressouce resource
	 * @return Inventoriable
	 */
	Inventoriable combustible(Inventoriable ressouce);
}
