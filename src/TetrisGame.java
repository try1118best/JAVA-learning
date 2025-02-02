import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class TetrisGame extends JFrame {

    private static final int WIDTH = 20;      // 游戏区域宽度（单位：方块）
    private static final int HEIGHT = 30;     // 游戏区域高度
    private static final int BLOCK_SIZE = 30; // 每个方块的大小（像素）

    private Timer timer;
    private boolean isFalling = false;
    private int[][] board = new int[HEIGHT][WIDTH]; // 游戏区域数据（0表示空）
    private int[][] currentShape;                   // 当前下落方块
    private int currentX, currentY;                 // 当前方块位置
    private int score = 0;

    // 七种俄罗斯方块形状
    private final int[][][] SHAPES = {
            {{1, 1, 1, 1}},                 // I
            {{1, 1}, {1, 1}},               // O
            {{1, 1, 1}, {0, 1, 0}},         // T
            {{1, 1, 1}, {1, 0, 0}},         // L
            {{1, 1, 1}, {0, 0, 1}},         // J
            {{1, 1, 0}, {0, 1, 1}},         // S
            {{0, 1, 1}, {1, 1, 0}}          // Z
    };

    private final Color[] COLORS = {
            Color.CYAN, Color.YELLOW, Color.MAGENTA,
            Color.ORANGE, Color.BLUE, Color.GREEN, Color.RED
    };

    public TetrisGame() {
        setTitle("Java Tetris - JDK8");
        setSize(WIDTH * BLOCK_SIZE + 200, HEIGHT * BLOCK_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 游戏区域面板
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        // 初始化游戏控制
        initGame();

        // 设置键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        // 游戏主循环定时器
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!moveDown()) {
                    mergeToBoard();
                    checkLines();
                    newShape();
                }
            }
        });
        timer.start();
    }

    private void initGame() {
        // 初始化游戏区域
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = 0;
            }
        }
        newShape();
    }

    private void newShape() {
        Random random = new Random();
        currentShape = SHAPES[random.nextInt(SHAPES.length)];
        currentX = WIDTH / 2 - currentShape[0].length / 2;
        currentY = 0;
        isFalling = true;

        // 检查游戏结束
        if (checkCollision(currentX, currentY, currentShape)) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
            System.exit(0);
        }
    }

    private boolean moveDown() {
        if (!checkCollision(currentX, currentY + 1, currentShape)) {
            currentY++;
            repaint();
            return true;
        }
        return false;
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!checkCollision(currentX - 1, currentY, currentShape)) currentX--;
                break;
            case KeyEvent.VK_RIGHT:
                if (!checkCollision(currentX + 1, currentY, currentShape)) currentX++;
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_UP:
                rotateShape();
                break;
        }
        repaint();
    }

    private void rotateShape() {
        int[][] rotated = new int[currentShape[0].length][currentShape.length];
        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[0].length; j++) {
                rotated[j][currentShape.length - 1 - i] = currentShape[i][j];
            }
        }
        if (!checkCollision(currentX, currentY, rotated)) {
            currentShape = rotated;
        }
    }

    private boolean checkCollision(int newX, int newY, int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int boardX = newX + j;
                    int boardY = newY + i;
                    if (boardX < 0 || boardX >= WIDTH || boardY >= HEIGHT) return true;
                    if (boardY >= 0 && board[boardY][boardX] != 0) return true;
                }
            }
        }
        return false;
    }

    private void mergeToBoard() {
        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j] != 0) {
                    int y = currentY + i;
                    if (y >= 0) board[y][currentX + j] = 1;
                }
            }
        }
    }

    private void checkLines() {
        int linesCleared = 0;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            boolean full = true;
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                removeLine(i);
                linesCleared++;
                i++; // 重新检查当前行
            }
        }
        score += linesCleared * 100;
    }

    private void removeLine(int line) {
        for (int i = line; i > 0; i--) {
            System.arraycopy(board[i - 1], 0, board[i], 0, WIDTH);
        }
        Arrays.fill(board[0], 0);
    }

    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 绘制游戏区域
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (board[i][j] != 0) {
                        g.setColor(Color.GRAY);
                        g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
                    }
                }
            }

            // 绘制当前下落方块
            if (currentShape != null) {
                for (int i = 0; i < currentShape.length; i++) {
                    for (int j = 0; j < currentShape[i].length; j++) {
                        if (currentShape[i][j] != 0) {
                            int x = (currentX + j) * BLOCK_SIZE;
                            int y = (currentY + i) * BLOCK_SIZE;
                            g.setColor(COLORS[getShapeIndex(currentShape)]);
                            g.fillRect(x, y, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
                        }
                    }
                }
            }

            // 绘制分数
            g.setColor(Color.BLACK);
            g.drawString("Score: " + score, WIDTH * BLOCK_SIZE + 10, 20);
        }

        private int getShapeIndex(int[][] shape) {
            for (int i = 0; i < SHAPES.length; i++) {
                if (Arrays.deepEquals(SHAPES[i], shape)) {
                    return i;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TetrisGame game = new TetrisGame();
            game.setVisible(true);
        });
    }
}