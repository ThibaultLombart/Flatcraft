
package fr.univartois.butinfo.r304.flatcraft.model.map.choose_sprite;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource3;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatNotFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.state_inventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

/**
 * Le type ChooseSpriteNether
 *
 * @author thibault
 *
 * @version 0.1.0
 */
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

		this.spriteStore = spriteStore;
	}

	@Override
	public Cell createSky() {
		return new GenerateCell(this.spriteStore.getSprite("default_nether_sky"));

	}

	@Override
	public Cell createSoilSurface() {

		
        
        
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_crimson"),"Crimson"),ToolType.NO_TOOL,new EtatResource3(this), new EtatNotFuel()));
		
	}
	@Override
	public Cell createSubSoil(int i) {

		if(i == 44) {
			return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_bedrock"),"Bedrock"),ToolType.NO_TOOL,new EtatResourceUnbreakable(this), new EtatNotFuel()));
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_netherrack"),"Netherrack"),ToolType.MEDIUM_TOOL,new EtatResource3(this), new EtatNotFuel()));
	}

	@Override
	public Cell createTrunk() {

		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_crimson_wood"),"Crimson Wood"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
	}
	
	@Override
	public Cell createLeaves() {

		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_nether_wart_block"),"Nether Wart Block"),ToolType.NO_TOOL,new EtatResource3(this), new EtatNotFuel()));
	}

}
