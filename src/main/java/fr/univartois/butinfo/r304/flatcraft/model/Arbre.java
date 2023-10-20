package fr.univartois.butinfo.r304.flatcraft.model;

import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.MapGenerator;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;
import java.util.Random;
public class Arbre implements IGenerate {

	public static final int MAX_HAUTEUR = 8;
	
	public static final int NB_ARBRES = 5;
	
	private IGenerate map ;
	
	private int taille; 
	
	
	public Arbre(IGenerate map, int taille) {
		super();
		this.map = map;
		this.taille = taille;
	}

	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
		SimpleGameMap newMap = this.map.createMapGen(hauteur, largeur, cellFactory);
	    for (int y = newMap.getSoilHeight()-1; y > 0; y--){
	    	for (int j = 0; j < taille; j++) {
	        		newMap.setAt(y, newMap.getSoilHeight()-j, cellFactory.createTrunk());
	        	}
	        }
		return newMap;
	}
	
}
