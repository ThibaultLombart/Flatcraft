/**
 * Ce fichier fait partie du projet flatcraft.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;

/**
 * Le type MapGenerator
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class MapGenerator implements IGenerate{
    
    private static MapGenerator mapGenerator = null;
    
    
    /**
     * Crée une nouvelle instance de MapGenerator.
     */
    private MapGenerator() {
        // TODO Auto-generated constructor stub.
    }
    
    public static MapGenerator getMapGenerator() {
        if(mapGenerator == null) {
            mapGenerator = new MapGenerator();
        }
        return mapGenerator;
    }
    
    
    
    @Override
	public SimpleGameMap createMapGen(int hauteur, int largeur, CellFactory cellFactory) {
        
        SimpleGameMap map = new SimpleGameMap(hauteur,largeur,20);
        
        for (int i = 0; i < hauteur; i++) {
            for (int y = 0; y < largeur; y++) {
                if(i > 20) {
                    map.setAt(i, y, cellFactory.createSubSoil());
                } else if (i == 20) {
                    map.setAt(i, y, cellFactory.createSoilSurface());
                } else {
                    map.setAt(i, y, cellFactory.createSky());
                }
                
            }
        }   
        return map;

    }

}

