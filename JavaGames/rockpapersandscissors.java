import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class rockpapersandscissors{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] options = {"rock", "paper", "scissors"};

        int numRounds = getNumberOfRounds(scanner);

        int playerWins = 0;
        int computerWins = 0;

        for (int i = 0; i < numRounds; i++) {
            String playerMove = getPlayerMove(scanner, options);
            String computerMove = options[random.nextInt(options.length)];

            String result = determineWinner(playerMove, computerMove);
            System.out.println("Round " + (i + 1) + ": " + result);

            if (result.equals("You win!")) {
                playerWins++;
            } else if (result.equals("Computer wins!")) {
                computerWins++;
            }
        }

        System.out.println("\nFinal Results:");
        System.out.println("Player wins: " + playerWins);
        System.out.println("Computer wins: " + computerWins);

        if (playerWins > computerWins) {
            System.out.println("You won the game!");
        } else if (computerWins > playerWins) {
            System.out.println("Computer won the game!");
        } else {
            System.out.println("It's a tie!");
        }

        scanner.close();
    }

    private static int getNumberOfRounds(Scanner scanner) {
        System.out.println("How many rounds would you like to play? ");
        int numRounds;
        try {
            numRounds = scanner.nextInt();
            if (numRounds <= 0) {
                throw new IllegalArgumentException("Number of rounds must be positive.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a positive integer.");
            scanner.nextLine(); // Clear the scanner buffer
            return getNumberOfRounds(scanner);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getNumberOfRounds(scanner);
        }
        return numRounds;
    }

    private static String getPlayerMove(Scanner scanner, String[] options) {
        System.out.println("Enter your move (rock, paper, scissors): ");
        String playerMove = scanner.nextLine().toLowerCase();

        while (!isValidMove(playerMove, options)) {
            System.out.println("Invalid move. Please enter rock, paper, or scissors: ");
            playerMove = scanner.nextLine().toLowerCase();
        }

        return playerMove;
    }

    private static boolean isValidMove(String move, String[] options) {
        for (String option : options) {
            if (move.equals(option)) {
                return true;
            }
        }
        return false;
    }

    private static String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
                   (playerMove.equals("paper") && computerMove.equals("rock")) ||
                   (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}