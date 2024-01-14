package fr.univartois.butinfo.r304.flatcraft.model.map.choose_sprite;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource1;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource2;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource3;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource4;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatNotFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.state_inventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

/**
 * Le type ChooseSprite
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public class ChooseSprite implements CellFactory {
	
	/**
	 * L'attribut spriteStore...
	 */
	private ISpriteStore spriteStore;
	
	/**
	 * L'attribut chooseSprite...
	 */
	private static ChooseSprite chooseSprite;
	
	/**
	 * L'attribut STONE...
	 */
	public static final String STONE = "Stone";
	/**
	 * L'attribut STONE_DEFAULT...
	 */
	public static final String STONE_DEFAULT = "default_stone";
	/**
	 * L'attribut RANDOM...
	 */
	public static final Random RANDOM = new Random();
	
	
	
    /**
     * Crée une nouvelle instance de ChooseSprite.
     */
    private ChooseSprite() {
    }
    
    /**
     * Donne l'attribut chooseSprite de cette instance de ChooseSprite.
     *
     * @return L'attribut chooseSprite de cette instance de ChooseSprite.
     */
    public static ChooseSprite getChooseSprite() {
        if(chooseSprite == null) {
            chooseSprite = new ChooseSprite();
        }
        return chooseSprite;
    }
    
	
	
	@Override
	public void setSpriteStore(ISpriteStore spriteStore) {
		this.spriteStore = spriteStore;
	}

	@Override
	public Cell createSky() {
        int chance = RANDOM.nextInt(4);
        if (chance <= 3) {
        	return new GenerateCell(this.spriteStore.getSprite("default_ice"));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_cloud"));
	}

	@Override
	public Cell createSoilSurface() {
		int chance = RANDOM.nextInt(4);
        if (chance <= 3) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_grass"),"Grass"),ToolType.NO_TOOL,new EtatResource3(this), new EtatNotFuel()));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_water"));
	}
	
	@Override
	public Cell createSubSoil(int i) {
		int chance;
		if(i == 44) {
			return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_bedrock"),"Bedrock"),ToolType.NO_TOOL,new EtatResourceUnbreakable(this), new EtatNotFuel()));
		}
		if(i > 40) {
			chance = RANDOM.nextInt(150);
			if(chance < 2) {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_diamond"),"Diamond"),ToolType.MEDIUM_TOOL,new EtatResource4(this), new EtatNotFuel()));
			} else if (chance < 10){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_gold"),"Gold Lump"),ToolType.MEDIUM_TOOL,new EtatResource3(this), new EtatNotFuel()));
			} else if (chance < 15){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_iron"),"Iron Lump"),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite(STONE_DEFAULT),STONE),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
			}
		}
		if(i > 30) {
			chance = RANDOM.nextInt(150);
			if (chance < 2){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_gold"),"Gold Lump"),ToolType.MEDIUM_TOOL,new EtatResource3(this), new EtatNotFuel()));
			} else if (chance < 5){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_iron"),"Iron Lump"),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
			}else if (chance < 15){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_coal"),"Coal Lump"),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatFuel()));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite(STONE_DEFAULT),STONE),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
			}
		}
		
		if(i > 27) {
			chance = RANDOM.nextInt(150);
			if (chance < 15){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_coal"),"Coal Ore"),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatFuel()));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite(STONE_DEFAULT),STONE),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
			}
		}
		
		if(i > 25) {
			return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite(STONE_DEFAULT),STONE),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
		}
		
		if(i == 25) {
			chance = RANDOM.nextInt(150);
			if (chance < 50){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_dirt"),"Dirt"),ToolType.NO_TOOL,new EtatResource1(this), new EtatNotFuel()));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite(STONE_DEFAULT),STONE),ToolType.MEDIUM_TOOL,new EtatResource2(this), new EtatNotFuel()));
			}
		}
		
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_dirt"),"Dirt"),ToolType.NO_TOOL,new EtatResource1(this), new EtatNotFuel()));
	}

	@Override
	public Cell createTrunk() {
		int chance = RANDOM.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_tree"),"Tree"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
		}
        if (chance <= 6 && chance > 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_acacia_tree"),"Acacia Wood"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_aspen_tree"),"Aspen Wood"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
	}

	@Override
	public Cell createLeaves() {
		int chance = RANDOM.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_leaves"),"Oak Leaves"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
		}
        if (chance <= 6 && chance > 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_acacia_leaves"),"Acacia Leaves"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_aspen_leaves"),"Aspen Leaves"),ToolType.NO_TOOL,new EtatResource3(this), new EtatFuel()));
	}


}
