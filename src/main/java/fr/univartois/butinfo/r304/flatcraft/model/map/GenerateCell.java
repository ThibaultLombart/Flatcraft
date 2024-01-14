package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.Player;
import fr.univartois.butinfo.r304.flatcraft.model.movables.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Portal;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.state_inventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * Le type GenerateCell
 *
 * @author Thibault
 *
 * @version 0.1.0
 */
public class GenerateCell extends AbstractCell{
	
	/**
	 * L'attribut portal...
	 */
	private Portal portal;
	
	/**
	 * Crée une nouvelle instance de GenerateCell.
	 * @param row ligne
	 * @param column colonne
	 */
	public GenerateCell(int row, int column) {
		super(row, column);

	}

	/**
	 * Crée une nouvelle instance de GenerateCell.
	 * @param resource resource
	 */
	public GenerateCell(Resource resource) {
		super(resource);

	}

	/**
	 * Crée une nouvelle instance de GenerateCell.
	 * @param sprite sprite
	 */
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
		if(resource != null && 1 > (resource.getToolType().compareTo(player.getWearItem().getToolType())) && player.getWearItem().getHardness() instanceof EtatResourceUnbreakable) {
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

	/**
	 * @return portail
	 */
	public Portal getPortal() {
		return portal;
	}

	/**
	 * @param portal set portail
	 */
	public void setPortal(Portal portal) {
		this.portal = portal;
	}
    
    /**
     * @return verifie si portail
     */
    public boolean hasPortal() {
    	return portal != null;
    }
}