package fr.univartois.butinfo.r304.flatcraft.model.map;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.decorator.GenerateDecorator;

/**
 * Le type Terrils
 *
 * @author north
 *
 * @version 0.1.0
 */
public class Terrils extends GenerateDecorator implements IGenerate{
 
	/**
	 * L'attribut taille...
	 */
	private int taille; 
	
	/**
	 * L'attribut r...
	 */
	Random r = new Random();
	
	/**
	 * Crée une nouvelle instance de Terrils.
	 * @param map map
	 * @param taille taille des terrils
	 */
	public Terrils(IGenerate map, int taille) {
	    super(map);
		this.taille = taille;
	}
	
	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
	    SimpleGameMap newMap = super.createMapGen(hauteur, largeur, cellFactory);
		
		
        int posInit = r.nextInt(newMap.getWidth());
        int compteur = 1;
        for(int i = 1; i < taille; i++) {
        	compteur += 2;
        }
		
		int h = newMap.getSoilHeight()-1;
		int l = posInit;
		for(int i = 0; i < taille; i ++) {
			for (int y = 0; y < compteur; y++) {
				if(l+y < largeur) {
					newMap.setAt(h, l+y, cellFactory.createSubSoil(h));
				}
			    
			}
			l += 1;
			h -= 1;
			compteur -= 2;
		}
		return newMap;
	}

}
