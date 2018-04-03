package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Ralph Niels
 * Wordt gebruikt om een tekst te kunnen afbeelden
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

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.text(world.round, getX() + 20, getY() + 20);
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.text((int) player.getHitpoints(), 610, getY() + 20);
    }
}