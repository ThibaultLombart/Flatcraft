package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;

/**
 * Le type IGenerate
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public interface IGenerate {

	/**
	 * @param hauteur hauteur
	 * @param largeur largeur
	 * @param cellFactory CellFactory
	 * @return SimpleGameMap
	 */
	SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory);

}