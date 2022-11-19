
package tetris;

import java.util.Random;
import javax.swing.ImageIcon;

public class TBlock extends Block{
    public static final int[][] TBlock={{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK,Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK, Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK +2*Panel.WIDTH_OF_BLOCK,Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}};    
    public static int counter=0;
    public static Random random= new Random();
    public static int reflect;
    
    TBlock(){
        this.xCordinate=TBlock[counter][0];
        this.yCordinate=TBlock[counter][1];
        this.image= new ImageIcon("red.png").getImage();
        this.currentBlock=4;
        counter++;
        Panel.createdBlock.add(this);
        if (counter<TBlock.length){
            new TBlock();
        }
        counter=0;

        reflect=random.nextInt(2);
        if(reflect==1){
            super.reflect();
        }
        }

    }

