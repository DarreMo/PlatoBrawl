package nl.han.ica.PlatoBrawl;


import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Bullet extends AnimatedSpriteObject {
	
	protected PlatoBrawl world;
	protected int size;
	
    public Bullet(Sprite sprite, int totalFrames, PlatoBrawl world, int size) {
		super(sprite, totalFrames);
		this.world = world;
		this.size = size;
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