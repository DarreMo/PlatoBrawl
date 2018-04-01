package nl.han.ica.PlatoBrawl;
import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Bullet extends SpriteObject implements ICollidableWithGameObjects {

    private final PlatoBrawl world;
	
	
	public Bullet (PlatoBrawl world, float x) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"));
		this.world = world;
		setxSpeed(x);
	}

	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject go : collidedGameObjects) {
            if (go instanceof Swordfish) {
            	try {
                    world.deleteGameObject(this);
                    ((Swordfish) go).swordfishHit();
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
	}


	@Override
	public void update() {
		if (getX() >= world.getWidth()) {
            world.deleteGameObject(this);
        }
		if (getX() <= 0) {
            world.deleteGameObject(this);
        }
		
	}
	
	
}