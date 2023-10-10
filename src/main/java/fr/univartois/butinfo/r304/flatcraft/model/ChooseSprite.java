package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

public class ChooseSprite implements CellFactory {
	
	private ISpriteStore spriteStore;
	
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
        	return new GenerateCell(new Resource("Grass",this.spriteStore.getSprite("default_grass"),ToolType.NO_TOOL,1));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_water"));
	}
	@Override
	public Cell createSubSoil() {
		// TODO Auto-generated method stub
		return new GenerateCell(new Resource("Dirt",this.spriteStore.getSprite("default_dirt"),ToolType.NO_TOOL,1));
	}

	@Override
	public Cell createTrunk() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(this.spriteStore.getSprite("default_tree"));
		}
        if (chance <= 6 && chance > 4) {
        	return new GenerateCell(this.spriteStore.getSprite("default_acasia_tree"));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_aspen_tree"));
	}

	@Override
	public Cell createLeaves() {
		// TODO Auto-generated method stub
		Random r = new Random();
        int chance = r.nextInt(8);
        if (chance <= 4) {
        	return new GenerateCell(this.spriteStore.getSprite("default_leaves"));
		}
        if (chance <= 6 && chance > 4) {
        	return new GenerateCell(this.spriteStore.getSprite("default_acasia_leaves"));
		}
		return new GenerateCell(this.spriteStore.getSprite("default_aspen_leaves"));
	}

}
