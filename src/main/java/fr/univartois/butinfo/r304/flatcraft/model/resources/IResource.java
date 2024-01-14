package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


/**
 * Le type IResource
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public interface IResource {
	
	/**
	 * @param cellule cellule
	 * @return IResource
	 */
	IResource nouvelleDurete(Cell cellule);

	/**
	 * @param joueur player
	 * @param ressource resource
	 * @return boolean
	 */
	boolean ajoutInventaire(Player joueur, Resource ressource);
	
}
