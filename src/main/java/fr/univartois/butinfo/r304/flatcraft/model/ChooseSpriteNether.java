package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

public class ChooseSpriteNether implements CellFactory {
	
    /**
     * L'attribut spriteStore...
     */
    private ISpriteStore spriteStore;
    
    /**
     * L'attribut chooseSprite...
     */
    private static ChooseSpriteNether chooseSpriteNether;
    
    
    
    /**
     * Crée une nouvelle instance de ChooseSpriteNether.
     */
    private ChooseSpriteNether() {
        // TODO Auto-generated constructor stub.
    }
    
    /**
     * Donne l'attribut chooseSprite de cette instance de ChooseSpriteNether.
     *
     * @return L'attribut chooseSprite de cette instance de ChooseSpriteNether.
     */
    public static ChooseSpriteNether getChooseSpriteNether() {
        if(chooseSpriteNether == null) {
            chooseSpriteNether = new ChooseSpriteNether();
        }
        return chooseSpriteNether;
    }
	
	@Override
	public void setSpriteStore(ISpriteStore spriteStore) {
		// TODO Auto-generated method stub
		this.spriteStore = spriteStore;
	}

	@Override
	public Cell createSky() {
		return new GenerateCell(this.spriteStore.getSprite("default_lava"));

	}

	@Override
	public Cell createSoilSurface() {
		// TODO Auto-generated method stub
		
        
        
        return new GenerateCell(new Resource("NetherRack",this.spriteStore.getSprite("default_desert_stone"),ToolType.NO_TOOL,1));
		
	}
	@Override
	public Cell createSubSoil() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource("NetherRack",this.spriteStore.getSprite("default_desert_stone"),ToolType.NO_TOOL,1));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(this.spriteStore.getSprite("default_brick"));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_brick"));
	}
	
	@Override
	public Cell createLeaves() {
		// TODO Auto-generated method stub
		Random r = new Random();
        
        
		return new GenerateCell(this.spriteStore.getSprite("default_brick"));
	}

}
