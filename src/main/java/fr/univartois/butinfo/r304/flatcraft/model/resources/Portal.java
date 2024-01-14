package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;

/**
 * Le type Portal
 *
 * @author thibault
 *
 * @version 0.1.0
 */
public class Portal {
    /**
     * L'attribut associatedCell...
     */
    private final GenerateCell associatedCell;
    /**
     * L'attribut portalType...
     */
    private final PortalType portalType; // Enumérations ou autres moyens peuvent être utilisés
    
    
    /**
     * Crée une nouvelle instance de Portal.
     * @param cell cell
     * @param portalType portail
     */
    public Portal(GenerateCell cell, PortalType portalType) {
        this.associatedCell = cell;
        this.portalType = portalType;
    }

	/**
	 * @return cell
	 */
	public GenerateCell getAssociatedCell() {
		return associatedCell;
	}

	/**
	 * @return portail type
	 */
	public PortalType getPortalType() {
		return portalType;
	}
    
    
}
