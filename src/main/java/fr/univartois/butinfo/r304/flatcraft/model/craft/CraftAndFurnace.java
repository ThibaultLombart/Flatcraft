/**
 * Ce fichier fait partie du projet projet-2023-2024.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.craft;


/**
 * Le type Craft
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public class CraftAndFurnace{
    
    /**
     * L'attribut rule...
     */
    private String rule;
    
    /**
     * L'attribut product...
     */
    private String product;
    
    
    /**
     * L'attribut quantity...
     */
    private int quantity;
    
    /**
     * Crée une nouvelle instance de Craft.
     * @param rule Regle
     * @param product Resultat
     * @param quantity Quantité
     */
    public CraftAndFurnace(String rule, String product, int quantity) {

        this.rule = rule;
        this.product = product;
        this.quantity = quantity;
    }
    
    /**
     * Donne l'attribut rule de cette instance de Craft.
     *
     * @return L'attribut rule de cette instance de Craft.
     */
    public String getRule() {
        return rule;
    }


    
    /**
     * Modifie l'attribut rule de cette instance de Craft.
     *
     * @param rule La nouvelle valeur de l'attribut rule pour cette instance de Craft.
     */
    public void setRule(String rule) {
        this.rule = rule;
    }


    
    /**
     * Donne l'attribut product de cette instance de Craft.
     *
     * @return L'attribut product de cette instance de Craft.
     */
    public String getProduct() {
        return product;
    }


    
    /**
     * Modifie l'attribut product de cette instance de Craft.
     *
     * @param product La nouvelle valeur de l'attribut product pour cette instance de Craft.
     */
    public void setProduct(String product) {
        this.product = product;
    }


    
    /**
     * Donne l'attribut quantity de cette instance de Craft.
     *
     * @return L'attribut quantity de cette instance de Craft.
     */
    public int getQuantity() {
        return quantity;
    }


    
    /**
     * Modifie l'attribut quantity de cette instance de Craft.
     *
     * @param quantity La nouvelle valeur de l'attribut quantity pour cette instance de Craft.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

}

