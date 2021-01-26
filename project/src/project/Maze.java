package project;



public class Maze {

    int i,j;
    int path_cost, estimated_cost, total_cost, distance;
    
    public Maze(int i, int j){
     this.i=i;
     this.j=j;
    };
    public int i() { return i;}

    public int j() { return j;}
    
    public void Print(){
       System.out.println("(" + i + "," + j + ")");
    }
    
    // go up
    public Maze north(){
      return new Maze(i-1,j);
    }
    
    public Maze next(){
        return new Maze(i,j);
      }
    
    //go down
    public Maze south(){
        return new Maze(i+1 , j);
    }
    
    //go right
    public Maze east(){
        return new Maze(i,j+1);
    }
    
    //go left
    public Maze west(){
      return new Maze(i,j-1);
    }
    
}
