package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

class Panel extends JPanel implements ActionListener {
    public static final int WIDTH_OF_BLOCK = 50;
    public static final int HEIGHT_OF_BLOCK = 50;
    public static final int WIDTH_OF_BOARD = WIDTH_OF_BLOCK * 10;
    public static final int HEIGHT_OF_BOARD = WIDTH_OF_BLOCK * 15;
    public static Timer timer;
    public static ArrayList<Block> currentBlockMoving = new ArrayList<Block>();
    public static ArrayList<Block> BLOCKS_ON_THE_BOARD = new ArrayList<Block>();
    public static ArrayList<Integer> Y_CORDINATES = new ArrayList<Integer>();
    public static Random random = new Random();
    public static int r;
    public static boolean pause;
    public static int currentBlock;
    public static int speed = 500;
    public static boolean gameOver;
    public static boolean restart;
    public static ArrayList[] blocksToBeAdded = new ArrayList[2];
    public static ArrayList<Block> nextBlock = new ArrayList<Block>();
    public static ArrayList<Block> createdBlock = new ArrayList<Block>();
    public static ArrayList<Block> temp1 = new ArrayList<Block>();
    public static ArrayList<Block> temp2 = new ArrayList<Block>();
    public static int yCountdown;
    public static int counter;
    public static int score;
    public static ArrayList<Block> toBeCancelled = new ArrayList<Block>();
    public static int tempSize;
    public static int initialSpeed;

    Panel() {
        timer = new Timer(speed, this);
        createNewBlock();
        for (Block block : createdBlock) {
            temp1.add(block);
        }
        currentBlockMoving = temp1;
        createdBlock.clear();
        createNewBlock();
        for (Block block : createdBlock) {
            temp2.add(block);
        }
        nextBlock = temp2;
        timer.start();
    }

    public static void createNewBlock() {
        r = random.nextInt(6);
        switch (r) {
            case 0:
                new CubeBlock();
                break;
            case 1:
                new IBlock();
                break;
            case 2:
                new LBlock();
                break;
            case 3:
                new SBlock();
                break;
            case 4:
                new TBlock();
                break;
            case 5:
                new PowerBlock();
                break;
        }
    }

    public void switchElements() {
        currentBlockMoving.clear();
        for (Block block : nextBlock) {
            temp1.add(block);
        }
        currentBlockMoving = temp1;
        createdBlock.clear();
        Panel.createNewBlock();
        nextBlock.clear();
        for (Block block : createdBlock) {
            nextBlock.add(block);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(new Color(10, 10, 10));
        graphics2D.fillRect(0, 0, 1600, 800);
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.setPaint(new Color(50, 50, 50));
        graphics2D.fillRect(5 * Panel.WIDTH_OF_BLOCK, 0, 10 * Panel.WIDTH_OF_BLOCK, 700);
        graphics2D.setPaint(new Color(50, 50, 200));
        graphics2D.drawRect(5 * Panel.WIDTH_OF_BLOCK, 0, 10 * Panel.WIDTH_OF_BLOCK, 700);
        graphics2D.drawRect(15 * Panel.WIDTH_OF_BLOCK, 0, 5 * Panel.WIDTH_OF_BLOCK, 700);
        graphics2D.setPaint(new Color(200, 50, 50));
        graphics2D.setFont(new Font("Normal", Font.BOLD, 25));
        graphics2D.drawString("P - Pause", 800, 450);
        graphics2D.drawString("R - Restart", 800, 500);
        graphics2D.setPaint(new Color(200, 50, 50));
        graphics2D.setFont(new Font("Normal", Font.BOLD, 50));

        for (Block bl : nextBlock) {
            if (bl.currentBlock == 5) {
                graphics2D.drawImage(bl.image, bl.xCordinate + 400, bl.yCordinate + 300, null);
            } else {
                graphics2D.drawImage(bl.image, bl.xCordinate + 350, bl.yCordinate + 300, null);
            }
        }

        for (Block c : BLOCKS_ON_THE_BOARD) {
            graphics2D.drawImage(c.image, c.xCordinate, c.yCordinate, null);
        }

        for (Block b : currentBlockMoving) {
            graphics2D.drawImage(b.image, b.xCordinate, b.yCordinate, null);
        }

        graphics2D.drawString("score ", 800, 350);
        graphics2D.drawString(""+score, 850, 400);
        if (gameOver) {
            graphics2D.setPaint(Color.RED);
            graphics2D.setFont(new Font("Normal", Font.BOLD, 40));
            graphics2D.drawString("Game Over", 765, 600);
        }
        else if (pause) {
            graphics2D.setPaint(Color.CYAN);
            graphics2D.setFont(new Font("Normal", Font.BOLD, 40));
            graphics2D.drawString("Paused", 800, 600);
        }
        else if (Panel.restart) {
            graphics2D.setPaint(Color.CYAN);
            graphics2D.setFont(new Font("Normal", Font.BOLD, 40));
            graphics2D.drawString("Restarted", 780, 600);
        }
        repaint();
    }

    public void moveDown() {
        for (Block b : currentBlockMoving) {
            if (b.yCordinate >= 650) {
                for (Block d : currentBlockMoving) {
                    BLOCKS_ON_THE_BOARD.add(d);
                    Panel.Y_CORDINATES.add(d.yCordinate);
                }
                restart=false;
                checkScore();
                switchElements();
                if (speed > 100) {
                    speed -= 10;
                    timer.setDelay(speed);
                }
                return;
            }
        }
        if (Panel.currentBlockMoving.get(0).currentBlock != 5) {
            if (!Y_CORDINATES.isEmpty()) {
                for (Block d : currentBlockMoving) {
                    for (int e = Panel.BLOCKS_ON_THE_BOARD.size() - 1; e >= 0; e--) {
                        if (d.yCordinate + HEIGHT_OF_BLOCK == BLOCKS_ON_THE_BOARD.get(e).yCordinate && d.xCordinate == BLOCKS_ON_THE_BOARD.get(e).xCordinate) {
                            for (Block f : currentBlockMoving) {
                                BLOCKS_ON_THE_BOARD.add(f);
                                Panel.Y_CORDINATES.add(f.yCordinate);
                            }                            
                            gameOverCheck();
                            checkScore();
                            switchElements();
                            if (speed > 100) {
                                speed -= 10;
                                timer.setDelay(speed);
                            }
                            return;
                        }
                    }
                }
            }
            
            for (Block c : currentBlockMoving) {
                c.yCordinate += HEIGHT_OF_BLOCK;
            }
        }

        if (Panel.currentBlockMoving.get(0).currentBlock == 5) {
            counter = 0;
            for (Block blo : Panel.BLOCKS_ON_THE_BOARD) {
                if (blo.xCordinate == Panel.currentBlockMoving.get(0).xCordinate && blo.yCordinate > Panel.currentBlockMoving.get(0).yCordinate) {
                    counter++;
                }
            }
            if (counter == (int) ((650 - Panel.currentBlockMoving.get(0).yCordinate) / 50)) {
                BLOCKS_ON_THE_BOARD.add(currentBlockMoving.get(0));
                checkScore();
                switchElements();
                if (speed > 100) {
                    speed -= 5;
                    timer.setDelay(speed);
                }
            }
            else {
                Panel.currentBlockMoving.get(0).yCordinate += Panel.HEIGHT_OF_BLOCK;
            }
        }
    }

    public static void setSpeed(int speed) {
        timer.setDelay(speed);
    }

    public static void restart() {
        Panel.restart=true;
        Panel.currentBlockMoving.clear();
        Panel.BLOCKS_ON_THE_BOARD.clear();
        Panel.createdBlock.clear();
        Panel.nextBlock.clear();
        Panel.gameOver=false;
        Panel.pause=false;
        createNewBlock();
        for (Block block : createdBlock) {
            temp1.add(block);
        }
        currentBlockMoving = temp1;
        createdBlock.clear();
        createNewBlock();
        for (Block block : createdBlock) {
            temp2.add(block);
        }
        nextBlock = temp2;
    }

    public static void gameOverCheck() {
        for (int i : Panel.Y_CORDINATES) {
            if (i < 0) {
                gameOver = true;
            }
        }
    }

    public void checkScore() {
        for (yCountdown = 650; yCountdown >= 0; yCountdown -= Panel.HEIGHT_OF_BLOCK) {
            tempSize = BLOCKS_ON_THE_BOARD.size();
            toBeCancelled.clear();
            counter = 0;
            for (tempSize = BLOCKS_ON_THE_BOARD.size(); tempSize > 0; tempSize--) {
                if (yCountdown == BLOCKS_ON_THE_BOARD.get(tempSize - 1).yCordinate) {
                    counter += 1;
                    toBeCancelled.add(BLOCKS_ON_THE_BOARD.get(tempSize - 1));
                }
            }
            if (counter != 10) {
                toBeCancelled.clear();
            }

            if (counter == 10) {
                Panel.score += 10;
                for (Block block : toBeCancelled) {
                    BLOCKS_ON_THE_BOARD.remove(block);
                    if(speed<500){
                        speed+=1;
                    }
                }
                timer.setDelay(speed);
                for (Block block : BLOCKS_ON_THE_BOARD) {
                    if (block.yCordinate < yCountdown) {
                        block.yCordinate += Panel.HEIGHT_OF_BLOCK;
                    }
                }
                checkScore();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            if (!pause) {
                moveDown();
            }
        }
    }
}
