package project;
import java.util.*;
public class TestLibrary {
	public static void main(String []args) {
		Scanner s=new Scanner(System.in);
		library c=new library();
		int x;
		do {
			System.out.println("**********************************************************************");
			System.out.println("*"+"\t"+"\t"+" Welcome to KSU Library :)");
			System.out.println("* "+"\t"+"\t"+"---------------------------");
			System.out.println("* "+"\t"+"Please enter one of the following options:");
			System.out.println("*"+"\t"+"1) Add a book");
			System.out.println("*"+"\t"+"2) Delete a book");
			System.out.println("*       3) Find a book");
			System.out.println("*       4) List all books");
			System.out.println("*       5) List books for a given genre");
			System.out.println("*       6) Number of books for a given author");
			System.out.println("*       7) Total number of books");
			System.out.println("*       8) Exit");
			System.out.println("*");
			System.out.println("**********************************************************************");
			System.out.println("Enter your option :>");
			x=s.nextInt();
			switch(x) {
			case 1:{
				System.out.println("Please, enter the book details #ISBN, author, title, and genre.");
				int ISBN=s.nextInt();
				String Author=s.next();
				String Title=s.next();
				String genre =s.next();
				if(c.addbook(ISBN, Author, Title, genre))
				System.out.println("The book has been added");
			}
			break;
			case 2:{
				System.out.println("please enter ISBN");
				int ISBN=s.nextInt();
			    c.deletebook(ISBN);
			    System.out.println("the book is deleted");
					
					
			}
			break;
			case 3:{
				System.out.println("please enter ISBN");
				c.findbook(s.nextInt());
				
			}
			break;
			case 4:{
				c.printAll();
			}
			break;
			case 5:{
				System.out.println("please enter genre ");
				c.printgenre(s.next());
			}
			break;
			case 6:
			{
				System.out.println("please enter author");
				System.out.println("the number of books  for a given author are "+c.getnumberofbooksbyauthor(s.next()));
				}
			break;
			case 7:
			{
				System.out.println("total number of books are "+c.getnumberofbooks());
				}
			break;
			
			}
		
		}while(x!=8);
		System.out.println("thanks ^_^");

		
	}

}
