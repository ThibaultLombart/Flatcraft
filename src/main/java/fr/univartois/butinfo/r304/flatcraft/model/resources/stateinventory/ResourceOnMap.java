/**
 * Ce fichier fait partie du projet projet-2023-2024.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory;

import java.util.HashMap;
import java.util.Map;

import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

/**
 * Le type ResourceOnMap
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class ResourceOnMap implements IState {
    
    /**
     * L'attribut NEXTSPRITE...
     */
    private final static Map<Sprite, Sprite> NEXTSPRITE = Map.of(SpriteStore.getSpriteStore().getSprite("default_mineral_gold"),SpriteStore.getSpriteStore().getSprite("default_gold_lump"),
            SpriteStore.getSpriteStore().getSprite("default_mineral_copper"),SpriteStore.getSpriteStore().getSprite("default_copper_lump"),
            SpriteStore.getSpriteStore().getSprite("default_mineral_iron"),SpriteStore.getSpriteStore().getSprite("default_iron_lump"),
            SpriteStore.getSpriteStore().getSprite("default_mineral_coal"),SpriteStore.getSpriteStore().getSprite("default_coal_lump"),
            SpriteStore.getSpriteStore().getSprite("default_mineral_diamond"),SpriteStore.getSpriteStore().getSprite("default_diamond"),
            SpriteStore.getSpriteStore().getSprite("default_mineral_mese"),SpriteStore.getSpriteStore().getSprite("default_mese_crystal_fragment"),
            SpriteStore.getSpriteStore().getSprite("default_stone"),SpriteStore.getSpriteStore().getSprite("default_cobble"));
    
    private final static Map<String, String> NEXTSTRING = Map.of("Copper Ore", "Copper Lump",
            "Iron Ore", "Iron Lump",
            "Coal Ore", "Coal Lump",
            "Diamond Ore", "Diamond",
            "Mese Ore", "Mese Crystal Fragment",
            "Stone", "Cobblestone");
    
    
    /**
     * L'attribut sprite...
     */
    private Sprite sprite;
    
    /**
     * L'attribut name...
     */
    private String name;
    
    
    /**
     * Crée une nouvelle instance de ResourceInInventory.
     * @param sprite Sprite
     * @param name name
     */
    public ResourceOnMap(Sprite sprite, String name) {
        // TODO Auto-generated constructor stub.
        this.sprite = sprite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState#nextState()
     */
    @Override
    public IState nextState() {
        // TODO Auto-generated method stub.
        Sprite resSprite = sprite;
        if(NEXTSPRITE.containsKey(sprite)) {
            resSprite = NEXTSPRITE.get(sprite);
        }
        
        String resName = name;
        if(NEXTSTRING.containsKey(name)) {
            resName = NEXTSTRING.get(name);
        }
        return new ResourceInInventory(resSprite,resName);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState#getSprite()
     */
    @Override
    public Sprite getSprite() {
        // TODO Auto-generated method stub.
        return this.sprite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub.
        return name;
    }

}

