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

package fr.univartois.butinfo.r304.flatcraft;

import java.io.IOException;
import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.controller.FlatcraftController;
import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.map.Arbre;
import fr.univartois.butinfo.r304.flatcraft.model.map.IGenerate;
import fr.univartois.butinfo.r304.flatcraft.model.map.MapGenerator;
import fr.univartois.butinfo.r304.flatcraft.model.map.Terrils;
import fr.univartois.butinfo.r304.flatcraft.model.map.choose_sprite.ChooseSprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe Flatcraft permet de lancer l'implantation en JavaFX du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Flatcraft extends Application {
	
	/**
	 * L'attribut game...
	 */
	private FlatcraftGame game;

    /**
     * La largeur (en pixels) de la fenêtre affichant le jeu.
     */
    private static final int GAME_WIDTH = 1280;

    /**
     * La hauteur (en pixels) de la fenêtre affichant le jeu.
     */
    private static final int GAME_HEIGHT = 720;
    
    /**
     * L'attribut RANDOM...
     */
    private static final Random RANDOM = new Random();
    /**
     * Le nombre de fois que la carte se "répète" horizontalement.
     * Cela permet d'avoir une carte plus grande que la fenêtre.
     */
    private static final int MAP_REPEAT = 50;

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws IOException {
        // On commence par charger la vue et son contrôleur.
    	
    	int typeTerrils = RANDOM.nextInt(10);
    	int typeArbre = RANDOM.nextInt(3,9);
    	int nbArbre = RANDOM.nextInt(50,150);
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/flatcraft/view/flatcraft.fxml"));
        Parent viewContent = fxmlLoader.load();
        FlatcraftController controller = fxmlLoader.getController();
        controller.setStage(stage);

        // On crée ensuite le jeu, que l'on lie au contrôleur.
        game = new FlatcraftGame(GAME_WIDTH, GAME_HEIGHT, MAP_REPEAT, SpriteStore.getSpriteStore(), ChooseSprite.getChooseSprite());
        
        
        IGenerate map = MapGenerator.getMapGenerator();
        
        map = new Terrils(map,typeTerrils);
        map = new Arbre(map,typeArbre,nbArbre);
        
        game.setGenerate(map);
        controller.setGame(game);
        game.setController(controller);
        game.prepare();

        // On peut maintenant afficher la scène et la fenêtre.
        Scene scene = new Scene(viewContent, GAME_WIDTH, GAME_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("FlatcraftFX");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Exécute l'application JavaFX du jeu Flatcraft.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     *
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        
    	launch();
    }

    /**
     * Donne l'attribut game de cette instance de Flatcraft.
     *
     * @return L'attribut game de cette instance de Flatcraft.
     */
    public FlatcraftGame getGame() {
        return game;
    }

}
