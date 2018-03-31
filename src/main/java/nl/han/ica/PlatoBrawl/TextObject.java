package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Ralph Niels
 * Wordt gebruikt om een tekst te kunnen afbeelden
 */
public class TextObject extends GameObject {

    private Swordfish swordfish;

    public TextObject(Swordfish swordfish) {
        this.swordfish = swordfish;
    }    

    @Override
    public void update() {
    }
    
    public int getSwordfishHitpoints() {
    	return swordfish.getHitpoints();
    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.text(getSwordfishHitpoints(), getX() + 20, getY() + 20);
    }
}