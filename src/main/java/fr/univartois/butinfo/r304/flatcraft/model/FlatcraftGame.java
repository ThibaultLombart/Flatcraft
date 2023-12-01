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

package fr.univartois.butinfo.r304.flatcraft.model;

import java.awt.Taskbar.State;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.r304.flatcraft.model.craft.CraftAndFurnace;
import fr.univartois.butinfo.r304.flatcraft.model.craft.CraftFurnaceObject;
import fr.univartois.butinfo.r304.flatcraft.model.craft.RuleParser;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.MapGenerator;
import fr.univartois.butinfo.r304.flatcraft.model.movables.DeplacementLineaire;
import fr.univartois.butinfo.r304.flatcraft.model.movables.EMob;
import fr.univartois.butinfo.r304.flatcraft.model.movables.Mob;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource3;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.ResourceInInventory;
import fr.univartois.butinfo.r304.flatcraft.model.resources.stateinventory.ResourceOnMap;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

/**
 * La classe {@link FlatcraftGame} permet de gérer une partie du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class FlatcraftGame {

    /**
     * La largeur de la carte du jeu affichée (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu affichée (en pixels).
     */
    private final int height;

    /**
     * Le contrôleur de l'application.
     */
    private IFlatcraftController controller;
    
    private IGenerate generate;

    /**
     * L'instance e {@link ISpriteStore} utilisée pour créer les sprites du jeu.
     */
    private ISpriteStore spriteStore;
    
    private CraftFurnaceObject craft;
    
    private CraftFurnaceObject furnace;

    /**
     * L'instance de {@link CellFactory} utilisée pour créer les cellules du jeu.
     */
    private CellFactory cellFactory;

    /**
     * La carte du jeu, sur laquelle le joueur évolue.
     */
    private GameMap map;

    /**
     * Le temps écoulé depuis le début de la partie.
     */
    private IntegerProperty time = new SimpleIntegerProperty(12);

    /**
     * Le niveau actuel de la partie.
     */
    private IntegerProperty level = new SimpleIntegerProperty(1);

    /**
     * La représentation du joueur.
     */
    private Player player;

    /**
     * La liste des objets mobiles du jeu.
     */
    private List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation simulant le temps qui passe dans le jeu.
     */
    private FlatcraftAnimation animation = new FlatcraftAnimation(this, movableObjects);
    
    private final static Map<String, Sprite> MAPCRAFTSPRITE = Map.of("wood",SpriteStore.getSpriteStore().getSprite("default_wood"),
            "stick",SpriteStore.getSpriteStore().getSprite("default_stick"),
            "woodpick",SpriteStore.getSpriteStore().getSprite("default_tool_woodpick"),
            "woodaxe",SpriteStore.getSpriteStore().getSprite("default_tool_woodaxe"),
            "stonepick",SpriteStore.getSpriteStore().getSprite("default_tool_stonepick"),
            "stoneaxe",SpriteStore.getSpriteStore().getSprite("default_tool_stoneaxe"),
            "steelpick",SpriteStore.getSpriteStore().getSprite("default_tool_steelpick"));
    
    private final static Map<String, String> MAPCRAFTNAME = Map.of("woodpick","Wood Pickaxe",
            "woodaxe","Wood Axe",
            "stonepick","Stone Pickaxe",
            "stoneaxe","Stone Axe",
            "steelpick","Steel Pickaxe");
   
    private final static Map<String, Sprite> MAPCOOKSPRITE = Map.of("gold_lingot",SpriteStore.getSpriteStore().getSprite("default_gold_ingot"),
            "steel_lingot",SpriteStore.getSpriteStore().getSprite("default_steel_ingot"),
            "copper_lingot",SpriteStore.getSpriteStore().getSprite("default_copper_ingot"));
    
    private final static Map<String, String> MAPCOOKNAME = Map.of("gold_lingot","Gold Lingot",
            "steel_lingot","Steel Lingot",
            "copper_lingot","Copper Lingot");

    /**
     * Crée une nouvelle instance de FlatcraftGame.
     *
     * @param width La largeur de la carte du jeu (en pixels).
     * @param height La hauteur de la carte du jeu (en pixels).
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory La fabrique permettant de créer les cellules du jeux.
     */
    public FlatcraftGame(int width, int height, ISpriteStore spriteStore, CellFactory factory) {
        this.width = width;
        this.height = height;
        this.spriteStore = spriteStore;
        this.cellFactory = factory;
        this.cellFactory.setSpriteStore(spriteStore);
    }

    /**
     * Donne la largeur de la carte du jeu affichée (en pixels).
     *
     * @return La largeur de la carte du jeu affichée (en pixels).
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la hauteur de la carte du jeu affichée (en pixels).
     *
     * @return La hauteur de la carte du jeu affichée (en pixels).
     */
    public int getHeight() {
        return height;
    }

    /**
     * Associe à cette partie le contrôleur gérant l'affichage du jeu.
     *
     * @param controller Le contrôleur gérant l'affichage.
     */
    public void setController(IFlatcraftController controller) {
        this.controller = controller;
    }
    
    public void setGenerate(IGenerate generate) {
        this.generate = generate;
    }

    /**
     * Prépare la partie de Flatcraft avant qu'elle ne démarre.
     */
    public void prepare() {
        // On crée la carte du jeu.
        map = createMap();
        controller.prepare(map);
       
        // TODO On crée le joueur, qui se trouve sur le sol à gauche de la carte.
        player = new Player(this, 0, 19*16, spriteStore.getSprite("player"));
        movableObjects.add(player);
        controller.addMovable(player);
        
        /*
         *  MOBS
         * 
         *  Mob cochon = new Mob(this, 50, 19*16, spriteStore.getSprite(EMob.COCHON.getSpriteDroit()),spriteStore.getSprite(EMob.COCHON.getSpriteGauche()),EMob.COCHON,new DeplacementLineaire());
            movableObjects.add(cochon);
            controller.addMovable(cochon);
            cochon.move(10);
         * 
         */
        
        
        // TODO On fait le lien entre les différentes propriétés et leur affichage.
        controller.bindTime(this.time);
        controller.bindLevel(this.level);
        controller.bindHealth(player.getHealthPoints());
        controller.bindXP(player.getXpPoints());
        
        
        RuleParser parser1 = new RuleParser("src/main/resources/fr/univartois/butinfo/r304/flatcraft/model/craft/craftrules.txt");
        this.craft = parser1.build();
        
        RuleParser parser2 = new RuleParser("src/main/resources/fr/univartois/butinfo/r304/flatcraft/model/craft/furnacerules.txt");
        this.furnace = parser2.build();
        
        
        
        
        // On démarre l'animation du jeu.
        animation.start();
    }

    /**
     * Crée la carte du jeu.
     *
     * @return La carte du jeu créée.
     */
    private GameMap createMap() {
        int hauteur = this.height / 16;
        int largeur = this.width / 16;
        
        return generate.createMapGen(hauteur, largeur, cellFactory);
        
        
    }

    /**
     * Indique à cette partie de Flatcraft qu'une nouvelle heure s'est écoulée
     * (dans le jeu).
     */
    void oneHour() {
        time.set((time.get() + 1) % 24);
    }

    /**
     * Fait se déplacer le joueur vers le haut.
     */
    public void moveUp() {
    	
    }
    

    /**
     * Fait se déplacer le joueur vers le bas.
     */
    public void moveDown() {
    	
    }

    /**
     * Fait se déplacer le joueur vers la gauche.
     */
    public void moveLeft() {
    	player.setHorizontalSpeed(-150);
    	move(player);
    }

    /**
     * Fait se déplacer le joueur vers la droite.
     */
    public void moveRight() {
    	player.setHorizontalSpeed(150);
    	move(player);
    }

    /**
     * Déplace un objet mobile en tenant compte de la gravité.
     *
     * @param movable L'objet à déplacer.
     */
    private void move(IMovable movable) {
        Cell currentCell = getCellOf(movable);
        for (int row = currentCell.getRow() + 1; row < map.getHeight(); row++) {
            Cell below = map.getAt(row, currentCell.getColumn());
            if (!below.move(movable)) {
                break;
            }
        }
    }

    /**
     * Interrompt le déplacement du joueur.
     */
    public void stopMoving() {
    	player.setHorizontalSpeed(0);
    	player.setVerticalSpeed(0);
    }

    /**
     * Fait sauter le joueur.
     */
    public void jump() {
        // TODO Cette méthode vous sera fournie ultérieurement.
    }

    /**
     * Fait creuser le joueur vers le haut.
     */
    public void digUp() {
        // TODO Nous reviendrons plus tard sur cette méthode.
    }

    /**
     * Fait creuser le joueur vers le bas.
     */
    public void digDown() {
        Cell x = getCellOf(player);
        int hori = x.getRow();
        int vert = x.getColumn();
        int cible = hori +1;
        Cell y = map.getAt(cible, vert);
        dig(y);
        move(player);
        
        
        
    }

    /**
     * Fait creuser le joueur vers la gauche.
     */
    public void digLeft() {
    	Cell x = getCellOf(player);
        int hori = x.getRow();
        int vert = x.getColumn();
        int cible = vert -1;
        Cell y = map.getAt(hori, cible);
        dig(y);
        move(player);
    }

    /**
     * Fait creuser le joueur vers la droite.
     */
    public void digRight() {
    	Cell x = getCellOf(player);
        int hori = x.getRow();
        int vert = x.getColumn();
        int cible = vert +1;
        Cell y = map.getAt(hori, cible);
        dig(y);
        move(player);
    }

    /**
     * Creuse la cellule donnée pour en extraire une ressource.
     *
     * @param toDig La cellule sur laquelle creuser.
     */
    private void dig(Cell toDig) {
        if (toDig.dig(player)) {
        	toDig.replaceBy(cellFactory.createSky());
        }
        	
        
    }

    /**
     * Récupére la cellule correspondant à la position d'un objet mobile.
     * Il s'agit de la cellule sur laquelle l'objet en question occupe le plus de place.
     *
     * @param movable L'objet mobile dont la cellule doit être récupérée.
     *
     * @return La cellule occupée par l'objet mobile.
     */
    private Cell getCellOf(IMovable movable) {
        // On commence par récupérer la position du centre de l'objet.
        int midX = movable.getX() + (movable.getWidth() / 2);
        int midY = movable.getY() + (movable.getHeight() / 2);

        // On traduit cette position en position dans la carte.
        int row = midY / spriteStore.getSpriteSize();
        int column = midX / spriteStore.getSpriteSize();

        // On récupère enfin la cellule à cette position dans la carte.
        return map.getAt(row, column);
    }


	public CellFactory getCellFactory() {
		return cellFactory;
	}
    
    
    /**
     * Crée une nouvelle ressource à l'aide d'un ensemble de ressources, en suivant les
     * règles de la table de craft.
     *
     * @param inputResources Les ressources déposées sur la table de craft.
     *
     * @return La ressource produite.
     */
    public Resource craft(Resource[][] inputResources) {
        
        String res = "";
        for (Resource[] resources : inputResources) {
            for(Resource resource : resources) {
                res += resource.getInternalName() + "_";
            }
        }
        res = res.substring(0, res.length() - 1);
        
        
        String nomItemCraft = "";
        int quantite = 0;
        for(CraftAndFurnace craftUnite : craft.getListCraft()) {
            if(craftUnite.getRule().equals(res)) {
                nomItemCraft = craftUnite.getProduct();
                quantite = craftUnite.getQuantity();
                break;
            }
                
               
        }
            
        if (quantite != 0) {
            Sprite spriteItem = MAPCRAFTSPRITE.get(nomItemCraft);
            String nomExterne = MAPCRAFTNAME.get(nomItemCraft);
            
            return new Resource(new ResourceInInventory(spriteItem,nomExterne),ToolType.NO_TOOL,new EtatResourceUnbreakable(cellFactory));
        } else {
            controller.displayError("Erreur, Il n'existe pas de craft.");
            return null;
        }
    }

    /**
     * Crée une nouvelle ressource à l'aide d'un combustible et d'une ressource, en suivant les
     * règles du fourneau.
     *
     * @param fuel Le matériau combustible utilisé dans le fourneau.
     * @param resource La ressource à transformer.
     *
     * @return La ressource produite.
     */
    public Resource cook(Resource fuel, Resource resource) {
        
        String res = resource.getInternalName();
        
        String nomItemCook = "";
        int quantite = 0;
        for(CraftAndFurnace cookUnite : furnace.getListCraft()) {
            if(cookUnite.getRule().equals(res)) {
                nomItemCook = cookUnite.getProduct();
                quantite = cookUnite.getQuantity();
                break;
            }
                
               
        }
        if (quantite != 0) {
            Sprite spriteItem = MAPCOOKSPRITE.get(nomItemCook);
            String nomExterne = MAPCOOKNAME.get(nomItemCook);
            
            return new Resource(new ResourceInInventory(spriteItem,nomExterne),ToolType.NO_TOOL,new EtatResourceUnbreakable(cellFactory));
        } else {
            controller.displayError("Erreur, Il n'existe pas de cuisson pour cet item.");
            return null;
        }
    }

}
