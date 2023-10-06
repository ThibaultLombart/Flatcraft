package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.IMovable;
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
		if (this.getResource().equals(null)) {
			movable.setX(this.getSprite().getHeight() * this.getRow()); 
			movable.setY(this.getSprite().getWidth() * this.getColumn());
			return true;
		}
		return false;
	}

	@Override
	public boolean dig(IMovable player) {
		// TODO Auto-generated method stub
		return false;
	}
	
}