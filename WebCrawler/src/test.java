import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class test {
	public static void main(String args[]){
		Random rand = new Random();
		Scanner in = new Scanner(System.in);
		String answer;

		do{
			String titleAnswer,badTitle1,badTitle2,badTitle3;
			do{
				
			titleAnswer = BookInfo.getRandBook();
			badTitle1= BookInfo.getRandBook();
			badTitle2= BookInfo.getRandBook();
			badTitle3= BookInfo.getRandBook();
			}while(titleAnswer.equals(badTitle1) || titleAnswer.equals(badTitle2) ||
					titleAnswer.equals(badTitle3) || badTitle1.equals(badTitle2) || 
					badTitle1.equals(badTitle3) ||  badTitle2.equals(badTitle3));
			
			URLReader bibleBook = new URLReader(titleAnswer,1);
			int randVerse = rand.nextInt(bibleBook.verses.size() - 1 +1)+1;
		
			System.out.println("***************************************");
			System.out.println("Where does the following verse belong?");
			System.out.println("");
			System.out.println("\""+bibleBook.verses.get(randVerse)+"\"");
			int randChoice = rand.nextInt(4-1+1)+1;
			switch (randChoice){
			case 1:
				System.out.println("\nA. " + bibleBook.Book);
				System.out.println("B. " + badTitle1);
				System.out.println("C. " + badTitle2);
				System.out.println("D. " + badTitle3);
				break;
			case 2:
				System.out.println("\nA. " + badTitle1);
				System.out.println("B. " + bibleBook.Book);
				System.out.println("C. " + badTitle2);
				System.out.println("D. " + badTitle3);
				break;
			case 3:
				System.out.println("\nA. " + badTitle1);
				System.out.println("B. " + badTitle2);
				System.out.println("C. " + bibleBook.Book);
				System.out.println("D. " + badTitle3);
				break;
			case 4:
				System.out.println("\nA. "+ badTitle1);
				System.out.println("B. " + badTitle2);
				System.out.println("C. " + badTitle3);
				System.out.println("D. "  + bibleBook.Book);
				break;
			}
			System.out.println("***************************************");
	
			answer = in.nextLine();
			
			if ((answer.equals("A") || answer.equals("a")) &&
					randChoice == 1){
				System.out.println("Congratulations! You're basically a saint.");
			}
			else if ((answer.equals("B") || answer.equals("b")) &&
					randChoice == 2){
				System.out.println("Congratulations! You're basically a saint.");
			}
			else if ((answer.equals("C") || answer.equals("c")) &&
					randChoice == 3){
				System.out.println("Congratulations! You're basically a saint.");
			}
			else if ((answer.equals("D") || answer.equals("d")) &&
					randChoice == 4){
				System.out.println("Congratulations! You're basically a saint.");
			}
			else{
				System.out.println("Bummer dude. Go read your Bible.");
				System.out.println("The correct answer was: " + titleAnswer);
			}
			
			System.out.println("Loading next question...");
			bibleBook.verses.clear();
			bibleBook.Book = "";
		}while(!answer.equals("q") || !answer.equals("Q"));
		in.close();
	}
}
