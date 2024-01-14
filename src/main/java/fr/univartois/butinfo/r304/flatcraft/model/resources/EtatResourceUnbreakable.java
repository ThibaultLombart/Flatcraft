package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;

/**
 * Le type EtatResourceUnbreakable
 *
 * @author north
 *
 * @version 0.1.0
 */
public class EtatResourceUnbreakable implements IResource {
	
	
	/**
	 * Crée une nouvelle instance de EtatResourceUnbreakable.
	 * @param sprite sprite
	 */
	public EtatResourceUnbreakable(CellFactory sprite) {
	    // not use
	}

	@Override
	public IResource nouvelleDurete(Cell cellule) {
		return this;
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource ressource) {

		return false;
	}

}
