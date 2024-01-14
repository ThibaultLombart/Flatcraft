package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


/**
 * Le type EtatResource4
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public class EtatResource4 implements IResource {
	
	/**
	 * L'attribut sprite...
	 */
	private CellFactory sprite;
	
	/**
	 * Crée une nouvelle instance de EtatResource4.
	 * @param sprite sprite
	 */
	public EtatResource4(CellFactory sprite) {
		this.sprite = sprite;
	}

	@Override
    public IResource nouvelleDurete(Cell cellule) {
		return new EtatResource3(sprite);		
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource resource) {
		return false;
	}

}
