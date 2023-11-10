package fr.univartois.butinfo.r304.flatcraft.model;

import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.MapGenerator;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;
import java.util.Random;
public class Arbre implements IGenerate {

	public static final int MAX_HAUTEUR = 8;
	
	public static final int NB_ARBRES = 5;
	
	private IGenerate map ;

	public Arbre(IGenerate map) {
		super();
		this.map = map;
	}

	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
		SimpleGameMap newMap = this.map.createMapGen(hauteur, largeur, cellFactory);
		
		for (int i = 0; i<NB_ARBRES; i++) {
			Random r = new Random();
			int pos = r.nextInt(newMap.getWidth()-1);
			int taille = r.nextInt(4,MAX_HAUTEUR);
		    	for (int j = 0; j < taille; j++) {
		    		System.out.println("j :"+j);
		        		newMap.setAt(newMap.getSoilHeight()-j-1, pos , cellFactory.createTrunk());
		    	}
		    	for(int h = taille; h > taille-3; h--) {
		    			for (int p = pos-2; p <= pos+2; p++) {
		    				if (p == pos-2 || p == pos+2) {
		    					newMap.setAt(newMap.getSoilHeight()-h-1, p, cellFactory.createLeaves());
		    				}
		    				else if (p == pos-1 || p == pos+1) {
		    					newMap.setAt(newMap.getSoilHeight()-h-1, p, cellFactory.createLeaves());
		    					newMap.setAt(newMap.getSoilHeight()-h-3, p, cellFactory.createLeaves());
		    				}
		    				else {
		    					newMap.setAt(newMap.getSoilHeight()-h-1, p, cellFactory.createLeaves());
		    					newMap.setAt(newMap.getSoilHeight()-h-3, p, cellFactory.createLeaves());
		    					newMap.setAt(newMap.getSoilHeight()-h-5, p, cellFactory.createLeaves());
		    				}
		    			}
		    		}
		   
		 }
		    
		return newMap;
	}
	
}
