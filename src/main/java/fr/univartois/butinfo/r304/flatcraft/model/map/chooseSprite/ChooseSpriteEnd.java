package fr.univartois.butinfo.r304.flatcraft.model.map.chooseSprite;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource3;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

public class ChooseSpriteEnd implements CellFactory {
	
	/**
	 * L'attribut spriteStore...
	 */
	private ISpriteStore spriteStore;
	
	   /**
     * L'attribut chooseSprite...
     */
    private static ChooseSpriteEnd chooseSpriteEnd;

    
    /**
     * Crée une nouvelle instance de ChooseSpriteEnd.
     */
    private ChooseSpriteEnd() {
        // TODO Auto-generated constructor stub.
    }
    
    /**
     * Donne l'attribut chooseSprite de cette instance de ChooseSpriteEnd.
     *
     * @return L'attribut chooseSprite de cette instance de ChooseSpriteEnd.
     */
    public static ChooseSpriteEnd getChooseSpriteEnd() {
        if(chooseSpriteEnd == null) {
            chooseSpriteEnd = new ChooseSpriteEnd();
        }
        return chooseSpriteEnd;
    }
	
	@Override
	public void setSpriteStore(ISpriteStore spriteStore) {
		// TODO Auto-generated method stub
		this.spriteStore = spriteStore;
	}

	@Override
	public Cell createSky() {
		return new GenerateCell(this.spriteStore.getSprite("default_end_sky"));

	}

	@Override
	public Cell createSoilSurface() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(4);
        if (chance <= 3) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_endstone"),"Endstone"),ToolType.MEDIUM_TOOL,new EtatResource3(this)));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_water"));
	}
	@Override
	public Cell createSubSoil(int i) {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance;
		if(i == 44) {
			return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_bedrock"),"Bedrock"),ToolType.NO_TOOL,new EtatResourceUnbreakable(this)));
		}
		if(i > 30) {
			chance = r.nextInt(150);
			if(chance < 20) {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_mese"),"Mese Ore"),ToolType.MEDIUM_TOOL,new EtatResource3(this)));
			}
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_endstone"),"Endstone"),ToolType.MEDIUM_TOOL,new EtatResource3(this)));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
        return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_chorus_plant"),"Chorus Plant"),ToolType.NO_TOOL,new EtatResource3(this)));
	}
	
	@Override
	public Cell createLeaves() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_chorus_flower"),"Chorus Leaves"),ToolType.NO_TOOL,new EtatResource3(this)));
	}

}
