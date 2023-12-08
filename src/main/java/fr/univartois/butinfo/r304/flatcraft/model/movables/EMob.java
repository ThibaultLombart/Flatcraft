/**
 * Ce fichier fait partie du projet flatcraft.
 *
 * (c) 2023 thibault.lombart
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.movables;

/**
 * Le type EMob
 *
 * @author thibault.lombart
 *
 * @version 0.1.0
 */
public enum EMob {
    
    
    COCHON("pig_droit","pig_gauche",10),
    VACHE("vache_droit","vache_gauche",10),
    POULE("poule_droit","poule_gauche",4),
    MOUTON("mouton_droit","mouton_gauche",10),
    ZOMBIE("zombie_droit","zombie_gauche",20);
    
    private String spriteDroit;
    private String spriteGauche;
    private int health;
    
    private EMob(String spriteDroit, String spriteGauche, int health) {
        this.spriteDroit = spriteDroit;
        this.spriteGauche = spriteGauche;
        this.health = health;
    }


    
    /**
     * Donne l'attribut spriteDroit de cette instance de EMob.
     *
     * @return L'attribut spriteDroit de cette instance de EMob.
     */
    public String getSpriteDroit() {
        return spriteDroit;
    }

    
    /**
     * Donne l'attribut spriteGauche de cette instance de EMob.
     *
     * @return L'attribut spriteGauche de cette instance de EMob.
     */
    public String getSpriteGauche() {
        return spriteGauche;
    }

    
    /**
     * Donne l'attribut health de cette instance de EMob.
     *
     * @return L'attribut health de cette instance de EMob.
     */
    public int getHealth() {
        return health;
    }
    
    
    
    

}

