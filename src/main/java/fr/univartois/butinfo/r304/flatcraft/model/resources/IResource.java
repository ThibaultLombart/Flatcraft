package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.Player;


public interface IResource {
	
	IResource nouvelleDurete(Cell cellule);

	void ajoutInventaire(Player joueur, Resource ressource);
	
}
