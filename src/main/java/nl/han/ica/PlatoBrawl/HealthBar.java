package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class HealthBar extends AnimatedSpriteObject {
	
	private Enemy enemy;

	public HealthBar (Enemy enemy) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 2);
		this.enemy = enemy;
	}

	@Override
	public void update() {
		setX(enemy.getCenterX() - (getWidth()/2));
    	setY(enemy.getY() - 50);
    	if (enemy.getHitpoints() >= 10) {
    		setCurrentFrameIndex(0);
    	}
    	if (enemy.getHitpoints() < 10) {
    		setCurrentFrameIndex(1);
    	}
	}


}
