package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


public class EtatResource0 implements IResource {
	
	private CellFactory sprite;
	
	public EtatResource0(CellFactory sprite) {
		this.sprite = sprite;
	}

	public IResource nouvelleDurete(Cell cellule) {
		cellule.replaceBy(sprite.createSky());
		return this;		
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource resource) {
		joueur.addItem(resource);
		return true;
	}

}
