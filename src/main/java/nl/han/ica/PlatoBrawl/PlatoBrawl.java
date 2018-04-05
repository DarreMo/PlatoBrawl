package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.PlatoBrawl.tiles.BoardTiles;
import nl.han.ica.PlatoBrawl.TextObject;
import nl.han.ica.PlatoBrawl.Enemy;
import processing.core.PApplet;

/**
 * Created by timon on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class PlatoBrawl extends GameEngine {

    private Sound backgroundSound;

    private TextObject dashboardText; 
    private Enemy enemy;
    private Player player;
    public int round; 
    public int enemyAmount;

    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.PlatoBrawl.PlatoBrawl"});
    }

    @Override
    public void setupGame() {

        int worldWidth=1280;
        int worldHeight=720;
        
        createObjects();
        createDashboard(worldWidth, 100);
        initializeTileMap();

        createViewWithoutViewport(worldWidth, worldHeight);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/background.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createViewWithViewport(int worldWidth,int worldHeight,int screenWidth,int screenHeight,float zoomFactor) {
        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int)Math.ceil(screenWidth/zoomFactor),(int)Math.ceil(screenHeight/zoomFactor),0,0);
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth,worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/background.jpg"));
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, 100, 100);
        round++;
		enemy = new Enemy(this, player);
        addGameObject(enemy, 200, 200);
        enemyAmount++;
    }

    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject(this, player);
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/board.png");
        TileType<BoardTiles> boardTileType = new TileType<>(BoardTiles.class, boardsSprite);

        TileType[] tileTypes = { boardTileType };
        int tileSize=40;
        int tilesMap[][]={
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0, 0, 0,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{ -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
        		{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
    }

    /**
     * Wordt gedraait wanneer volgende ronde start
     */    
    public void nextRound() {
    	round++;
    	player.newRound ();
    	if (round == 2 ) {
    		PowerUp pu = new HitpointsUp(this);
    		addGameObject(pu, getWidth()/2 - pu.getWidth()/2, 150);
    	}
    	if (round == 4) {
    		BulletUp bu = new BulletUp(this);
    		addGameObject(bu, getWidth()/2 - bu.getWidth()/2, 150);
    	}
    }

}