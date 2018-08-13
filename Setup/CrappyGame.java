package ExampleCode;

import java.awt.*;
import hsa_ufa.Console;

/**
 * An example of the GameEngine design pattern in action.
 * February 7, 2011
 * @author Sam Scott
 **/
public class CrappyGame
{
  static Console c = new Console (320, 240, "Arrow keys to move, q to quit");;           // The output console
  
  public static void main (String[] args) throws InterruptedException
  {
    // The locations of the walls
    int topEdge = 0, bottomEdge = 230, leftEdge = 0, rightEdge = 310;
    // Ball x and y coordinates
    double ballx = 100, bally = 100;
    // Ball speed (x and y separate)
    double ballXSpeed = 1.5, ballYSpeed = 3;
    // The player's x and y coordinates
    double playerx = 50, playery = 50;
    // The player's maximum speed
    double playerSpeed = 5;
    // Variables for player input
    int playerKeyCode;
    char playerKeyChar;
    // Done flag - set to true to end the game
    boolean done = false;
    
    c.setBackgroundColor (new Color (0, 128, 0));
    
    
    while (!done) // Loops until the game is over
    {
      // 1. Pause
      Thread.sleep (1000/30);   // this gives 30 frames per second
      
      // 2. Update object positions
      ballx = ballx + ballXSpeed;
      bally = bally + ballYSpeed;
      
      // 3. Process player input
      playerKeyCode = c.getKeyCode (); // change to c.getLastKeyCode and c.getLastKeyChar
      playerKeyChar = c.getKeyChar (); // to see a different style of movement
      if (playerKeyCode == Console.VK_DOWN)
        playery = playery + playerSpeed;
      else if (playerKeyCode == Console.VK_UP)
        playery = playery - playerSpeed;
      else if (playerKeyCode == Console.VK_RIGHT)
        playerx = playerx + playerSpeed;
      else if (playerKeyCode == Console.VK_LEFT)
        playerx = playerx - playerSpeed;
      else if (playerKeyChar == 'q')
        done = true;
      
      // 4. Check for collisions
      if (ballx > rightEdge | ballx < leftEdge)
      {
        ballx = ballx - ballXSpeed;
        ballXSpeed = ballXSpeed * -1;
      }
      if (bally > bottomEdge | bally < topEdge)
      {
        bally = bally - ballYSpeed;
        ballYSpeed = ballYSpeed * -1;
      }
      
      // 5. Redraw the screen
      synchronized(c) {
        c.clear ();
        c.println ("   Arrow keys to move, q to quit");
        c.setColor (Color.yellow);
        c.fillOval ((int) ballx, (int) bally, 10, 10);
        c.setColor (Color.white);
        c.fillRect ((int) playerx, (int) playery, 20, 20);
      }
    }
    c.close ();
  } // main method
} // CrappyGame class
