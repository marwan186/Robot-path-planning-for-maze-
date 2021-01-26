package project;



import javax.swing.JFrame;
/* created by marwan */

public class image  {
	
	 public static void main (String [] args) 
	    {
	    	
		 JFrame world = new JFrame();
	      world.setSize(1024, 800);
	      world.setTitle("Maze");
	        MazePanel p = new MazePanel();

		      p.trainTheShit();

		      world.setContentPane(p);
		     
		      world.setVisible(true);
		      Thread gameThread = new Thread(p);
		      gameThread.start();
	        
	        
	    

    
    

  
 
	  
	  
	 
	 
  }
	  


  
  
}

