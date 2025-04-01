import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class TicTacToe extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int BIG_SYMBOL = 75;
    private static final int SMALL_SYMBOL = 15;
    private int hoverX = -1;
    private int hoverY = -1;
    private JPanel gamePanel;
    private JLabel positionLabel;
    private boolean gameOver;
    private boolean xTurn;
    private int xWins;
    private int oWins;
    private int clicks;
    private int[] board;
    private JButton resetButton;
    private static final int[][] POSITIONS = {
            {300, 300}, {500, 300}, {700, 300},
            {300, 500}, {500, 500}, {700, 500},
            {300, 700}, {500, 700}, {700, 700}
    };
    private static final int[][] WIN_PATTERNS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
    private static final int[][] WIN_LINES = {
            {200, 300, 800, 300}, {200, 500, 800, 500}, {200, 700, 800, 700},
            {300, 200, 300, 800}, {500, 200, 500, 800}, {700, 200, 700, 800},
            {200, 200, 800, 800}, {800, 200, 200, 800}
    };
    private static int WIN_INDEX;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe visualizer = new TicTacToe();
            visualizer.setVisible(true);
        });
    }

    public TicTacToe() {
        xWins = 0;
        oWins = 0;
        clicks = 0;
        WIN_INDEX = -1;
        xTurn = true;
        gameOver = false;
        board = new int[9];
        setTitle("Tic Tac Toe");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        gamePanel.setBackground(new Color(24, 24, 24));
        add(gamePanel, BorderLayout.CENTER);

        // Create a label to display mouse position
        positionLabel = new JLabel(xWins + " - " + oWins);
        positionLabel.setForeground(Color.WHITE);
        positionLabel.setHorizontalAlignment(JLabel.CENTER);
        positionLabel.setBackground(new Color(35, 35, 35));
        positionLabel.setOpaque(true);
        positionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setFocusPainted(false);
        resetButton.setBackground(new Color(24, 24, 24));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resetButton.setBounds(400, 830, 200, 50);
        add(positionLabel, BorderLayout.SOUTH);
        add(resetButton, BorderLayout.NORTH);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!gameOver) {
                    hoverX = e.getX();
                    hoverY = e.getY();
                    gamePanel.repaint(); // Request a repaint to show the hover effect
                }
            }
        });

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                // Clear hover effect when mouse leaves the panel
                hoverX = -1;
                hoverY = -1;
                gamePanel.repaint();
            }

            // Add your click handling here if you haven't already
        });

        // Add mouse listener to handle clicks
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameOver) {
                    if (e.getX() > 200 && e.getX() < 400 && e.getY() < 400 && e.getY() > 200 && board[0] == 0) {
                        if (xTurn) { board[0] = 1; xTurn = false; } else { board[0] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 400 && e.getX() < 600 && e.getY() < 400 && e.getY() > 200 && board[1] == 0) {
                        if (xTurn) { board[1] = 1; xTurn = false; } else { board[1] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 600 && e.getX() < 800 && e.getY() < 400 && e.getY() > 200 && board[2] == 0) {
                        if (xTurn) { board[2] = 1; xTurn = false; } else { board[2] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 200 && e.getX() < 400 && e.getY() < 600 && e.getY() > 400 && board[3] == 0) {
                        if (xTurn) { board[3] = 1; xTurn = false; } else { board[3] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 400 && e.getX() < 600 && e.getY() < 600 && e.getY() > 400 && board[4] == 0) {
                        if (xTurn) { board[4] = 1; xTurn = false; } else { board[4] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 600 && e.getX() < 800 && e.getY() < 600 && e.getY() > 400 && board[5] == 0) {
                        if (xTurn) { board[5] = 1; xTurn = false; } else { board[5] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 200 && e.getX() < 400 && e.getY() < 800 && e.getY() > 600 && board[6] == 0) {
                        if (xTurn) { board[6] = 1; xTurn = false; } else { board[6] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 400 && e.getX() < 600 && e.getY() < 800 && e.getY() > 600 && board[7] == 0) {
                        if (xTurn) { board[7] = 1; xTurn = false; } else { board[7] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (e.getX() > 600 && e.getX() < 800 && e.getY() < 800 && e.getY() > 600 && board[8] == 0) {
                        if (xTurn) { board[8] = 1; xTurn = false; } else { board[8] = 2; xTurn = true; }
                        clicks++;
                    }
                    if (checkWin()) {
                        gameOver = true;
                        if (xTurn) {
                            positionLabel.setText("X WINS!");
                            xWins++;
                        } else {
                            positionLabel.setText("O WINS!");
                            oWins++;
                        }
                    } else if (clicks == 9) {
                        positionLabel.setText("Tie!");
                    }
                }
                repaint();
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = 0;
        }
        xTurn = true;
        clicks = 0;
        positionLabel.setText(xWins + " - " + oWins);
        gameOver = false;
        repaint();
    }

    public boolean checkWin() {
        int cnt = 0;
        for (int[] line: WIN_PATTERNS) {
            if (board[line[0]] == 1 && board[line[1]] == 1 && board[line[2]] == 1) {
                xTurn = !xTurn;
                WIN_INDEX = cnt;
                return true;
            }
            if (board[line[0]] == 2 && board[line[1]] == 2 && board[line[2]] == 2) {
                xTurn = !xTurn;
                WIN_INDEX = cnt;
                return true;
            }
            cnt++;
        }
        return false;
    }

    private void drawBoard(Graphics g) {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw gradient background
        Color color1 = new Color(24, 24, 24);
        Color color2 = new Color(35, 35, 35);
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, HEIGHT, color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw board lines
        g2d.setColor(Color.lightGray);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawLine(400, 200, 400, 800);
        g2d.drawLine(600, 200, 600, 800);
        g2d.drawLine(200, 400, 800, 400);
        g2d.drawLine(200, 600, 800, 600);

        for (int i = 0; i < 9; i++) {
            int x = POSITIONS[i][0];
            int y = POSITIONS[i][1];

            if (board[i] == 1) {
                drawX(g2d, x, y, BIG_SYMBOL);
            }
            if (board[i] == 2) {
                drawO(g2d, x, y, BIG_SYMBOL);
            }
        }

        if (!gameOver && hoverX >= 0 && hoverY >= 0) {
            // Make the hover symbol semi-transparent
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

            if (xTurn) {
                drawX(g2d, hoverX + 35, hoverY + 40, SMALL_SYMBOL);
            } else {
                drawO(g2d, hoverX + 35, hoverY + 40, SMALL_SYMBOL);
            }

            // Restore original composite
            g2d.setComposite(originalComposite);
        }

        if (gameOver) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(30, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(WIN_LINES[WIN_INDEX][0], WIN_LINES[WIN_INDEX][1], WIN_LINES[WIN_INDEX][2], WIN_LINES[WIN_INDEX][3]);
        }

        // Draw the final image to the screen
        g.drawImage(bufferedImage, 0, 0, null);
        g2d.dispose();
    }

    /**
     * Draws an X at the specified coordinates
     * @param g2d Graphics context
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void drawX(Graphics2D g2d, int x, int y, int offset) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2d.drawLine(x - offset, y - offset, x + offset, y + offset);
        g2d.drawLine(x - offset, y + offset, x + offset, y - offset);
    }

    /**
     * Draws an O at the specified grid position
     * @param g2d Graphics context
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void drawO(Graphics2D g2d, int x, int y, int radius) {
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

/**
 * Grid positions for symbols (center points of each cell):
 *
 *  (300, 300) | (500, 300) | (700, 300)
 *  -----------|------------|------------
 *  (300, 500) | (500, 500) | (700, 500)
 *  -----------|------------|------------
 *  (300, 700) | (500, 700) | (700, 700)
 */
