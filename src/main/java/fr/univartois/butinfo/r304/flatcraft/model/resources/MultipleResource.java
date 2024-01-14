
package fr.univartois.butinfo.r304.flatcraft.model.resources;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.IResourceFuel;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * Le type MultipleResource
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public class MultipleResource implements Inventoriable {
	

	/**
	 * L'attribut resource...
	 */
	private Resource resource;
    /**
     * L'attribut quantity...
     */
    private int quantity;
    
    /**
     * Crée une nouvelle instance de MultipleResource.
     * @param resource resource
     * @param quantity quantité
     */
    public MultipleResource(Resource resource, int quantity) {
        this.resource = resource;
        this.quantity = quantity;
 
    }
    
	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public Sprite getSprite() {

		return resource.getSprite();
	}

	@Override
	public ToolType getToolType() {
		return resource.getToolType();
	}

	@Override
	public String getInternalName() {
		return resource.getInternalName();
	}
	
	@Override
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @return resource
	 */
	public Resource getResource() {
		return resource;
	}

	@Override
    public IResourceFuel getFuel() {
		return resource.getFuel();
	}

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#execute()
     */
    @Override
    public void execute() {
        // NOT USED
    }

	@Override
	public IResource getHardness() {
		return resource.getHardness();
	}

}
