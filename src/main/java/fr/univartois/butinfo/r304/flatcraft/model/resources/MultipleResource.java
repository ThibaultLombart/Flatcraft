
package fr.univartois.butinfo.r304.flatcraft.model.resources;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class MultipleResource implements Inventoriable {
	

	private Resource resource;
    private int quantity;
    
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
	
	public Resource getResource() {
		return resource;
	}

}
