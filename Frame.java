
package tetris;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static tetris.Panel.currentBlockMoving;


public class Frame extends JFrame implements KeyListener {
    Panel panel = new Panel();
    public static Image icon = new ImageIcon("tetris.png").getImage();
    
    
    public Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.setIconImage(icon);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(1600,800));
        this.add(panel);
        this.addKeyListener(this);
        this.pack();
    }


    public static int keyPressed;
    public static int xCurrent;
    public static int yCurrent;
    public static int x;
    public static int y;
    public static int xCenter;
    public static int yCenter;
    public static boolean leftMovement;
    public static boolean rightMovement;
    public static boolean rotation;
    public static ArrayList<Block> tempBlock = new ArrayList<Block>();
    public static ArrayList<Integer> tempX=new ArrayList<Integer>();
    public static ArrayList<Integer> tempY=new ArrayList<Integer>();
    public static int counter;
    public static int currentSize;
    public static Block lastElement;
    public static int lastY;
    
    public void moveLeft(){
        for(Block w: currentBlockMoving){
            if(w.xCordinate<=5*Panel.WIDTH_OF_BLOCK){
                leftMovement=false;
                return;
            }
            for(Block v: Panel.BLOCKS_ON_THE_BOARD){
                if(w.xCordinate-v.xCordinate==Panel.WIDTH_OF_BLOCK && w.yCordinate==v.yCordinate){
                    leftMovement=false;
                    return;
                }
            }
        }
    }
    
    public void moveRight(){
        for(Block w: currentBlockMoving){
            if(w.xCordinate>=14*Panel.WIDTH_OF_BLOCK){
                rightMovement=false;
                return;
            }
            for(Block v: Panel.BLOCKS_ON_THE_BOARD){
                if(v.xCordinate-w.xCordinate==Panel.WIDTH_OF_BLOCK && w.yCordinate==v.yCordinate){
                    rightMovement=false;
                    return;
                }
            }
        }
    }
    
    public static void copyCordinates(){
        tempX.clear();
        tempY.clear();
        for(Block block: Panel.currentBlockMoving){
            tempX.add(block.xCordinate);
            tempY.add(block.yCordinate);
        }
    }
    
    public void copyBack(){
        for(counter=0; counter<tempX.size();counter++){
            currentBlockMoving.get(counter).xCordinate=tempX.get(counter);
            currentBlockMoving.get(counter).yCordinate=tempY.get(counter);
        }
    }
    
    public void copyBackCheck(){
        for(Block block: Panel.currentBlockMoving){
            for(Block b: Panel.BLOCKS_ON_THE_BOARD){
                if((block.xCordinate==b.xCordinate)&& (block.yCordinate==b.yCordinate)){
                    copyBack();
                    return;
                }
            }
        }
    }
    
    public void rotate(int rotationPoint){
        if(Panel.currentBlockMoving.get(0).currentBlock==5){
            return;
        }
        for(Block b: currentBlockMoving){
            xCurrent=currentBlockMoving.get(rotationPoint).xCordinate;
            yCurrent=currentBlockMoving.get(rotationPoint).yCordinate;
            xCenter=b.xCordinate-currentBlockMoving.get(rotationPoint).xCordinate;
            yCenter=b.yCordinate-currentBlockMoving.get(rotationPoint).yCordinate;
            x=-yCenter;
            y=xCenter;
            b.xCordinate=xCurrent+x;
            b.yCordinate=yCurrent+y;
        }
        check();

    }
    
    public void checkVertical(){
        
        currentSize=currentBlockMoving.size();
        lastElement=currentBlockMoving.get(currentSize-1);
        lastY=lastElement.yCordinate;
        for(Block c: Panel.currentBlockMoving){
            if(c.yCordinate>=650){
                copyBack();
                rotate(currentSize-1);
                return;
           }
        }
    }
    
    public static int largestX;
    
    public void alignRight(){
        largestX=0;
        for(Block block:Panel.currentBlockMoving){
            if(block.xCordinate>largestX){
                largestX=block.xCordinate;
            }
        }
        for(Block b: Panel.currentBlockMoving){
            b.xCordinate-=largestX-14*Panel.WIDTH_OF_BLOCK;
        }
    }
    
    public static int smallestX;
    public void alignLeft(){
        smallestX=5000;
        for(Block block:Panel.currentBlockMoving){
            if(block.xCordinate<smallestX){
                smallestX=block.xCordinate;
            }
        }
        for(Block b: Panel.currentBlockMoving){
            b.xCordinate+=5*Panel.WIDTH_OF_BLOCK-smallestX;
        }
    }
    
    public static int largestY;
    public void alignBottom(){
        largestY=0;
        for(Block block:Panel.currentBlockMoving){
            if(block.yCordinate>largestY){
                largestY=block.yCordinate;
            }
        }
        for(Block b: Panel.currentBlockMoving){
            b.yCordinate-=largestY-650;
        }
    }
    
    public void check(){
        for(Block c: Panel.currentBlockMoving){
        if(c.xCordinate>14*Panel.WIDTH_OF_BLOCK){
            alignRight();
            copyBackCheck();
                return;
            }
        
        if(c.xCordinate<5*Panel.WIDTH_OF_BLOCK){
            alignLeft();
            copyBackCheck();
                return;
            }
        for(Block b: Panel.BLOCKS_ON_THE_BOARD){
            if(b.xCordinate==c.xCordinate&&b.yCordinate==c.yCordinate){
                    copyBack();
                    return;
                }
            }
        
            if(c.yCordinate>650){
                alignBottom();
                    return;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed=e.getKeyCode();
        if(!Panel.pause){
            switch(keyPressed){
                case 37:
                    moveLeft();
                    if(leftMovement){
                    for(Block b: currentBlockMoving){
                          b.xCordinate-=Panel.WIDTH_OF_BLOCK;
                    }}
                    leftMovement=true;
                    break;
                case 39:
                    moveRight();
                    if(rightMovement){
                     for(Block b: currentBlockMoving){
                          b.xCordinate+=Panel.WIDTH_OF_BLOCK;
                    }}
                    rightMovement=true;
                    break;
                case 38:
                    if(Panel.currentBlockMoving.get(0).currentBlock!=0 ){
                        copyCordinates();
                        rotate(2);
                    }
                    break;
                case 40:
                    Panel.setSpeed(100);
                    break;
            }}
            switch(keyPressed){
                case 80:
                    if(!Panel.pause){
                    Panel.pause=true;}
                    else{
                        Panel.pause=false;
                    }
                    break;
                case 82:
                    Panel.restart();
                    break;
            }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==40){
            Panel.setSpeed(Panel.speed);
        }
    }

}