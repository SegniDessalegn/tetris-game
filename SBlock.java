
package tetris;

import java.util.Random;
import javax.swing.ImageIcon;

public class SBlock extends Block{
    public static final int[][] S_POINTS_0={{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK+2*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK, Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK,Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}};
    public static int counter=0;
    public static Random random= new Random();
    public static int reflect;
    
    SBlock(){
        this.xCordinate=S_POINTS_0[counter][0];
        this.yCordinate=S_POINTS_0[counter][1];
        this.image= new ImageIcon("blue.png").getImage();
        this.currentBlock=3;
        counter++;
        Panel.createdBlock.add(this);
        if (counter<S_POINTS_0.length){
            new SBlock();
        }
        counter=0;

        reflect=random.nextInt(2);
        if(reflect==1){
            super.reflect();
        }
    }
}
