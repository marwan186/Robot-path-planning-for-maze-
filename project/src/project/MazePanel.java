package project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Math;

import javax.imageio.ImageIO;
import javax.swing.*;









/*Class Header: MazePanel*/
/*Description: its got the run, paint, and other methods used for recursion
 I created a 2D array and basically the movefrom and paint methods do most
of the work calling the other methods to change stuff up.
*/
class MazePanel extends JPanel implements Runnable {
   public MazePanel() {
   }

   long startTime;
   //stop time
   long stopTime;
   //calculate the elapsed time
   long duration;
   //time for DFS
   double dfsTime;
   //time for BFS
   double bfsTime;
   boolean repaint = false;
   final static byte X = 1;
   //free space   (white Square)
   final static int C = 0;
   //initial state
   final static int S = 2;
   //goal
   final static int E = 8;
   // the path
   final static byte V = 9;
   public static byte[][] pixels;
   final static int START_I = 1, START_J = 1;
   //goal  (i,j)
   final static int END_I = 28, END_J = 29;
  
   
   public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	   Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
       BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

       Graphics2D g2d = dimg.createGraphics();
       g2d.drawImage(tmp, 0, 0, null);
       g2d.dispose();

       return dimg;
   }

   public static void trainTheShit(){
       File dir = new File("C:\\Users\\USER\\Downloads\\maze1");
       
       File[] directoryListing = dir.listFiles();
       if (directoryListing != null) {
           for (File child : directoryListing) {
               getBinaryStringFromImage(child);
               System.out.print("\n\n\n\n\n");
           }
       } else {

       }
   }

   public static String getBinaryStringFromImage(File imageFile)
   {
       BufferedImage img = null;
       try {
           img = ImageIO.read(imageFile);
       } catch (IOException e) {

       }
       img = resize(img,30,30);
       
  pixels = new byte[img.getWidth()][];
       byte[] second = new byte[img.getWidth()*img.getHeight()];

       for (int x = 0; x < img.getWidth(); x++) {
           pixels[x] = new byte[img.getHeight()];
           System.out.println();
           for (int y = 0; y < img.getHeight(); y++) {
               pixels[x][y] = (byte) (img.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
               second[x*y]=(byte) (img.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
               System.out.print(pixels[x][y]);
               
           }

       }

       pixels[1][1] = 2;
       pixels[28][29] = 8;
       return second.toString();
   }
   

	public void paintComponent(Graphics g) {
		
	        super.paintComponent(g);
	        g.translate(70, 70);      //move the maze to begin at 70 from x and 70 from y

	        // draw the maze
	        if (repaint == true) {
	            for (int row = 0; row < pixels.length; row++) {
	                for (int col = 0; col < pixels[0].length; col++) {
	                    Color color;
	                    switch (pixels[row][col]) {
	                        case 1:
	                            color = Color.BLACK;       // block
	                            break;
	                        case 8:
	                            color = Color.RED;          // goal
	                            break;
	                        case 2:
	                            color = Color.YELLOW;      //initial state 
	                            break;
	                            
	                            
	                       
	                        default:
	                            color = Color.WHITE;        // white free space 0
	                    }
	                    g.setColor(color);
	                    g.fillRect(25 * col, 25 * row, 25, 25);    // fill rectangular with color 
	                    g.setColor(Color.BLUE);
	                    g.drawRect(25 * col, 25 * row, 25, 25);    //draw rectangular with color

	                }
	            }
	        }

	        if (repaint == false) {
	            for (int row = 0; row < pixels.length; row++) {
	                for (int col = 0; col < pixels[0].length; col++) {
	                    Color color;
	                    switch (pixels[row][col]) {
	                        case 1:
	                            color = Color.darkGray;
	                            break;
	                        case 8:
	                            color = Color.RED;
	                            break;
	                        case 2:
	                            color = Color.YELLOW;
	                            break;
	                        case 9:
	                            color = Color.green;   // the path from the initial state to the goal
	                            break;
	                        default:
	                            color = Color.WHITE;
	                    }
	                    g.setColor(color);
	                    g.fillRect(25 * col, 25 * row, 25, 25);
	                    g.setColor(Color.BLUE);
	                    g.drawRect(25 * col, 25 * row, 25, 25);

	                }

	            }

	        }

	    }
  	
  /*Method Header: run  */
  public void run () {
	  Scanner s= new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter which way to solve ropot path"
	    		+ " 1- BFS "
	    		+ "2- DFS"
	    		+ "3- GREEDY"
	    		+ "4- AStar");
	    int i = s.nextInt();
	    switch(i) {
	    case 1:
	    	BFS();
	    case 2:
	    	DFS();
	    case 3:
	    	greedy_bfs();
	    case 4:
	   // 	A_star();
	    	
	    
	    }
  }
  
 

  // Print the Maze 

  public void Print() {
      for (int i = 0; i < pixels.length; i++) {
          for (int J = 0; J < pixels.length; J++) {
              System.out.print(pixels[i][J]);
              System.out.print(' ');
          }
          System.out.println();
      }
  }
  
  // return true if it is in the maze
  public boolean isInMaze(int i, int j) {

      if (i >= 0 && i <pixels.length && j >= 0 && j < pixels.length) {
          return true;
      } else {
          return false;
      }
  }
  
  public boolean isInMaze(Maze pos) {
      return isInMaze(pos.i(), pos.j());
  }
  // check if it is the reached goal
  public boolean isFinal(int i, int j) {

      return (i == MazePanel.END_I && j == MazePanel.END_J);
  }
  public boolean isFinal(Maze pos) {
      return isFinal(pos.i(), pos.j());
  }
  
 
  // mark the visited path with the color green 
  public int mark(int i, int j, byte value) {
      assert (isInMaze(i, j));  // it is used for test.if the condition is false it will throw an error named AssertionError.
      int temp = pixels[i][j];
      pixels[i][j] = value;
      return temp;
  }
  public int mark(Maze pos, byte value) {
      return mark(pos.i(), pos.j(), value);
  }
  
  // return true if the node is not a wall and not visited
  public boolean isClear(int i, int j) {
	  assert (isInMaze(i, j));
      return (pixels[i][j] != X && pixels[i][j] != V);
  }

  public boolean isClear(Maze pos) {
      return isClear(pos.i(), pos.j());
  }
  
  

  public void BFS() {
      //start the timer
      startTime = System.nanoTime();
      int path_cost = 0;
      int expanded_nodes = 0;

      //list from Maze
      LinkedList<Maze> frointer = new LinkedList<Maze>();

      // add initial node to the list
      frointer.add(new Maze(START_I, START_J));

      Maze crt , next;
      while (!frointer.isEmpty()) {
    	  expanded_nodes++;
          //get current position
          crt = frointer.removeFirst();
          // to be sure if it reach the goal

          if (isFinal(crt)) {
              break;
          }
         

          //mark the current position
          mark(crt, V);

          //put its neighbors in the queue
          next = crt.south();    //move down
          
          if (isInMaze(next) && isClear(next)) {
        	  frointer.add(next);
              path_cost++;
          }
          next = crt.north();    //move up
          if (isInMaze(next) && isClear(next)) {
        	  frointer.add(next);
              path_cost++;

          }
           next = crt.east();    //move right 
          if (isInMaze(next) && isClear(next)) {
        	  frointer.add(next);
              path_cost++;

          }
          next = crt.west();   //move left
          if (isInMaze(next) && isClear(next)) {
        	  frointer.add(next);
              path_cost++;

          }
         


      }
      if (!frointer.isEmpty()) {
          stopTime = System.nanoTime();
         JOptionPane.showMessageDialog(getRootPane(), "You Got it");

     } else {
         JOptionPane.showMessageDialog(getRootPane(), "You Are stuck in the maze");
     }

     System.out.println("\nFind Goal By BFS : ");
     Print();
     // stop time
    
     duration = stopTime - startTime;    //calculate the elapsed time

     System.out.println(String.format("Time %1.3f ms", duration));
      System.out.println("path cost: " + path_cost);          
      System.out.println("expanded nodes: " + expanded_nodes);
  }
  

  public void DFS() {
	  startTime = System.nanoTime();
	  int path_cost = 0;
      int expanded_nodes = 0;
      //stack of Maze
      Stack<Maze> frointer = new Stack<Maze>();

      //insert the start
      frointer.push(new Maze(START_I, START_I));

      Maze crt;   //current node
      Maze next;  //next node
      while (!frointer.empty()) {
    	  
    	  expanded_nodes++;
          crt = frointer.pop();
          if (isFinal(crt)) {

              break;
          }

          //mark the current position
          mark(crt, V);

          //put its neighbours in the queue
          next = crt.south();    
          if (isInMaze(next) && isClear(next)) {
        	  frointer.push(next);
        	  path_cost++;

          }
          next = crt.north();     
          if (isInMaze(next) && isClear(next)) {
        	  frointer.push(next);
        	  path_cost++;

          }
          next = crt.east();    
          if (isInMaze(next) && isClear(next)) {
        	  frointer.push(next);
        	  path_cost++;

          }
          next = crt.west();   
          if (isInMaze(next) && isClear(next)) {
        	  frointer.push(next);
        	  path_cost++;

          }
      }

      if (!frointer.empty()) {
           stopTime = System.nanoTime();
          JOptionPane.showMessageDialog(getRootPane(), "You Got it");

      } else {
          JOptionPane.showMessageDialog(getRootPane(), "You Are stuck in the maze");
      }

      System.out.println("\nFind Goal By DFS : ");
      Print();
      // stop time
     
      duration = stopTime - startTime;    //calculate the elapsed time

      System.out.println(String.format("Time %1.3f ms", duration));
      System.out.println("path cost: " + path_cost);         
      System.out.println("expanded nodes: " + expanded_nodes);
      

  }
  
  
  
  
  
  private static int heuristic () {
      //manhattan distance
	  return (Math.abs(START_I - END_I) + Math.abs(START_J -END_J));
  }


  public static class TotalCost implements Comparator<Maze> {
      
      public int compare(Maze first, Maze second) {
          if (first.total_cost < second.total_cost) {
              return -1;
          }
          else if (first.total_cost > second.total_cost) {
              return 1;
          }
          else
              return 0;
      }
  }

  public static class EstimatedCost implements Comparator<Maze> {
      
      public int compare(Maze first, Maze second) {
          if (first.estimated_cost < second.estimated_cost) {
              return -1;
          }
          else if (first.estimated_cost > second.estimated_cost) {
              return 1;
          }
          else
              return 0;
      }
  }
  
  
  
  private  void greedy_bfs() {   //storgae only contains on node for greedy_bfs
      Comparator<Maze> comp = new EstimatedCost();
      PriorityQueue<Maze> q = new PriorityQueue<Maze>(1000, comp);         //use a priority queue
      int path_cost = 0;
      int expanded_nodes = 0;
      new Maze(START_I, START_J).estimated_cost = heuristic();       //use the heuristic function to get the estimated cost
      q.add(new Maze(START_I, START_J));

      Maze crt , next;
      while (!q.isEmpty()) {

          //get current position
          crt = q.remove();
          expanded_nodes++;
          // to be sure if it reach the goal

          if (isFinal(crt)) {
              break;
          }
         

          //mark the current position
          mark(crt, V);

          //put its neighbors in the queue
          next = crt.north();    //move up
          
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
        	  path_cost++;
        	 q.add(next);
          }
          
          next = crt.east();    
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
        	  path_cost++;
        	 q.add(next);
          }
          
          next = crt.west();  
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
        	  path_cost++;
        	 q.add(next);
          }

          next = crt.south();    

          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
        	  path_cost++;
        	 q.add(next);
          }
          
      
      }
      
      if (!q.isEmpty()) {
          stopTime = System.nanoTime();
         JOptionPane.showMessageDialog(getRootPane(), "You Got it");

     } else {
         JOptionPane.showMessageDialog(getRootPane(), "You Are stuck in the maze");
     }

     System.out.println("\nFind Goal By DFS : ");
     Print();
     // stop time
    
     duration = stopTime - startTime;    //calculate the elapsed time

     System.out.println(String.format("Time %1.3f ms", duration));
     System.out.println("path cost: " + path_cost);         
     System.out.println("expanded nodes: " + expanded_nodes);
  }


  
  /*public void A_star () {
      LinkedList<Maze> frointer = new LinkedList<Maze>();
      frointer.add(new Maze(START_I, START_J));    
      int path_cost = 0;
      int expanded_nodes = 0;
      new Maze(START_I, START_J).path_cost = 0;       //use the heuristic function to get the estimated cost
      

     

      int min=0;
      Maze best= null;										//use this for loop to find the closest node of all the nodes we need to visit
      for (int i = 0; i < frointer.size(); i++) {
    	  Maze h= frointer.get(i);
          int temp =  new Maze(START_I, START_J).path_cost + heuristic();              //heuristic of the A* funtion
          best = new Maze(START_I, START_J);
          if (temp < min) {
              min = temp; 
              best= new Maze(h.i, h.j);
          }
      }

      Maze end = best; 
   
      new Maze(START_I, START_J).total_cost = new Maze(START_I, START_J).path_cost + heuristic();
      Maze crt, next;
      while (!frointer.isEmpty()) {

    	  crt = frointer.remove();
          expanded_nodes++;
          
    	  min= 0;
          best = null;
          crt.path_cost = 0;
          
          for (int i = 0; i < frointer.size(); i++) {
        	  Maze h= frointer.get(i);
              int temp = crt.path_cost + heuristic();
             
              if (temp < min) {
                  min = temp;
                  best = new Maze(h.i, h.j);
                 
              }
          }
    	  
          end = best;
          crt.total_cost = crt.path_cost + heuristic();
    	  
    	  
    	  
    	  
    	  
           
          // to be sure if it reach the goal

          if (isFinal(crt)) {
              break;
          }
         

          //mark the current position
          mark(crt, V);

          //put its neighbors in the queue
          next = crt.south();    //move up
          
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
                    	  
       	  next.path_cost = crt.path_cost + 1;
          next.total_cost = crt.path_cost + heuristic();
          frointer.add(next);
      	  path_cost++;


                  }
          
          next = crt.north(); 
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
                    	  
       	  next.path_cost = crt.path_cost + 1;
          next.total_cost = crt.path_cost + heuristic();
          frointer.add(next);
      	  path_cost++;

                  }
          next = crt.east(); 
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
                    	  
       	  next.path_cost = crt.path_cost + 1;
          next.total_cost = crt.path_cost + heuristic();
          frointer.add(next);
      	  path_cost++;
      	  
              }
          
          next = crt.west(); 
          if (isInMaze(next) && isClear(next)) {
        	  next.estimated_cost = heuristic();
                    	  
       	  next.path_cost = crt.path_cost + 1;
          next.total_cost = crt.path_cost + heuristic();
          frointer.add(next);
      	  path_cost++;
              
              }

          }
      if (!frointer.isEmpty()) {
          stopTime = System.nanoTime();
         JOptionPane.showMessageDialog(getRootPane(), "You Got it");

     } else {
         JOptionPane.showMessageDialog(getRootPane(), "You Are stuck in the maze");
     }

     System.out.println("\nFind Goal By A_star : ");
     Print();
     // stop time
    
     duration = stopTime - startTime;    //calculate the elapsed time

     System.out.println(String.format("Time %1.3f ms", duration));
      System.out.println("path cost: " + path_cost);          //out put on screen
      System.out.println("expanded nodes: " + expanded_nodes);
  }*/
  
  }


