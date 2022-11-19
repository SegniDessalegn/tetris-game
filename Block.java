
package tetris;

import java.awt.Image;
import static tetris.Frame.x;
import static tetris.Frame.xCenter;
import static tetris.Frame.xCurrent;
import static tetris.Frame.y;
import static tetris.Frame.yCenter;
import static tetris.Frame.yCurrent;

public class Block {

    public int xCordinate;
    public int yCordinate;
    public Image image;
    public static int rotationPoint;
    public int currentBlock;

    
    public void reflect(){
        rotationPoint=2;
        for(Block b: Panel.createdBlock){
            xCurrent=Panel.createdBlock.get(rotationPoint).xCordinate;
            yCurrent=Panel.createdBlock.get(rotationPoint).yCordinate;
            xCenter=b.xCordinate-Panel.createdBlock.get(rotationPoint).xCordinate;
            yCenter=b.yCordinate-Panel.createdBlock.get(rotationPoint).yCordinate;
            x=-yCenter;
            y=-xCenter;
            b.xCordinate=xCurrent+x;
            b.yCordinate=yCurrent+y;
        }
    }
}
