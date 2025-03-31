import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class TicTacToe extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private JPanel gamePanel;
    private JLabel positionLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe visualizer = new TicTacToe();
            visualizer.setVisible(true);
        });
    }

    public TicTacToe() {
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
        positionLabel = new JLabel("Mouse Position: X = 0, Y = 0");
        positionLabel.setForeground(Color.WHITE);
        positionLabel.setHorizontalAlignment(JLabel.CENTER);
        positionLabel.setBackground(new Color(35, 35, 35));
        positionLabel.setOpaque(true);
        positionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(positionLabel, BorderLayout.SOUTH);

        // Add mouse motion listener to track mouse movement
        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Get x and y coordinates
                int x = e.getX();
                int y = e.getY();

                // Update the label with current coordinates
                positionLabel.setText("Mouse Position: X = " + x + ", Y = " + y);
            }
        });

        // Add mouse listener to handle clicks
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse clicks for game logic if needed
                System.out.println("Clicked at: X = " + e.getX() + ", Y = " + e.getY());
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
    public void drawX(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int offset = 75;
        g2d.drawLine(x - offset, y - offset, x + offset, y + offset);
        g2d.drawLine(x - offset, y + offset, x + offset, y - offset);
    }

    /**
     * Draws an O at the specified grid position
     * @param g2d Graphics context
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void drawO(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int radius = 75;
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
