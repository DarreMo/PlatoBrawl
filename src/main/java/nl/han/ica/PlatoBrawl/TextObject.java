package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class TextObject extends GameObject {
	
	private PlatoBrawl world;
	private Player player;

    public TextObject(PlatoBrawl world, Player player) {
    	this.world = world;
    	this.player = player;
    }

    @Override
    public void update() {
    }

    /**
     * Draw
     * Tekent de huidige ronde en de hitpoints van Player
     */
    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.text("Round: " + world.round, getX() + 50, getY() + 20);
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.text("Hitpoints: " + (int) player.getHitpoints(), 900, getY() + 20);
    }
}