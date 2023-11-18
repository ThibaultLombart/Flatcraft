package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;

public class EtatResourceUnbreakable implements IResource {
	
	private CellFactory sprite;
	
	public EtatResourceUnbreakable(CellFactory sprite) {
		this.sprite = sprite;
	}

	@Override
	public IResource nouvelleDurete(Cell cellule) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean ajoutInventaire(Player joueur, Resource ressource) {
		// TODO Auto-generated method stub
		return false;
	}

}
