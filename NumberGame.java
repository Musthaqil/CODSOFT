package task1;
import java.util.*;

public class NumberGame {
	public static void main(String[] args) {
		Random r = new Random();
		int predictedValue = (r.nextInt(100))+1; //Here we generate the random numbers 1 to 100
		
		Scanner sc = new Scanner(System.in);
		int chances = 10; //No. of Chances
		int round = 1; //No. of Rounds initially it starts from 1
		int score = 0; //score 
		while(chances!=0) { //until user has the chance they can play
			System.out.println("Round No.: "+round+"\tChances Left: "+chances+"\nGuess the number from 1 to 100");
//			System.out.println(predictedValue); //to test the game working properly, here we print that random number.(testing)
			int userInput=sc.nextInt(); //getting the input guess number from user
			
			if(userInput<predictedValue) { //checking if it is low
				System.out.println("Too low");
				chances--; //for each wrong guess user will lose chance
			}
			else if(userInput>predictedValue) { //checking if it is high
				System.out.println("Too high");
				chances--; //for each wrong guess user will lose the chance
			}
			else { //if it is not high or low it'll be the correct number
				System.out.println("Great! You got the number");
				score+=(100*chances); //the score will be calculated by the product of 100 with no of chances remaining 
				System.out.println("Your score is "+score); //total score will be displayed after each round completion
				round++; //once the number is guessed correctly user will move to next round
				chances=10; //starting of the each round, user will get 5 chances
				predictedValue = (r.nextInt(100))+1; //once the number is guessed correctly again the number is randomized for next round
			}
		}
		//Once the user lose all chances they'll be exited from the game by displaying their total score
		System.out.println("You are done with your chances and you played really good");
		System.out.println("Your final score is "+score);
		sc.close();
	}
}