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
public class PlayerBullet extends Bullet implements ICollidableWithGameObjects {

	public PlayerBullet (Player player, PlatoBrawl world, float x, int f) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Bullet.png"), 2, world, 25);
		setxSpeed(x);
		setCurrentFrameIndex(f);
	}
	
	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject go : collidedGameObjects) {
            if (go instanceof Enemy) {
            	try {
                    world.deleteGameObject(this);
                    ((Enemy) go).bulletHit();
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
	}


	@Override
	public void update() {	}
}