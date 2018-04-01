package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class HealthBar extends AnimatedSpriteObject {

	public HealthBar (float x, float y) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 2);
		this.x = x;
		this.y = y;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
