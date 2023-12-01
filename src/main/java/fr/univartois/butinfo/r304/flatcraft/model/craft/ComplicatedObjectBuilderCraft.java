/**
 * Ce fichier fait partie du projet projet-2023-2024.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.craft;

import java.util.ArrayList;
import java.util.List;

/**
 * Le type ComplicatedObjectBuilderCraft
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class ComplicatedObjectBuilderCraft {
    
    /**
     * L'attribut listCraft...
     */
    private List<CraftAndFurnace> listCraft = new ArrayList<>();
    

    /**
     * Crée une nouvelle instance de ComplicatedObjectBuilderCraft.
     */
    private ComplicatedObjectBuilderCraft() {
        // TODO Auto-generated constructor stub.
    }
    
    
    
    
    
    /**
     * @return nouvelle instance
     * 
     */
    public static ComplicatedObjectBuilderCraft newInstance() {
        // TODO Auto-generated method stub.
        return new ComplicatedObjectBuilderCraft();
    }
    
    /**
     * @param item Craft
     */
    public void withCraft(CraftAndFurnace item) {
        this.listCraft.add(item);
    }





    
    /**
     * Donne l'attribut listCraft de cette instance de ComplicatedObjectBuilderCraft.
     *
     * @return L'attribut listCraft de cette instance de ComplicatedObjectBuilderCraft.
     */
    public List<CraftAndFurnace> getListCraft() {
        return listCraft;
    }

    
    

}

