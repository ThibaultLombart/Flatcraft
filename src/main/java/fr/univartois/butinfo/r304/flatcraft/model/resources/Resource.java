/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.resources;

import java.util.Objects;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.IResourceFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.IState;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * Une ressource est un élément de la carte avec lequel le joueur peut interagir.
 * Il peut soit l'extraire, soit la laisser sur place.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Resource implements Inventoriable{

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
    
    private IResourceFuel fuel;

    /**
     * Crée une nouvelle instance de Resource.
     *
     * @param state L'état state pour les textures
     * @param toolType Le type d'outils nécessaire pour extraire la ressource de la carte.
     * @param hardness La dureté initiale de la ressource.
     *
     * @throws IllegalArgumentException Si la valeur de {@code hardness} est négative.
     */
    public Resource(IState state, ToolType toolType, IResource hardness, IResourceFuel fuel) {
        this.state = state;
        this.toolType = toolType;
        this.hardness = hardness;
        this.fuel = fuel;
    }
    
    

    public IState getState() {
		return state;
	}
    
    public void setState(IState state) {
    	this.state = state;
    }



	/**
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#getName()
     */
    @Override
    public String getName() {
        return state.getName();
    }
    
    /**
     * Donne le nom unique identifiant le type de cette ressource.
     *
     * @return Le nom de cette ressource.
     */
    public String getInternalName() {
        return state.getName().toLowerCase().replace(" ", "_");
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#getSprite()
     */
    @Override
    public Sprite getSprite() {
        return state.getSprite();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#getToolType()
     */
    @Override
    public ToolType getToolType() {
        return toolType;
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

    /**
     * Donne un coup sur cette ressource pour l'extraire de la carte.
     * Cela réduit sa dureté.
     *
     * @throws IllegalStateException Si la dureté de la ressource est déjà égale
     *         à {@code 0}.
     */
    public void dig(Cell cellule) {
        hardness = hardness.nouvelleDurete(cellule);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#execute()
     */
    @Override
    public void execute() {
        // On ne fait rien ici.
        // Une ressource ne propose pas d'action par défaut.
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(state.getName());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Resource resource) {
            return state.getName().equals(resource.state.getName());
        }
        return false;
    }




	public IResourceFuel getFuel() {
		return fuel;
	}



	@Override
	public int getQuantity() {
		return 1;
	}

    
}
