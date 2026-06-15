import java.awt.*;
import java.awt.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 08.06.2026
 * @author 
 */

public class ZentralesFrame extends Frame {
  // start attributes
  // end attributes
  
  public ZentralesFrame() { 
    // Frame init
    super();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { dispose(); }
    });
<<<<<<< HEAD
    int frameWidth = 1071; 
    int frameHeight = 1022;
=======
    int frameWidth = 292; 
    int frameHeight = 300;
>>>>>>> 111b4f845d3d4cc3aa9528b5eaead9392d705015
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("ZentralesFrame");
    setResizable(false);
    Panel cp = new Panel(null);
    add(cp);
    // start components
    
    // end components
    
    setVisible(true);
  } // end of public ZentralesFrame
  
  // start methods
  
  public static void main(String[] args) {
    new ZentralesFrame();
  } // end of main
  
  // end methods
} // end of class ZentralesFrame
