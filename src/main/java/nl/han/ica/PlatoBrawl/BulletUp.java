package nl.han.ica.PlatoBrawl;


import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class BulletUp extends PowerUp implements ICollidableWithGameObjects{
	
	
	public BulletUp(PlatoBrawl world) {
		super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/boards.jpg"), world);
	}

	@Override
	public void update() {
		
	}

	
}
