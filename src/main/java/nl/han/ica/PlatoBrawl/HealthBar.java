package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Timon
 */
public class HealthBar extends AnimatedSpriteObject {
	
	private Swordfish swordfish;

	public HealthBar (Swordfish swordfish) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 2);
		this.swordfish = swordfish;
	}

	@Override
	public void update() {
		setX(swordfish.getCenterX() - (getWidth()/2));
    	setY(swordfish.getY() - 50);
    	if (swordfish.getHitpoints() >= 10) {
    		setCurrentFrameIndex(0);
    	}
    	if (swordfish.getHitpoints() < 10) {
    		setCurrentFrameIndex(1);
    	}
	}


}
