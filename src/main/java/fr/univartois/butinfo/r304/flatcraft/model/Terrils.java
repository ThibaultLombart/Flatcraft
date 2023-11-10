package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.decorator.GenerateDecorator;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public class Terrils extends GenerateDecorator implements IGenerate{
	
	private IGenerate map; 
	 
	private int taille; 
	
	public Terrils(IGenerate map, int taille) {
	    super(map);
		this.map = map;
		this.taille = taille;
	}
	
	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
	    SimpleGameMap newMap = super.createMapGen(hauteur, largeur, cellFactory);
		
		Random r = new Random();
        int posInit = r.nextInt(newMap.getWidth());
        int compteur = 1;
        for(int i = 1; i < taille; i++) {
        	compteur += 2;
        }
		
		int h = newMap.getSoilHeight()-1;
		int l = posInit;
		for(int i = 0; i < taille; i ++) {
			for (int y = 0; y < compteur; y++) {
			    newMap.setAt(h, l+y, cellFactory.createSubSoil());
			}
			l += 1;
			h -= 1;
			compteur -= 2;
		}
		
		/*
		 * if(taille == 3) {
			for (int i = 0; i < 5; i++) {
				mapGenere.setAt(h, l+i, cellFactory.createSubSoil());
			}
			l = l + 1;
			h -= 1;
			for (int i = 0; i < 3; i++) {
				mapGenere.setAt(h, l+i, cellFactory.createSubSoil());
			}
			l = l + 1;
			h -= 1;
			mapGenere.setAt(h, l, cellFactory.createSubSoil());
		}
		
		if(taille == 2) {
			for (int i = 0; i < 3; i++) {
				mapGenere.setAt(h, l+i, cellFactory.createSubSoil());
			}
			l = l + 1;
			h -= 1;
			mapGenere.setAt(h, l, cellFactory.createSubSoil());
			}		
		
		if(taille == 1) {
			mapGenere.setAt(h, l+1, cellFactory.createSubSoil());
		}
		 */
		
		return newMap;
	}

}
