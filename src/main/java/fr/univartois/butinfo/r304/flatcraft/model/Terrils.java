package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public class Terrils implements IGenerate{
	
	IGenerate map; 
	 
	int taille; 
	
	public Terrils(IGenerate map, int taille) {
		this.map = map;
		this.taille = taille;
	}
	
	@Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
		var mapGenere = map.createMapGen(hauteur, largeur, cellFactory);
		
		Random r = new Random();
        int posInit = r.nextInt(mapGenere.getWidth());
		
		int h = mapGenere.getSoilHeight();
		int l = posInit;
		if(taille == 3) {
			for (int i = 0; i < 5; i++) {
				mapGenere.setAt(h, l, cellFactory.createSubSoil());
				l += 1;
			}
			l = posInit + 2;
			h = mapGenere.getSoilHeight() + 1;
			for (int i = 0; i < 3; i++) {
				mapGenere.setAt(h, l, cellFactory.createSubSoil());
				l += 1;
			}
			l = posInit + 1;
			h = mapGenere.getSoilHeight() + 1;
			mapGenere.setAt(h, l, cellFactory.createSubSoil());
		}
		
		if(taille == 2) {
			for (int i = 0; i < 3; i++) {
				mapGenere.setAt(h, l, cellFactory.createSubSoil());
				l += 1;
			}
			l = posInit + 2;
			h = mapGenere.getSoilHeight() + 1;
			mapGenere.setAt(h, l, cellFactory.createSubSoil());
			}		
		
		if(taille == 1) {
			mapGenere.setAt(h, l, cellFactory.createSubSoil());
		}
		return mapGenere;
	}

}
