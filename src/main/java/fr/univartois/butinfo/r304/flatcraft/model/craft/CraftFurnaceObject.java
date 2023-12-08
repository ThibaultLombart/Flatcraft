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
 * Le type ComplicatedObject
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class CraftFurnaceObject {
    
    /**
     * L'attribut listCraft...
     */
    private List<CraftAndFurnace> listCraft = new ArrayList<>();
    
    /**
     * Crée une nouvelle instance de ComplicatedObject.
     */
    public CraftFurnaceObject(ComplicatedObjectBuilderCraft b) {

        this.listCraft = b.getListCraft();
    }

    
    /**
     * Donne l'attribut listCraft de cette instance de CraftFurnaceObject.
     *
     * @return L'attribut listCraft de cette instance de CraftFurnaceObject.
     */
    public List<CraftAndFurnace> getListCraft() {
        return listCraft;
    }

    
    /**
     * Modifie l'attribut listCraft de cette instance de CraftFurnaceObject.
     *
     * @param listCraft La nouvelle valeur de l'attribut listCraft pour cette instance de CraftFurnaceObject.
     */
    public void setListCraft(List<CraftAndFurnace> listCraft) {
        this.listCraft = listCraft;
    }
    
    

}

