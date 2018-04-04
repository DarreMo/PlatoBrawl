package nl.han.ica.PlatoBrawl;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class SuperBullet extends Bullet implements ICollidableWithGameObjects {

	public SuperBullet (Player player, PlatoBrawl world, float x, int f) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/SuperBullet.png"), 2, world, 25);
		setxSpeed(x);
		setCurrentFrameIndex(f);
	}
	
	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject go : collidedGameObjects) {
            if (go instanceof Swordfish) {
            	try {
                    world.deleteGameObject(this);
                    ((Swordfish) go).superBulletHit();
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
	}


	@Override
	public void update() {	}
	
}