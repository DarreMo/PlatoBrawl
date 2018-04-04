package nl.han.ica.PlatoBrawl;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class PowerUp extends SpriteObject implements ICollidableWithGameObjects{
	
	protected PlatoBrawl world;
	
	public PowerUp(Sprite sprite, PlatoBrawl world) {
		super(sprite);
		this.world = world;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
        	if (go instanceof Player) {
            		try { 
            			world.deleteGameObject(this);
            		} catch (TileNotFoundException e) {
            			e.printStackTrace();
            		}
            	}
            }
		
	}


}
