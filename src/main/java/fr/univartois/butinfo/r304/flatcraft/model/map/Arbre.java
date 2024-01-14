package fr.univartois.butinfo.r304.flatcraft.model.map;


import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.decorator.GenerateDecorator;

/**
 * Le type Arbre
 *
 * @author Thibault
 *
 * @version 0.1.0
 */
public class Arbre extends GenerateDecorator implements IGenerate {

	/**
	 * L'attribut r...
	 */
	Random r = new Random();
	
	/**
	 * L'attribut tailleMax...
	 */
	private int tailleMax;
	
	/**
	 * L'attribut nbArbres...
	 */
	private int nbArbres;
	
	/**
	 * Crée une nouvelle instance de Arbre.
	 * @param map map
	 * @param tailleMax taille arbre max
 	 * @param nbArbres nombre arbre max
	 */
	public Arbre(IGenerate map, int tailleMax, int nbArbres) {
		super(map);
		this.tailleMax = tailleMax;
		this.nbArbres = nbArbres;
	}

	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
		SimpleGameMap newMap = super.createMapGen(hauteur, largeur, cellFactory);
		
		for (int i = 0; i<this.nbArbres; i++) {
			Cell trunk = cellFactory.createTrunk();
			Cell leaves = cellFactory.createLeaves();
			int pos = r.nextInt(1,newMap.getWidth()-1);
			int taille = r.nextInt(this.tailleMax/2)+4;
		    	for (int j = 0; j < taille; j++) {
		        		newMap.setAt(newMap.getSoilHeight()-j-1, pos , trunk);
		    	}
		    	for(int h = taille; h > taille-3; h--) {
		    			for (int p = pos-2; p <= pos+2; p++) {
		    				if(p < largeur && p > 0) {
		    					if (p == pos-2 || p == pos+2) {
			    					newMap.setAt(newMap.getSoilHeight()-h-3, p, leaves);
			    				}
			    				else if (p == pos-1 || p == pos+1) {
			    					newMap.setAt(newMap.getSoilHeight()-h-3, p, leaves);
			    					newMap.setAt(newMap.getSoilHeight()-h-5, p, leaves);
			    				}
			    				else {
			    					newMap.setAt(newMap.getSoilHeight()-h-3, p, leaves);
			    					newMap.setAt(newMap.getSoilHeight()-h-5, p, leaves);
			    					newMap.setAt(newMap.getSoilHeight()-h-7, p, leaves);
			    				}
		    				}
		    				
		    			}
		    		}
		   
		 }
		    
		return newMap;
	}
	
}
