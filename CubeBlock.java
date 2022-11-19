
package tetris;

import javax.swing.ImageIcon;
import static tetris.Panel.currentBlock;

public class CubeBlock extends Block{
    public static final int CUBE_POINTS[][]={{9*Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK,-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK,Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}
            ,{9*Panel.WIDTH_OF_BLOCK+Panel.WIDTH_OF_BLOCK, Panel.HEIGHT_OF_BLOCK-3*Panel.HEIGHT_OF_BLOCK}};
    public static CubeBlock CUBE_BLOCK[] = new CubeBlock[CUBE_POINTS.length];
    public static int counter=0;
//    public int currentBlock=0;
    CubeBlock(){
        this.xCordinate=CUBE_POINTS[counter][0];
        this.yCordinate=CUBE_POINTS[counter][1];
        this.image= new ImageIcon("yellow.png").getImage();
        this.currentBlock=0;
        CUBE_BLOCK[counter]=this;
        counter++;
        Panel.createdBlock.add(this);
       if (counter<CUBE_POINTS.length){
            new CubeBlock();
        }
        counter=0;
//        this.currentBlock=0;

    }

}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public static final int CUBE_POINTS[][]={{0,0},{Panel.WIDTH,0},{0,Panel.HEIGHT},{Panel.WIDTH, Panel.HEIGHT}};
//    public int xCordinate;
//    public int yCordinate;
//    public Image image;
//    
//    
//    CubeBlock(){
//        for(int i=0;i<CUBE_POINTS.length; i++){
//                    this.xCordinate=CUBE_POINTS[i][0];
//                    this.yCordinate=CUBE_POINTS[i][1];
//                }
//        this.image= new ImageIcon("orangeBlock.png").getImage();
//    }
