package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.Player;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public interface IResource {
	
	int nouvelleDurete(Cell cellule);

	void ajoutInventaire(Player joueur, Sprite sprite);
	
}
