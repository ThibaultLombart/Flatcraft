package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;

public interface IGenerate {

	SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory);

}