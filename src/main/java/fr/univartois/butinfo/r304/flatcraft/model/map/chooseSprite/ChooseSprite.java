package fr.univartois.butinfo.r304.flatcraft.model.map.chooseSprite;

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
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

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
     * Crée une nouvelle instance de ChooseSprite.
     */
    private ChooseSprite() {
        // TODO Auto-generated constructor stub.
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
		// TODO Auto-generated method stub
		this.spriteStore = spriteStore;
	}

	@Override
	public Cell createSky() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(4);
        if (chance <= 3) {
        	return new GenerateCell(this.spriteStore.getSprite("default_ice"));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_cloud"));
	}

	@Override
	public Cell createSoilSurface() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(4);
        if (chance <= 3) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_grass"),"Grass"),ToolType.NO_TOOL,new EtatResource3(this)));
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
		if(i > 40) {
			chance = r.nextInt(150);
			if(chance < 2) {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_diamond"),"Diamond Ore"),ToolType.MEDIUM_TOOL,new EtatResource4(this)));
			} else if (chance < 10){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_gold"),"Gold Ore"),ToolType.MEDIUM_TOOL,new EtatResource3(this)));
			} else if (chance < 15){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_iron"),"Iron Ore"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_stone"),"Stone"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			}
		}
		if(i > 30) {
			chance = r.nextInt(150);
			if (chance < 2){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_gold"),"Gold Ore"),ToolType.MEDIUM_TOOL,new EtatResource3(this)));
			} else if (chance < 5){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_iron"),"Iron Ore"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			}else if (chance < 15){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_coal"),"Coal Ore"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_stone"),"Stone"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			}
		}
		
		if(i > 27) {
			chance = r.nextInt(150);
			if (chance < 15){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_mineral_coal"),"Coal Ore"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_stone"),"Stone"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			}
		}
		
		if(i > 25) {
			return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_stone"),"Stone"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
		}
		
		if(i == 25) {
			chance = r.nextInt(150);
			if (chance < 50){
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_dirt"),"Dirt"),ToolType.NO_TOOL,new EtatResource1(this)));
			} else {
				return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_stone"),"Stone"),ToolType.MEDIUM_TOOL,new EtatResource2(this)));
			}
		}
		
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_dirt"),"Dirt"),ToolType.NO_TOOL,new EtatResource1(this)));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_tree"),"Tree"),ToolType.NO_TOOL,new EtatResource3(this)));
		}
        if (chance <= 6 && chance > 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_acacia_tree"),"Acacia Wood"),ToolType.NO_TOOL,new EtatResource3(this)));
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_aspen_tree"),"Aspen Wood"),ToolType.NO_TOOL,new EtatResource3(this)));
	}

	@Override
	public Cell createLeaves() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_leaves"),"Oak Leaves"),ToolType.NO_TOOL,new EtatResource3(this)));
		}
        if (chance <= 6 && chance > 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_acacia_leaves"),"Acacia Leaves"),ToolType.NO_TOOL,new EtatResource3(this)));
		}
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_aspen_leaves"),"Aspen Leaves"),ToolType.NO_TOOL,new EtatResource3(this)));
	}


}
