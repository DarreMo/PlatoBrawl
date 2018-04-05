package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class RestartButton extends SpriteObject {
	
	PlatoBrawl world;
	
	public RestartButton(PlatoBrawl world) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/RestartButton.png"));
		this.world = world;
	}

	@Override
	public void update() {
		
	}
	
	
	/**
     * keyPressed
     * Wanneer op 'r' wordt gedrukt begint een nieuwe game
     */
    public void keyPressed(int keyCode, char key) {
		if (key == 'r') {
			world.deleteGameObject(this);
			world.round = 0;
			world.enemyAmount = 0;
			world.setupGame();
		}
	}

}