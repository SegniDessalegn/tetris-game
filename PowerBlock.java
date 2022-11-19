
package tetris;

import javax.swing.ImageIcon;

public class PowerBlock extends Block {
    public static final int I_POINTS[][]={{9*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}};
    public static int counter=0;
    
    PowerBlock(){
        this.xCordinate=I_POINTS[counter][0];
        this.yCordinate=I_POINTS[counter][1];
        this.image= new ImageIcon("cyan.png").getImage();
        this.currentBlock=5;
        counter++;
        Panel.createdBlock.add(this);
        if (counter<I_POINTS.length){
            new IBlock();
        }
        counter=0;

        
}
}
