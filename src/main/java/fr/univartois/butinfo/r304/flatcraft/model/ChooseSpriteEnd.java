package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
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
		return new GenerateCell(this.spriteStore.getSprite("default_coal_block"));

	}

	@Override
	public Cell createSoilSurface() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(4);
        if (chance <= 3) {
        	return new GenerateCell(new Resource("End Stone",this.spriteStore.getSprite("default_sandstone"),ToolType.NO_TOOL,1));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_water"));
	}
	@Override
	public Cell createSubSoil() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource("End Stone",this.spriteStore.getSprite("default_sandstone"),ToolType.NO_TOOL,1));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(this.spriteStore.getSprite("default_obsidian"));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_obsidian"));
	}
	
	@Override
	public Cell createLeaves() {
		// TODO Auto-generated method stub
		Random r = new Random();
        
        
		return new GenerateCell(this.spriteStore.getSprite("default_mese_crystal"));
	}

}
