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


import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


import java.util.ArrayList;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;
import fr.univartois.butinfo.r304.flatcraft.model.craft.CraftAndFurnace;
import fr.univartois.butinfo.r304.flatcraft.model.craft.CraftFurnaceObject;
import fr.univartois.butinfo.r304.flatcraft.model.craft.RuleParser;
import fr.univartois.butinfo.r304.flatcraft.model.map.GameMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.GenerateCell;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.choose_sprite.ChooseSprite;
import fr.univartois.butinfo.r304.flatcraft.model.map.choose_sprite.ChooseSpriteEnd;
import fr.univartois.butinfo.r304.flatcraft.model.map.choose_sprite.ChooseSpriteNether;
import fr.univartois.butinfo.r304.flatcraft.model.movables.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource2;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResource5;
import fr.univartois.butinfo.r304.flatcraft.model.resources.EtatResourceUnbreakable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.IResource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.EtatNotFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.fuel.IResourceFuel;
import fr.univartois.butinfo.r304.flatcraft.model.resources.state_inventory.ResourceInInventory;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.MultipleResource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Portal;
import fr.univartois.butinfo.r304.flatcraft.model.resources.PortalType;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
     * Le nombre de fois que la carte se "répète" horizontalement.
     */
    private final int mapRepeat;

    /**
     * Le contrôleur de l'application.
     */
    private IFlatcraftController controller;
    
    /**
     * L'attribut generate...
     */
    private IGenerate generate;

    /**
     * L'instance e {@link ISpriteStore} utilisée pour créer les sprites du jeu.
     */
    private ISpriteStore spriteStore;
    
    /**
     * L'attribut liste des Crafts
     */
    private CraftFurnaceObject craft;
    
    /**
     * L'attribut liste des Cuissons
     */
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
     * La position à gauche de la carte dans la fenêtre.
     * Celle-ci change lorsque l'utilisateur se déplace horizontalement.
     */
    private IntegerProperty leftAnchor = new SimpleIntegerProperty(0);

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
     * L'attribut ENDPORTAL...
     */
    private static final String ENDPORTAL = "endportal";
    
    /**
     * L'attribut NETHERPORTAL...
     */
    private static final String NETHERPORTAL = "netherportal";
    
    /**
     * L'attribut STEELPICK...
     */
    private static final String STEELPICK = "steelpick";
    /**
     * L'attribut STONEAXE...
     */
    private static final String STONEAXE = "stoneaxe";
    /**
     * L'attribut STONEPICK...
     */
    private static final String STONEPICK = "stonepick";
    /**
     * L'attribut WOODAXE...
     */
    private static final String WOODAXE = "woodaxe";
    /**
     * L'attribut WOODPICK...
     */
    private static final String WOODPICK = "woodpick";

    /**
     * La dernière direction suivie par le joueur.
     * Elle est stockée sous la forme d'un entier, afin d'indiquer s'il avance ou s'il
     * recule.
     */
    private int lastDirection = 1;

    /**
     * L'iterateur permettant de parcourir les ressources contenues dans l'inventaire du
     * joueur.
     */
    private Iterator<Inventoriable> inventoryIterator;

    /**
     * La liste des objets mobiles du jeu.
     */
    private List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation simulant le temps qui passe dans le jeu.
     */
    private FlatcraftAnimation animation = new FlatcraftAnimation(this, movableObjects);
    
    /**
     * L'attribut liste des mondes
     */
    private List<GameMap> worldList = new ArrayList<>();
    
    /**
     * L'attribut MAPCRAFTSPRITE...
     */
    private static final Map<String, Sprite> MAPCRAFTSPRITE = Map.of("wood",SpriteStore.getSpriteStore().getSprite("default_wood"),
            "stick",SpriteStore.getSpriteStore().getSprite("default_stick"),
            WOODPICK,SpriteStore.getSpriteStore().getSprite("default_tool_woodpick"),
            WOODAXE,SpriteStore.getSpriteStore().getSprite("default_tool_woodaxe"),
            STONEPICK,SpriteStore.getSpriteStore().getSprite("default_tool_stonepick"),
            STONEAXE,SpriteStore.getSpriteStore().getSprite("default_tool_stoneaxe"),
            STEELPICK,SpriteStore.getSpriteStore().getSprite("default_tool_steelpick"),
            NETHERPORTAL,SpriteStore.getSpriteStore().getSprite("default_netherportal"),
            ENDPORTAL,SpriteStore.getSpriteStore().getSprite("default_endportal"));
    
    /**
     * L'attribut MAPCRAFTNAME...
     */
    private static final Map<String, String> MAPCRAFTNAME = Map.of(WOODPICK,"Wood Pickaxe",
    		WOODAXE,"Wood Axe",
            STONEPICK,"Stone Pickaxe",
            STONEAXE,"Stone Axe",
            STEELPICK,"Steel Pickaxe",
            NETHERPORTAL,"Nether Portal",
            ENDPORTAL,"End Portal");
    
    /**
     * L'attribut MAPCRAFTTOOLTYPE...
     */
    private static final Map<String, ToolType> MAPCRAFTTOOLTYPE = Map.of(WOODPICK,ToolType.MEDIUM_TOOL,
    		WOODAXE,ToolType.MEDIUM_TOOL,
            STONEPICK,ToolType.HARD_TOOL,
            STONEAXE,ToolType.HARD_TOOL,
            STEELPICK,ToolType.HARD_TOOL);
    
    /**
     * L'attribut mapcrafthardness...
     */
    private final Map<String, IResource> mapcrafthardness = Map.of("wood",new EtatResource2(cellFactory));
    
    /**
     * L'attribut MAPCRAFTFUEL...
     */
    private static final Map<String, IResourceFuel> MAPCRAFTFUEL = Map.of("wood",new EtatFuel(),
    			"stick",new EtatFuel(),
    			WOODPICK,new EtatFuel(),
    			WOODAXE,new EtatFuel());
    
   
    /**
     * L'attribut MAPCOOKSPRITE...
     */
    private static final Map<String, Sprite> MAPCOOKSPRITE = Map.of("gold_lingot",SpriteStore.getSpriteStore().getSprite("default_gold_ingot"),
            "steel_lingot",SpriteStore.getSpriteStore().getSprite("default_steel_ingot"),
            "copper_lingot",SpriteStore.getSpriteStore().getSprite("default_copper_ingot"));
    
    /**
     * L'attribut MAPCOOKNAME...
     */
    private static final Map<String, String> MAPCOOKNAME = Map.of("gold_lingot","Gold Lingot",
            "steel_lingot","Steel Lingot",
            "copper_lingot","Copper Lingot");

    /**
     * Crée une nouvelle instance de FlatcraftGame.
     *
     * @param width La largeur de la carte du jeu (en pixels).
     * @param height La hauteur de la carte du jeu (en pixels).
     * @param mapRepeat Le nombre de fois que la carte se "répète" horizontalement.
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory La fabrique permettant de créer les cellules du jeux.
     */
    public FlatcraftGame(int width, int height, int mapRepeat, ISpriteStore spriteStore,
            CellFactory factory) {
        this.width = width;
        this.height = height;
        this.mapRepeat = mapRepeat;
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
        return width * mapRepeat;
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
    
    /**
     * Set la map générée
     * 
     * @param generate map générée
     */
    public void setGenerate(IGenerate generate) {
        this.generate = generate;
    }
    
    /**
     * Prépare la partie de Flatcraft avant qu'elle ne démarre.
     */
    public void prepare(){
        
        ChooseSpriteEnd spriteEnd = ChooseSpriteEnd.getChooseSpriteEnd();
        spriteEnd.setSpriteStore(spriteStore);
        ChooseSpriteNether spriteNether = ChooseSpriteNether.getChooseSpriteNether();
        spriteNether.setSpriteStore(spriteStore);
        
        GameMap overworld = createMap(cellFactory);
    	GameMap end = createMap(spriteEnd);
    	GameMap nether = createMap(spriteNether);
    	worldList.add(overworld);
        worldList.add(end);
        worldList.add(nether);
        
    	// On crée la carte du jeu.
        setMap(worldList.get(0),cellFactory);
        
        controller.prepare(getMap());

        player = new Player(this, 0, 19*16, spriteStore.getSprite("player"));
        movableObjects.add(player);
        controller.addMovable(player);
        
        
        controller.bindLeftAnchor(leftAnchor);
        
        

        controller.bindTime(this.time);
        controller.bindLevel(this.level);
        controller.bindInventory(this.getPlayer().getInventory());
        controller.bindHealth(player.getHealthPoints());
        controller.bindXP(player.getXpPoints());
        
        RuleParser parser1 = new RuleParser("craftrules.txt");
        try {
            parser1.parse();
        } catch (IOException e) {

            throw new UncheckedIOException(e);
        }
        this.craft = parser1.build();
        
        RuleParser parser2 = new RuleParser("furnacerules.txt");
        try {
            parser2.parse();
        } catch (IOException e) {
        	throw new UncheckedIOException(e);
        }
        this.furnace = parser2.build();
        
        
        
        
        // On démarre l'animation du jeu.
        animation.start();
        
        Inventoriable woodpick = new Resource(new ResourceInInventory(SpriteStore.getSpriteStore().getSprite("default_tool_woodpick"),WOODPICK),ToolType.MEDIUM_TOOL,new EtatResourceUnbreakable(ChooseSprite.getChooseSprite()), new EtatNotFuel());
        Inventoriable portal = new Resource(new ResourceInInventory(SpriteStore.getSpriteStore().getSprite("default_netherportal"), NETHERPORTAL),ToolType.NO_TOOL,new EtatResource5(ChooseSprite.getChooseSprite()),new EtatNotFuel());
        Inventoriable portal2 = new Resource(new ResourceInInventory(SpriteStore.getSpriteStore().getSprite("default_endportal"), ENDPORTAL),ToolType.NO_TOOL,new EtatResource5(ChooseSprite.getChooseSprite()),new EtatNotFuel());
        player.setWearItem(woodpick);
        player.addItem(woodpick);
        player.addItem(portal);
        player.addItem(portal2);
    }

    /**
     * Récuperer la map
     * 
     * @return map
     */
    public GameMap getMap() {
		return map;
	}

	/**
	 * Récuperer joueur
	 * 
	 * @return Joueur
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Set le joueur
	 * 
	 * @param player joueur
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
     * Crée la carte du jeu.
	 * @param cellFactory2 Cellfactory
     *
     * @return La carte du jeu créée.
     */
    private GameMap createMap(CellFactory cellFactory2) {
        int hauteur = getHeight() / 16;
        int largeur = getWidth() / 16;
        return generate.createMapGen(hauteur, largeur, cellFactory2);
        
        
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
    	throw new UnsupportedOperationException();
    }
    

    /**
     * Fait se déplacer le joueur vers le bas.
     */
    public void moveDown() {
    	throw new UnsupportedOperationException();
    }

    /**
     * Fait se déplacer le joueur vers la gauche.
     */
    public void moveLeft() {
    	player.setHorizontalSpeed(-150);
    	move(player);
        lastDirection = -1;
    }

    /**
     * Fait se déplacer le joueur vers la droite.
     */
    public void moveRight() {
    	player.setHorizontalSpeed(150);
    	move(player);
        lastDirection = 1;
    }

    /**
     * Déplace un objet mobile en tenant compte de la gravité.
     *
     * @param movable L'objet à déplacer.
     */
    private void move(IMovable movable) {
        // On applique la gravité.
    	
        Cell currentCell = getCellOf(movable);
        for (int row = currentCell.getRow() + 1; row < getMap().getHeight(); row++) {
            Cell below = getMap().getAt(row, currentCell.getColumn());
            if (!below.move(movable)) {
                break;
            }
        }

        // On positionne la carte pour afficher la section où se trouve le joueur.
        int middlePosition = player.getX() + player.getWidth() / 2;
        int mapSection = middlePosition / width;
        leftAnchor.set(-mapSection * width);
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
    	throw new UnsupportedOperationException();
    }

    /**
     * Fait creuser le joueur vers le haut.
     */
    public void digUp() {
    	throw new UnsupportedOperationException();
    }

    /**
     * Fait creuser le joueur vers le bas.
     */
    public void digDown() {
        Cell x = getCellOf(player);
        int hori = x.getRow();
        int vert = x.getColumn();
        int cible = hori +1;
        Cell y = getMap().getAt(cible, vert);
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
        Cell y = getMap().getAt(hori, cible);
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
        Cell y = getMap().getAt(hori, cible);
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
        return getCellAt(midX, midY);
    }

    /**
     * Donne la cellule à la position donnée sur la carte.
     *
     * @param x La position en x de la cellule.
     * @param y La position en y de la cellule.
     *
     * @return La cellule à la position donnée.
     */
    public Cell getCellAt(int x, int y) {
        // On traduit cette position en position dans la carte.
        int row = y / spriteStore.getSpriteSize();
        int column = x / spriteStore.getSpriteSize();

        // On récupère enfin la cellule à cette position dans la carte.
        return getMap().getAt(row, column);
    }


	/**
	 * Récuperer CellFactory
	 * 
	 * @return CellFactory
	 */
	public CellFactory getCellFactory() {
		return cellFactory;
	}
    
    
    /**
     * Récupére la cellule correspondant à la prochaine position d'un objet mobile.
     * Il s'agit de la cellule voisine de celle sur laquelle l'objet en question occupe le
     * plus de place, en suivant la dernière direction suivie par joueur.
     *
     * @param movable L'objet mobile dont la prochaine cellule doit être récupérée.
     *
     * @return La prochaine cellule occupée par l'objet mobile.
     */
    private Optional<Cell> getNextCellOf(IMovable movable) {
        // On commence par récupérer la position du centre de l'objet.
        int midX = movable.getX() + (movable.getWidth() / 2);
        int midY = movable.getY() + (movable.getHeight() / 2);

        // On traduit cette position en position dans la carte.
        int row = midY / spriteStore.getSpriteSize();
        int column = midX / spriteStore.getSpriteSize() + lastDirection;

        // On récupère enfin la cellule à cette position dans la carte.
        if (column < map.getWidth()) {
            return Optional.of(map.getAt(row, column));
        }

        return Optional.empty();
    }

    /**
     * Crée une nouvelle ressource à l'aide d'un ensemble de ressources, en suivant les
     * règles de la table de craft.
     *
     * @param inputResources Les ressources déposées sur la table de craft.
     *
     * @return La ressource produite.
     */
    public Inventoriable craft(Inventoriable[][] inputResources) {
        
        StringBuilder res = new StringBuilder();
        for (Inventoriable[] resources : inputResources) {
            for(Inventoriable resource : resources) {
                if(resource == null) {
                    res.append("empty_");
                } else {
                    res.append(resource.getInternalName() + "_");
                }
            }
        }
        res = res.deleteCharAt(res.length()-1);    
        
        String nomItemCraft = "";
        int quantite = 0;
        for(CraftAndFurnace craftUnite : craft.getListCraft()) {
            if(craftUnite.getRule().equals(res.toString())) {
                nomItemCraft = craftUnite.getProduct();
                quantite = craftUnite.getQuantity();
                break;
            }
        }
            
        if (quantite != 0) {
            Sprite spriteItem;
            String nomExterne = nomItemCraft;
            ToolType toolType = ToolType.NO_TOOL;
            spriteItem = MAPCRAFTSPRITE.get(nomItemCraft);
            
            if(MAPCRAFTNAME.containsKey(nomItemCraft)) {
                nomExterne = MAPCRAFTNAME.get(nomItemCraft);
            }
            
            if(MAPCRAFTTOOLTYPE.containsKey(nomItemCraft)) {
            	toolType = MAPCRAFTTOOLTYPE.get(nomItemCraft);
            }
            
            IResource hardness = new EtatResourceUnbreakable(cellFactory);
            if(mapcrafthardness.containsKey(nomItemCraft)) {
            	hardness = mapcrafthardness.get(nomItemCraft);
            }
            
            IResourceFuel fuel = new EtatNotFuel();
            if(MAPCRAFTFUEL.containsKey(nomItemCraft)) {
            	fuel = MAPCRAFTFUEL.get(nomItemCraft);
            }
            if (quantite == 1 ) {
            	return new Resource(new ResourceInInventory(spriteItem,nomExterne),toolType,hardness, fuel);
            }
            else {
            	return new MultipleResource(new Resource(new ResourceInInventory(spriteItem,nomExterne),toolType,hardness, fuel), quantite);
            }
        } else {
            controller.displayError("Erreur, Il n'existe pas de craft.");
            return null;
        }
    }

    /**
     * Crée une nouvelle ressource à l'aide d'un combustible et d'une ressource, en
     * suivant les règles du fourneau.
     *
     * @param fuel Le matériau combustible utilisé dans le fourneau.
     * @param resource La ressource à transformer.
     *
     * @return La ressource produite.
     */
    public Inventoriable cook(Inventoriable fuel, Inventoriable resource) {
        
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
            String nomExterne;
            Sprite spriteItem = MAPCOOKSPRITE.get(nomItemCook);
            if(MAPCOOKNAME.containsKey(nomItemCook)) {
                nomExterne = MAPCOOKNAME.get(nomItemCook);
            } else {
                nomExterne = nomItemCook;
            }
            
            
            return fuel.getFuel().combustible(new Resource(new ResourceInInventory(spriteItem,nomExterne),ToolType.NO_TOOL,new EtatResourceUnbreakable(cellFactory), new EtatFuel()));
        } else {
            controller.displayError("Erreur, Il n'existe pas de cuisson pour cet item.");
            return null;
        }
    }

	/**
	 * Changer la map
	 * 
	 * @param map variable map
	 * @param cellFactory2 cellFactory
	 */
	private void setMap(GameMap map, CellFactory cellFactory2) {
		this.map = map;
		this.cellFactory = cellFactory2;
	}
	
	/**
	 * Changer de dimension
	 * 
	 * @param portalType Type du Portail
	 */
	public void changeDimension(PortalType portalType) {
	    switch (portalType) {
	        case END:
	            setMap(worldList.get(1),ChooseSpriteEnd.getChooseSpriteEnd());
	            break;
	        case NETHER:
	            setMap(worldList.get(2),ChooseSpriteNether.getChooseSpriteNether()); 
	            break;
	    }
	    controller.prepare(getMap());
	}
	
    /**
     * Dépose sur la carte la ressource que le joueur a actuellement en main.
     */
	public void dropResource() {
	    Optional<Cell> next = getNextCellOf(player);
	    if (next.isEmpty()) {
	        return;
	    }

	    Cell target = next.get();
	    
	    Inventoriable inHand = player.getWearItem();
	    if (target.setResource(inHand)) {
	    	if ((inHand.getName().equals(ENDPORTAL) || inHand.getName().equals(NETHERPORTAL))) {
		        PortalType portalType = inHand.getName().equals(ENDPORTAL) ? PortalType.END : PortalType.NETHER;
		        GenerateCell portalCell = (GenerateCell) target;
		        portalCell.setPortal(new Portal(portalCell, portalType));
		    }
	        player.removeItem(inHand);
	        switchResource();
	    }
	    
	}

    /**
     * Modifie la ressource que l'utilisateur a actuellement en main.
     * C'est la prochaine ressource dans l'inventaire qui est choisie.
     */
    public void switchResource() {
        if ((inventoryIterator == null) || (!inventoryIterator.hasNext())) {
        	inventoryIterator = copyInventoryKeys().iterator();
        }

        Inventoriable inHand = inventoryIterator.next();
        player.setWearItem(inHand);
    }
    
    /**
     * Permet de ne pas faire buguer l'itérator
     * 
     * @return iterator list
     */
    private List<Inventoriable> copyInventoryKeys() {
        return new ArrayList<>(player.getInventory().keySet());
    }

    /**
     * Exécute l'action associée à la ressource située sur la cellule voisine de celle du
     * joueur.
     */
    public void executeResource() {
        Optional<Cell> next = getNextCellOf(player);
        if (next.isPresent()) {
            Cell cell = next.get();
            cell.execute(this);
        }
    }

}
