package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Player;
import fr.univartois.butinfo.r304.flatcraft.model.movables.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class GenerateCell extends AbstractCell{

	public GenerateCell(int row, int column) {
		super(row, column);
	}

	public GenerateCell(Resource resource) {
		super(resource);
	}

	public GenerateCell(Sprite sprite) {
		super(sprite);
	}

	@Override
	public boolean move(IMovable movable) {
		if (this.getResource() == null) {
			movable.setY(this.getSprite().getHeight() * this.getRow()); 
			movable.setX(this.getSprite().getWidth() * this.getColumn());
			return true;
		}
		return false;
	}

	@Override
	public boolean dig(Player player) {
		Resource resource = this.getResource();
		if(resource != null && 1 > (resource.getToolType().compareTo(player.getWearItem().getToolType()))) {
			resource.dig(this);
			this.move(player);
			return resource.getHardness().ajoutInventaire(player, resource);
		}else {
			return false;
		}
		
			
	}
}