package fr.univartois.butinfo.r304.flatcraft.model;

import fr.univartois.butinfo.r304.flatcraft.model.decorator.GenerateDecorator;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.MapGenerator;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;
import java.util.Random;
public class Arbre extends GenerateDecorator implements IGenerate {


	
	private IGenerate map;

	private int tailleMax;
	
	private int nbArbres;
	
	public Arbre(IGenerate map, int tailleMax, int nbArbres) {
		super(map);
		this.map = map;
		this.tailleMax = tailleMax;
		this.nbArbres = nbArbres;
	}

	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
		SimpleGameMap newMap = super.createMapGen(hauteur, largeur, cellFactory);
		
		for (int i = 0; i<this.nbArbres; i++) {
			Random r = new Random();
			int pos = r.nextInt(newMap.getWidth()-1);
			int taille = r.nextInt(4,this.tailleMax);
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
