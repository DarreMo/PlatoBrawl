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
	
	
	/**
     * ObjectCollision
     * Wanneer een SuperBullet collide met een Enemy verliest deze 4 hitpoints
     */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject go : collidedGameObjects) {
            if (go instanceof Enemy) {
            	try {
                    world.deleteGameObject(this);
                    ((Enemy) go).superBulletHit();
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
	}


	@Override
	public void update() {	}
	
}