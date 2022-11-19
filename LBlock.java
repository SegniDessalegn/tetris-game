
package tetris;

import java.util.Random;
import javax.swing.ImageIcon;

    public class LBlock extends Block{
    public static final int L_POINTS[][]={{9*Panel.WIDTH_OF_BLOCK,-3*Panel.WIDTH_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK,Panel.HEIGHT_OF_BLOCK-3*Panel.WIDTH_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK,2*Panel.HEIGHT_OF_BLOCK-3*Panel.WIDTH_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK,2*Panel.HEIGHT_OF_BLOCK-3*Panel.WIDTH_OF_BLOCK}};
    public static int counter=0;
    public static Random random=new Random();
    public static int reflect;

    LBlock(){
        this.xCordinate=L_POINTS[counter][0];
        this.yCordinate=L_POINTS[counter][1];
        this.image= new ImageIcon("purple.png").getImage();
        this.currentBlock=2;
        counter++;
        Panel.createdBlock.add(this);
        if (counter<L_POINTS.length){
        new LBlock();}
        counter=0;

        reflect=random.nextInt(2);
        if(reflect==1){
            super.reflect();
        }
        
}
}
