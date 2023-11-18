package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


public class EtatResource1 implements IResource {
	
	private CellFactory sprite;
	
	public EtatResource1(CellFactory sprite) {
		this.sprite = sprite;
	}

	public IResource nouvelleDurete(Cell cellule) {
		return new EtatResource0(sprite);		
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource resource) {
		return false;
	}

}
