package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.Player;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class GenerateCell extends AbstractCell{

	public GenerateCell(int row, int column) {
		super(row, column);
		// TODO Auto-generated constructor stub
	}

	public GenerateCell(Resource resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}

	public GenerateCell(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(IMovable movable) {
		// TODO Auto-generated method stub
		if (this.getResource() == null) {
			movable.setY(this.getSprite().getHeight() * this.getRow()); 
			movable.setX(this.getSprite().getWidth() * this.getColumn());
			return true;
		}
		return false;
	}

	@Override
	public boolean dig(Player player) {
		// TODO Auto-generated method stub
		this.getResource().dig(this);
		return this.getResource().getHardness().ajoutInventaire(player, getResource());
			
	}

	
}