package nl.han.ica.PlatoBrawl;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class EnemyBullet extends Bullet implements ICollidableWithGameObjects {

	Swordfish swordfish;
	
	public EnemyBullet (Swordfish swordfish, PlatoBrawl world, float x, int f) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/EnemyBullet.png"), 2, world, 25);
		setxSpeed(x);
		setCurrentFrameIndex(f);
		this.swordfish = swordfish;
	}
	
	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject go : collidedGameObjects) {
            if (go instanceof Player) {
            	try {
                    world.deleteGameObject(this);
                    ((Player) go).bulletHit();
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	@Override
	public void update() {
		if (swordfish.getAlive() == false) {
            world.deleteGameObject(this);
		}
	}
	
}

