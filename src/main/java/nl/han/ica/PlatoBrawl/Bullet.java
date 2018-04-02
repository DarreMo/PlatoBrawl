package nl.han.ica.PlatoBrawl;
import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Bullet extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private final PlatoBrawl world;
	private final int size = 25;
	
	public Bullet (PlatoBrawl world, float x, int f) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Bullet.png"), 2);
		this.world = world;
		setxSpeed(x);
		setCurrentFrameIndex(f);
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
		if (getX() <= 0 - size) {
            world.deleteGameObject(this);
        }
		
	}
	
	
}