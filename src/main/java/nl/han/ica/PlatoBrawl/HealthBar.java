package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Timon
 */
public class HealthBar extends AnimatedSpriteObject {
	
	private Enemy enemy;

	public HealthBar (Enemy enemy) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/HealthBar.png"), 3);
		this.enemy = enemy;
	}
	
	
	/**
     * De healthbar beweegt mee met de Enemy en verandert met zijn hitpoints mee
     */
	@Override
	public void update() {
		setX(enemy.getCenterX() - (getWidth()/2));
    	setY(enemy.getY() - 50);
    	if (enemy.getHitpoints() >= 8) {
    		setCurrentFrameIndex(0);
    	}
    	if (enemy.getHitpoints() < 8 && enemy.getHitpoints() > 3) {
    		setCurrentFrameIndex(1);
    	}
    	if (enemy.getHitpoints() <= 3) {
    		setCurrentFrameIndex(2);
    	}
	}

}