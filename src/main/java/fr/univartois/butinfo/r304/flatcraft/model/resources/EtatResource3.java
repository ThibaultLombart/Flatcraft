package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


public class EtatResource3 implements IResource {
	
	private CellFactory sprite;
	
	public EtatResource3(CellFactory sprite) {
		this.sprite = sprite;
	}

	public IResource nouvelleDurete(Cell cellule) {
		return new EtatResource2(sprite);		
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource resource) {
		return false;
	}

}
