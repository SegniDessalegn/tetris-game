
package tetris;

import java.util.Random;
import javax.swing.ImageIcon;

public class IBlock extends Block {
    public static final int I_POINTS[][]={{8*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{8*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{8*Panel.WIDTH_OF_BLOCK+2*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{8*Panel.WIDTH_OF_BLOCK+3*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{8*Panel.WIDTH_OF_BLOCK+4*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}};
    public static int counter=0;
    public static Random random=new Random();
    public static int reflect;
    
    IBlock(){
        this.xCordinate=I_POINTS[counter][0];
        this.yCordinate=I_POINTS[counter][1];
        this.image= new ImageIcon("green.png").getImage();
        this.currentBlock=1;
        counter++;
        Panel.createdBlock.add(this);
        if (counter<I_POINTS.length){
            new IBlock();
        }
        counter=0;

        reflect=random.nextInt(2);
        if(reflect==1){
            super.reflect();
        }
        
}
}
