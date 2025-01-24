import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimplePong extends JPanel implements ActionListener, KeyListener {
    int ballX = 450, ballY = 300, ballDX = 3, ballDY = 3;
    int paddleX = 300, paddleY = 850;
    int paddleX2 = 300, paddleY2 = 5;
    Timer timer = new Timer(2, this);

    public SimplePong() {
        JFrame frame = new JFrame("Simple Pong");
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(ballX, ballY, 50, 50); // Draw the ball
        g.fillRect(paddleX, paddleY, 180, 10); // Draw the paddle
        g.fillRect(paddleX2, paddleY2, 180, 10); // Draw the paddle 2
    }

    public void actionPerformed(ActionEvent e) {
        ballX += ballDX;
        ballY += ballDY;
        if (ballX < 0 || ballX > getWidth() - 50) ballDX *= -1; // Bounce ball off walls
        if (ballY < 0 || ballY > getHeight() - 50) ballDY *= -1; // Bounce ball off top/bottom
        //bottom paddle
        if (ballY > paddleY - 50 && ballX + 50 > paddleX && ballX < paddleX + 180) {ballDY *= -1; System.out.println("bottom, hit");} // Bounce ball off paddle

        //top paddle
        if (ballY < paddleY2+5 && ballX + 50 > paddleX2 && ballX < paddleX2 + 180) {ballDY *= -1; System.out.println("top hit");} // Bounce ball off paddle 2
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddleX > 0) paddleX -= 30; // Move paddle left
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddleX < getWidth() - 180) paddleX += 30; // Move paddle right
        if (e.getKeyCode() == KeyEvent.VK_A && paddleX2 > 0) paddleX2 -= 30; // Move paddle left
        if (e.getKeyCode() == KeyEvent.VK_D && paddleX2 < getWidth() - 180) paddleX2 += 30; // Move paddle right
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimplePong::new);
    }
}

