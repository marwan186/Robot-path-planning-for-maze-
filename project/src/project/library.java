package project;

public class library {
	private Book[] libraryBooks;
	private int numofbooks;
	private static final int max_size=100; 
	public library() {
		int numofbooks=0;
		libraryBooks=new Book[max_size];
	}
	public boolean addbook(int ISBN,String Author,String Title,String genre) {
		if(numofbooks<max_size) {
			if(findbook(ISBN)==-1) {
			Book s=new Book(ISBN, Author,Title, genre);
			if(verifyISBN(ISBN)) {
				libraryBooks[numofbooks]=s;	

							numofbooks++;
				return true;
			}
			else
				System.out.println("ISBN isn't correct");
				return false;
			
			}
			else
				System.out.println("the book already there");
				return false;
			}
		else return false;
	}
	
	private boolean verifyISBN(int ISBN) {
	
		int x1=(ISBN/1000)%10,x2=(ISBN/100)%10,x3=(ISBN/10)%10,x4=ISBN%10;
		if((x1*3+x2*2+x3*1)%4==x4)
			return true;
		
		else return false;
		
	}
	public boolean addbook(Book newbook ) {
		if(numofbooks<max_size) {
			if(findbook(newbook.getISBN())==-1) {
				libraryBooks[numofbooks]=newbook;
				numofbooks++;
				return true;
			}
			else System.out.println("the book already there");
			return false;
		}
		else System.out.println("ISBN isn't correct");
		return false;
	}
	public void deletebook(int ISBN) {
		for(int i=0;i<numofbooks;i++) {
			if(libraryBooks[i].getISBN()==ISBN) {
				libraryBooks[i]=libraryBooks[numofbooks-1];
				numofbooks--;
			}

		}
		
		
	}
	public int findbook(int ISBN) {
		for(int i=0;i<numofbooks;i++) {
			if(libraryBooks[i].getISBN()==ISBN) 
			return i;	
			
		}
		return -1;
	}
	
public int findbook(Book findbook1) {
	for(int i=0;i<numofbooks;i++) {
		if(libraryBooks[i].getClass().equals(findbook1)) 
			
		return i;
	}
		return -1;
	}
	public void printAll() {
		if(numofbooks>0) {
			for(int i=0;i<numofbooks;i++) {
				libraryBooks[i].printBookInfo1();
		}
		}
	}
	public void printgenre(String genre) {
		for(int i=0;i<numofbooks;i++)
			if(libraryBooks[i].getGenre()==genre)
				
				libraryBooks[i].printBookInfo();
	}
	public int getnumberofbooksbyauthor(String authorname) {
	int total=0;
	for(int i=0;i<numofbooks;i++) {
		if(libraryBooks[i].getAuthor().equals(authorname))
			total++;
	}
	return total;
		
	}
	public int getnumberofbooks() {
		return numofbooks;
		
		
	}
	public Book[]  getlibrarybooks() {
		return libraryBooks;
		
	}
	public void setnumofbooks(int n){
		numofbooks=n;
		
	}
	

}
