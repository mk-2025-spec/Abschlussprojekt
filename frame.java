import java.awt.*;
import java.awt.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 08.06.2026
 * @author 
 */

public class frame extends Frame {
  // start attributes
  // end attributes
  
  public frame() { 
    // Frame init
    super();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { dispose(); }
    });
    int frameWidth = 881; 
    int frameHeight = 788;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("frame");
    setResizable(false);
    Panel cp = new Panel(null);
    add(cp);
    // start components
    
    // end components
    
    setVisible(true);
  } // end of public frame
  
  // start methods
  
  public static void main(String[] args) {
    new frame();
  } // end of main
  
  // end methods
} // end of class frame
