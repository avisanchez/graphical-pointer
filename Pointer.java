import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Pointer extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

  // Screen reference
  private static JPanel Screen;

  // pinpoint layout: [int xPos, int yPos]
  private static ArrayList<Integer[]> pinpoints = new ArrayList<Integer[]>();

  private static boolean showCoordinates = false;
  private static int pointerX = 0;
  private static int pointerY = 0;
  private static int isDragging = -1;

  private static Color selectedColor = Color.RED;
  private static Font selectedFont = new Font("Arial", Font.PLAIN, 13);
  private static FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

  public Pointer() {

    Screen.addKeyListener(this);
    Screen.setFocusable(true);

    Screen.addMouseListener(this);
    Screen.addMouseMotionListener(this);

  }

  // draws relevant info such as mouse coordinates and pinpoints
  public static void draw(Graphics g) {

    if (Pointer.Screen == null) {
      System.out.println(
          "ERROR: Cannot draw pointer without setting screen reference. Fix by invoking Pointer.setReferenceScreen(this) in Screen constructor.");
      return;
    }

    g.setColor(selectedColor);
    g.setFont(selectedFont);

    if (showCoordinates) {
      drawMouseCoordinates(g);
    }

    drawPinpoints(g);

  }

  private static void drawMouseCoordinates(Graphics g) {
    String coordinatesAsText = "(" + pointerX + ", " + pointerY + ")";
    int textwidth = (int) (selectedFont.getStringBounds(coordinatesAsText, frc).getWidth());
    int textheight = (int) (selectedFont.getStringBounds(coordinatesAsText, frc).getHeight());

    if (pointerX < 35 && pointerY < 35) {
      g.drawString(coordinatesAsText, pointerX + 15, pointerY + 15);
    } else if ((pointerX + textwidth) > Screen.getWidth()) {
      // shift left
      g.drawString(coordinatesAsText, pointerX - textwidth - 5, pointerY + 8);
    } else if ((pointerY - textheight) < 0) {
      // shift left down
      g.drawString(coordinatesAsText, pointerX - textwidth - 5, pointerY + 8);
    } else {
      // normal - top right
      g.drawString(coordinatesAsText, pointerX + 5, pointerY - 5);
    }
  }

  private static void drawPinpoints(Graphics g) {
    // draw saved pinpoints
    for (Integer[] each : pinpoints) {

      int localX = each[0];
      int localY = each[1];
      int rad = 5;

      String coordinatesAsText = "(" + localX + ", " + localY + ")";
      int textwidth = (int) (selectedFont.getStringBounds(coordinatesAsText, frc).getWidth());

      g.drawOval(localX - rad, localY - rad, 2 * rad, 2 * rad);

      g.setColor(Color.BLACK);
      g.drawLine(localX, localY, localX, localY - 10);

      g.setColor(selectedColor);
      g.drawString(coordinatesAsText, localX - textwidth / 2, localY - 20);
    }
  }

  public static void setReferenceScreen(JPanel Screen) {
    Pointer.Screen = Screen;
    Pointer init = new Pointer();
  }

  // set the pointer and pinpoint colors
  public static void setCustomColor(Color color) {
    Pointer.selectedColor = color;
  }

  public static void setCustomFont(Font font) {
    Pointer.selectedFont = font;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (isDragging != -1) {
      pinpoints.set(isDragging, new Integer[] { e.getX(), e.getY() });
      Screen.repaint();
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {

    if (showCoordinates) {
      Pointer.pointerX = e.getX();
      Pointer.pointerY = e.getY();
      Screen.repaint();
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {

    int mouseX = e.getX();
    int mouseY = e.getY();

    // check for pinpoint delete
    for (int i = 0; i < Pointer.pinpoints.size(); i++) {
      int px = pinpoints.get(i)[0];
      int py = pinpoints.get(i)[1];

      if (mouseX >= (px - 5) && mouseX <= (px + 5) && mouseY >= (py - 5) && mouseY <= (py + 5)) {
        pinpoints.remove(i);
        Screen.repaint();
        return;
      }
    }

    // otherwise add new pinpoint
    Pointer.pinpoints.add(new Integer[] { mouseX, mouseY });
    Pointer.pointerX = mouseX;
    Pointer.pointerY = mouseY;

    Screen.repaint();
  }

  // checks for pinpoint dragging
  @Override
  public void mousePressed(MouseEvent e) {

    int mouseX = e.getX();
    int mouseY = e.getY();

    // check for pinpoint selection
    for (int i = 0; i < Pointer.pinpoints.size(); i++) {
      int px = pinpoints.get(i)[0];
      int py = pinpoints.get(i)[1];

      if (mouseX >= (px - 5) && mouseX <= (px + 5) && mouseY >= (py - 5) && mouseY <= (py + 5)) {
        isDragging = i;
        Screen.repaint();
        return;
      }
    }

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Pointer.isDragging = -1;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub

    if (e.getKeyCode() == 16 && isDragging == -1) { // shift key

      pointerX = Screen.getMousePosition().x;
      pointerY = Screen.getMousePosition().y;

      showCoordinates = true;
      Screen.repaint();
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

    if (e.getKeyCode() == 16) { // shift key
      showCoordinates = false;
      Screen.repaint();
    }

  }

}
