package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


/**
 * Le type EtatResource0
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public class EtatResource0 implements IResource {
	
	/**
	 * L'attribut sprite...
	 */
	private CellFactory sprite;
	
	/**
	 * Crée une nouvelle instance de EtatResource0.
	 * @param sprite sprite
	 */
	public EtatResource0(CellFactory sprite) {
		this.sprite = sprite;
	}

	@Override
    public IResource nouvelleDurete(Cell cellule) {
		cellule.replaceBy(sprite.createSky());
		return this;		
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource resource) {
		resource.setState(resource.getState().nextState());
		joueur.addItem(resource);
		return true;
	}

}
