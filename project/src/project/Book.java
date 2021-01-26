package project;

public class Book {
	private int ISBN;
    private String Author;
    private String Title;
    private String genre;
    private String refcode;
  public Book() {
	  int ISBN=0;
	    String Author=null;
	     String Title=null;
	    String genre=null;
	     String refcode =null;
  }
  public Book(int ISBN,String Author,String Title,String genre) {
	  this.Author=Author;
	  this.ISBN=ISBN;
	  this.Title=Title;
	  this.genre=genre;
  }

		public int getISBN() {
			return ISBN;
		}
		public void setISBN(int iSBN) {
			ISBN = iSBN;
		}
		public String getAuthor() {
			return Author;
		}
		public void setAuthor(String author) {
			Author = author;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getrefcode() {
			return refcode;
		}
		
		public void generateRefrence() {
			String s=Author.substring(0,2)+"-"+genre.substring(0,2);
			
		}
		public boolean verifyISBN(int ISBN) {
			int x1=(ISBN/1000)%10,x2=(ISBN/100)%10,x3=(ISBN/10)%10,x4=ISBN%10;
			if((x1*3+x2*2+x3*1)%4==x4)
				return true;
			
			else return false;
			
		}
		public void  printBookInfo() {
			System.out.println("Title: "+Title+"\n"+"Author "+Author+"\n"+"ISBN "+ISBN+"-"+Author.substring(0,2)+"-"+
					genre.substring(0,2)+"\n"+"Genre "+genre);
			
		}
		public void equals(Book b) {
			if( b.equals(ISBN))
				System.err.println(" no two books can have the same ISBN.");
			}
		public void printBookInfo1(){
	           
            System.out.println("ISBN: "+ISBN+"\nAuthor: "+Author+"\nTitle: "+Title+"\nGenre: "+genre);
           
           
            }
			
		
		
		
		
}
