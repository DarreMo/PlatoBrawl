package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class HitpointsUp extends PowerUp implements ICollidableWithGameObjects{
	
	
	public HitpointsUp(PlatoBrawl world) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/boards.jpg"), world);
	}

	@Override
	public void update() {
		
	}

}