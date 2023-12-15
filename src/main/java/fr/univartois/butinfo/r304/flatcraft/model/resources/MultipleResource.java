package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class MultipleResource implements Inventoriable {
	
	/**
     * Le sprite représentant cette ressource.
     */
    private IState state;

    /**
     * Le type d'outils nécessaire pour extraire cette ressource de la carte.
     */
    private final ToolType toolType;

    /**
     * La dureté de cette ressource.
     * Il s'agit du nombre de coups devant être appliqués avec un outil pour extraire
     * cette ressource depuis la map.
     */
    
    private IResource hardness;
    
    private int quantity;
    
    
    public MultipleResource(IState state, ToolType toolType, IResource hardness, int quantity) {
        this.state = state;
        this.toolType = toolType;
        this.hardness = hardness;
        this.quantity=quantity;
    }
    
	@Override
	public String getName() {
		return state.getName();
	}

	@Override
	public Sprite getSprite() {
		return state.getSprite();
	}

	@Override
	public ToolType getToolType() {
		return toolType;
	}

	@Override
	public String getInternalName() {
		return state.getName().toLowerCase().replace(" ", "_");
	}

	public IState getState() {
		return state;
	}
    
    public void setState(IState state) {
    	this.state = state;
    }
    
    /**
     * Donne la dureté de cet élément.
     * Il s'agit du nombre de coups devant être appliqués avec un outil pour extraire
     * cet élément depuis la carte.
     *
     * @return La dureté de cet élément.
     */
    public IResource getHardness() {
        return hardness;
    }
    
    public void dig(Cell cellule) {
        hardness = hardness.nouvelleDurete(cellule);
    }
}
