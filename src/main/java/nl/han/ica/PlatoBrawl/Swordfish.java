package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Created by Jeffrey on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class Swordfish extends AnimatedSpriteObject {

    private PlatoBrawl world;
    private HealthBar healthbar;
    private Player player;
    private int hitpoints;
    private boolean isAlive = true;

    public Swordfish(PlatoBrawl world, Player player) {
        this(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/swordfish.png"));
        this.world=world;
        this.hitpoints = 10;
        this.player = player;
        setHealthBar();
        setCurrentFrameIndex(1);
        setxSpeed(-1);
        if (world.round >= 20) {
    		setxSpeed(-2);
    	}
        setySpeed(2);
    }

    private Swordfish(Sprite sprite) {
        super(sprite, 2);
    }

    @Override
    public void update() {
    	if (getX() <= 0) {
        	setxSpeed(1);
        	setCurrentFrameIndex(0);
        	if (world.round >= 20) {
        		setxSpeed(2);
        	}
        }
    	if (getX() + getWidth() >= world.getWidth()) {
        	setxSpeed(-1);
        	setCurrentFrameIndex(1);
        	if (world.round >= 20) {
        		setxSpeed(-2);
        	}
        }
        if (getY() <= 0) {
        	setySpeed(2);
        }
        if (getY() + getHeight() >= world.getHeight()) {
        	setySpeed(-2);
        }
        if (hitpoints <= 0) {
        	die();
        }
        if (world.round < 10) {
        	if (getCenterX()%100 == 0) {
        	shoot();
        	}
        }
        if (world.round >= 10 && world.round < 15) {
        	if (getCenterX()%80 == 0) {
        	shoot();
        	}
        }
        if (world.round >= 15 && world.round < 25) {
        	if (getCenterX()%60 == 0) {
        	shoot();
        	}
        }
    }
      
    
    private void setHealthBar() {
    	HealthBar h = new HealthBar(this);
        world.addGameObject(h);
        this.healthbar = h;
    }
    
    private void die() {
    	isAlive = false;
    	world.deleteGameObject(this);
    	world.deleteGameObject(healthbar);
    	world.numberOfSwordfish--;
    	if (world.numberOfSwordfish == 0) {
    		nextRound();
    	}
    }
    
    private void nextRound() {
    	world.nextRound();
    	world.numberOfSwordfish = world.round;
    	for (int i = 0; i < world.numberOfSwordfish; i++) {
    		Swordfish swordfish = new Swordfish(world, player);
    		int random = (int )(Math.random() * 400 + 100);
    		world.addGameObject(swordfish, random, random);
    	}
    }
    
    public void shoot() {
    	if (world.round < 5) {
	    	if (getxSpeed() <= 0) {
	    		EnemyBullet b = new EnemyBullet(this, world, -5, 1);
	    		world.addGameObject(b, getX(), getCenterY());
	    	}
	    	if (getxSpeed() > 0) {
	    		EnemyBullet b = new EnemyBullet(this, world, 5, 0);
	    		world.addGameObject(b, getX() + getWidth(), getCenterY());
	    	}
    	}
    	
    	if (world.round >= 5) {
    		if (getCenterX() >= player.getCenterX()) {
        		EnemyBullet b = new EnemyBullet(this, world, -5, 1);
        		world.addGameObject(b, getX(), getCenterY());
        	}
        	if (getCenterX() < player.getCenterX()) {
        		EnemyBullet b = new EnemyBullet(this, world, 5, 0);
        		world.addGameObject(b, getX() + getWidth(), getCenterY());
        	}
    	}
    }
    
    public int getHitpoints() {
    	return hitpoints;
    }   
    
    public boolean getAlive() {
    	return isAlive;
    }
    
    public void bulletHit() {
    	hitpoints--;
    }
    
    public void superBulletHit() {
		hitpoints-=3;		
	}

}
