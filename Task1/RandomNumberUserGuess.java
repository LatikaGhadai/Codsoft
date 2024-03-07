import java.util.Scanner;

public class RandomNumberUserGuess {
    private int score = 0;

    public static void main(String[] args) {
        RandomNumberUserGuess random = new RandomNumberUserGuess();
        random.randomNumberGenerator();
    }

    public void randomNumberGenerator() {
        int minRange = 1;
        int maxRange = 100;
        int attemptLimit = 5;
        int countAttempt = 0;
        int randomNumber = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
        System.out.println("Guess a random number between " + minRange + " and " + maxRange + ": ");
        Scanner scanner = new Scanner(System.in);

        while (countAttempt < attemptLimit) {
            countAttempt++;
            int guessUserNumber = scanner.nextInt();
            if (randomNumber == guessUserNumber) {
                System.out.println("Congratulations! You guessed the correct number: " + randomNumber);
                updateScore(attemptLimit - countAttempt); // Update score based on attempts taken
                break;
            } else if (randomNumber < guessUserNumber) {
                System.out.println("Sorry! Your guess number is too high.");
            } else {
                System.out.println("Sorry! Your guess number is too low.");
            }
        }

        if (countAttempt < attemptLimit) {
            System.out.println("You have " + (attemptLimit - countAttempt) + " attempt(s) left... ");
            System.out.println("Do you want to play again? (yes/no): ");
            playAgain(); // Call playAgain method here
        }

        if (countAttempt == attemptLimit) {
            System.out.println("Sorry! You've run out of attempts.");
            System.out.println("The correct number was: " + randomNumber);
            System.out.println("Do you want to play again? (yes/no): ");
            playAgain(); // Call playAgain method here
        }
        scanner.close();
    }

    public void playAgain() {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            playAgain = scanner.next();
            if (playAgain.equalsIgnoreCase("yes")) {
                randomNumberGenerator(); // Restart the game if the user wants to play again
            } else if (!playAgain.equalsIgnoreCase("no")) {
                System.out.println("Invalid input! Please enter 'yes' or 'no'.");
            }
        } while (!playAgain.equalsIgnoreCase("yes") && !playAgain.equalsIgnoreCase("no"));

        System.out.println("Thanks for playing!");
        System.out.println("Your score is: " + score); // Display score when game ends
        scanner.close();
    }

    public void updateScore(int points) {
        score += points;
    }
}
