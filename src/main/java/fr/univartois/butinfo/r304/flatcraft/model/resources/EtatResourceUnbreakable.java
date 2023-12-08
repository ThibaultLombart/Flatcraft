package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.Player;

public class EtatResourceUnbreakable implements IResource {
	
	
	public EtatResourceUnbreakable(CellFactory sprite) {

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
