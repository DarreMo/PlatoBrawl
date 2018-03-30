package nl.han.ica.platogrot;

import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import processing.core.PVector;

/**
 * @author Ralph Niels
 * Een zwaardvis is een spelobject dat zelfstandig
 * door de wereld beweegt
 */
public class Swordfish extends SpriteObject {

    private PlatoGrot world;
    private int[][] indexMap;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Swordfish(PlatoGrot world, int[][] indexMap) {
        this(new Sprite("src/main/java/nl/han/ica/platogrot/media/swordfish.png"));
        this.indexMap = indexMap;
        this.world=world;
    }

    /**
     * Maak een Swordfish aan met een sprite
     * @param sprite De sprite die aan dit object gekoppeld moet worden
     */
    private Swordfish(Sprite sprite) {
        super(sprite);
        setxSpeed(-1);
    }

    @Override
    public void update() {
        if (getX()+getWidth()<=0) {
            setX(world.getWidth());
        }

    }
    /**
     * Gets the TileMap.
     * @return The two dimensional int array which contains the indexes of the tileTypes.
     */
    public int[][] getFishMap() {
        return indexMap;
    }
    
    
    /**
     * Gets the x and y location of the Swordfish.
     * @return An PVector which contains the location of the Swordfish in Pixels.
     */
    public PVector getSwordfishPixelLocation() {
        return new PVector(getX() * getWidth(), getY() * getHeight());
    }
}
