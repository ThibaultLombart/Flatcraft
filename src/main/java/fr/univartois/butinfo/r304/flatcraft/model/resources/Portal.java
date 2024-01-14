package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.map.GameMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.map.chooseSprite.ChooseSpriteEnd;
import fr.univartois.butinfo.r304.flatcraft.model.map.chooseSprite.ChooseSpriteNether;

public class Portal {
    private final GenerateCell associatedCell;
    private final PortalType portalType; // Enumérations ou autres moyens peuvent être utilisés
    
    
    public Portal(GenerateCell cell, PortalType portalType) {
        this.associatedCell = cell;
        this.portalType = portalType;
    }

	public GenerateCell getAssociatedCell() {
		return associatedCell;
	}

	public PortalType getPortalType() {
		return portalType;
	}
    
    
}
