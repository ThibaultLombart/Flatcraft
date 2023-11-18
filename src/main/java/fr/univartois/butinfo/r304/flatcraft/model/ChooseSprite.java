package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource3;
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
	public Cell createSubSoil() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_dirt"),"Dirt"),ToolType.NO_TOOL,new EtatResource3(this)));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(new Resource(new ResourceOnMap(this.spriteStore.getSprite("default_tree"),"Oak Wood"),ToolType.NO_TOOL,new EtatResource3(this)));
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
