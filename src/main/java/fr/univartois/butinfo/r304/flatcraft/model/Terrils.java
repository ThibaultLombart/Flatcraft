package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.decorator.GenerateDecorator;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public class Terrils extends GenerateDecorator implements IGenerate{
 
	private int taille; 
	
	Random r = new Random();
	
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
