package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.Player;
import fr.univartois.butinfo.r304.flatcraft.model.movables.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Portal;
import fr.univartois.butinfo.r304.flatcraft.model.resources.PortalType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class GenerateCell extends AbstractCell{
	
	private Portal portal;
	
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

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.Cell#setResource(fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable)
     */
    @Override
    public boolean setResource(Inventoriable resource) {
    	if(this.getResource() == null && !(resource.getHardness() instanceof EtatResourceUnbreakable) ) {
    		Resource resourceNew = new Resource(new ResourceOnMap(resource.getSprite(),resource.getName()),resource.getToolType(),resource.getHardness(),resource.getFuel());
    		this.replaceBy(new GenerateCell(resourceNew));
        	return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.Cell#accepts(fr.univartois.butinfo.r304.flatcraft.model.movables.IMovable)
     */
    @Override
    public boolean accepts(IMovable movable) {
        if(this.getResource() == null) {
        	return true;
        }
        return false;
    }

    @Override
    public void execute(FlatcraftGame game) {
    	if (this.hasPortal()) {
            game.changeDimension(this.portal.getPortalType());
    	}
    }

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}
    
    public boolean hasPortal() {
    	if (portal != null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}