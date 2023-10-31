import java.awt.*;
import java.util.Random;
import javax.swing.Timer;

public class ScaryHouse extends Canvas {
   private final Random random;
   private boolean isLightningVisible;
   private boolean isFlashVisible;


      public ScaryHouse() {
         setSize(800, 600);
         setBackground(Color.BLACK);
         setVisible(true);

         random = new Random();


         Timer lightningTimer = new Timer(3000, e -> triggerLightning());
         lightningTimer.start();
      }

   public void triggerLightning() {
      isLightningVisible = true;
      repaint();

      Timer flashTimer = new Timer(40, e -> {
         isFlashVisible = true;
         repaint();

         Timer flashOffTimer = new Timer(60, e2 -> {
            isFlashVisible = false;
            repaint();
         });
         flashOffTimer.setRepeats(false);
         flashOffTimer.start();

         Timer lightningOffTimer = new Timer(500, e2 -> {
            isLightningVisible = false;
            repaint();
         });
         lightningOffTimer.setRepeats(false);
         lightningOffTimer.start();
      });
      flashTimer.setRepeats(false);
      flashTimer.start();
   }

   @Override
   public void paint(Graphics window) {
      if (isFlashVisible) {
         window.setColor(Color.WHITE);
         window.fillRect(0, 0, 800, 600);
      } else {
         if (isLightningVisible) {
            drawLightning(window);
         }
         theHouse(window);
         drawMoon(window);


      }
   }

   public void theHouse(Graphics window) {
      // House body
      window.setColor(new Color(61, 61, 61));
      window.fillRect(200, 330, 400, 270);

      // Roof
      window.setColor(Color.DARK_GRAY);
      int[] xRoof = {180, 400, 620};
      int[] yRoof = {330, 180, 330};
      window.fillPolygon(xRoof, yRoof, 3);

      // Windows with panes
      window.setColor(Color.WHITE);
      window.fillRect(240, 370, 90, 100);
      window.fillRect(470, 370, 90, 100);
      window.setColor(Color.BLACK);
      window.drawLine(240 + 45, 370, 240 + 45, 470);
      window.drawLine(470 + 45, 370, 470 + 45, 470);
      window.drawLine(240, 370 + 50, 330, 370 + 50);
      window.drawLine(470, 370 + 50, 560, 370 + 50);

      // Door
      window.setColor(new Color(50, 25, 25));
      window.fillRect(360, 430, 80, 170);
      window.setColor(Color.LIGHT_GRAY);
      window.fillOval(375, 500, 10, 10);


      // Chimney
      window.setColor(Color.GRAY);
      window.fillRect(530, 210, 40, 120);
      window.setColor(new Color(61, 61, 61));
      window.fillRect(530, 210, 40, 10);
   }

   public void drawMoon(Graphics window) {
      window.setColor(Color.WHITE);
      window.fillOval(650, 30, 120, 120);

      // Moon Craters
      window.setColor(Color.LIGHT_GRAY);
      window.fillOval(670, 50, 30, 30);
      window.fillOval(720, 100, 25, 25);
      window.fillOval(740, 60, 20, 20);
   }




   public void drawLightning(Graphics window) {
      window.setColor(Color.WHITE);
      Graphics2D g2 = (Graphics2D) window;
      g2.setStroke(new BasicStroke(9));

      int xOffset = random.nextInt(100) - 50;

      int[] xPoints = {395 + xOffset, 405 + xOffset, 400 + xOffset, 390 + xOffset, 400 + xOffset, 410 + xOffset};
      int[] yPoints = {0, 150, 175, 300, 325, 450};
      g2.drawPolyline(xPoints, yPoints, 6);
   }
}
