package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.PlatoBrawl.Player;

/**
 * Created by timon on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class Enemy extends AnimatedSpriteObject {

    private PlatoBrawl world;
    private HealthBar healthbar;
    private Player player;
    private int hitpoints;
    private boolean isAlive = true;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Enemy(PlatoBrawl world, Player player) {
        this(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/enemy.png"));
        this.world=world;
        this.hitpoints = 10;
        this.player = player;
        setHealthBar();
        setCurrentFrameIndex(1);
        setxSpeed(-world.round);
        setySpeed(2);
    }

    /**
     * Maak een Enemy aan met een sprite
     * @param sprite De sprite die aan dit object gekoppeld moet worden
     */
    private Enemy(Sprite sprite) {
        super(sprite, 2);
    }

    @Override
    public void update() {
    	if (getX() <= 0) {
        	setxSpeed(1);
        	setCurrentFrameIndex(0);
        }
    	if (getX() + getWidth() >= world.getWidth()) {
        	setxSpeed(-1);
        	setCurrentFrameIndex(1);
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
        if (world.round >= 10 && world.round < 20) {
        	if (getCenterX()%80 == 0) {
        	shoot();
        	}
        }
        if (world.round >= 20) {
        	if (getCenterX()%60 == 0) {
        	shoot();
        	}
        }
    }
  
    /**
     * Maakt een nieuwe HealthBar aan voor de Enemy
     */
    private void setHealthBar() {
    	HealthBar h = new HealthBar(this);
        world.addGameObject(h);
        this.healthbar = h;
    }
    
    
    /**
     * Gaat dood en wordt verwijdert
     * @param world Referentie naar de wereld
     */
    private void die() {
    	isAlive = false;
    	world.deleteGameObject(this);
    	world.deleteGameObject(healthbar);
    	world.enemyAmount--;
    	if (world.enemyAmount == 0) {
    		nextRound();
    	}
    }
    
    /**
     * Volgende ronde wordt gestart
     */
    private void nextRound() {
    	world.nextRound();
    	world.enemyAmount = world.round;
    	for (int i = 0; i < world.enemyAmount; i++) {
    		Enemy enemy = new Enemy(world, player);
    		int random = (int )(Math.random() * 300 + 100);
    		world.addGameObject(enemy, random, random);
    	}
    }
    
    /**
     * Schietmethode
     * Na ronde 5 schieten de Enemies op de speler ipv naar voren
     */
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
    
    
    /**
     * De enemy wordt geraakt met een bullet
     */
    public void bulletHit() {
    	hitpoints--;
    }
    
    /**
     * De enemy wordt geraakt met een SuperBullet en verliest meer levens
     */
    public void superBulletHit() {
		hitpoints-=3;		
	}

}