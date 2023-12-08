package fr.univartois.butinfo.r304.flatcraft.model;

import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource3;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.ResourceOnMap;
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
		return new GenerateCell(this.spriteStore.getSprite("default_nether_sky"));

	}

	@Override
	public Cell createSoilSurface() {
		// TODO Auto-generated method stub
		
        
        
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_crimson"),"Crimson"),ToolType.NO_TOOL,new EtatResource3(this)));
		
	}
	@Override
	public Cell createSubSoil(int i) {
		// TODO Auto-generated method stub
		if(i == 44) {
			return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_bedrock"),"Bedrock"),ToolType.NO_TOOL,new EtatResourceUnbreakable(this)));
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_netherrack"),"Netherrack"),ToolType.MEDIUM_TOOL,new EtatResource3(this)));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_crimson_wood"),"Crimson Wood"),ToolType.NO_TOOL,new EtatResource3(this)));
	}
	
	@Override
	public Cell createLeaves() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_nether_wart_block"),"Nether Wart Block"),ToolType.NO_TOOL,new EtatResource3(this)));
	}

}
