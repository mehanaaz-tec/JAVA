import java.util.Scanner;

public class Hangman {

    // List of words to guess from (can expand this list)
    static String[] wordList = {"apple", "banana", "grape", "orange", "melon"};
    
    // Maximum number of incorrect guesses allowed
    static final int MAX_TRIES = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Choose a random word from the list
        String secretWord = wordList[(int) (Math.random() * wordList.length)];
        char[] guessedWord = new char[secretWord.length()];
        boolean[] guessedLetters = new boolean[26]; // track guesses a-z

        // Fill guessedWord with underscores
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        int incorrectGuesses = 0;
        boolean wordGuessed = false;

        System.out.println("Welcome to Hangman!");

        // Game loop
        while (incorrectGuesses < MAX_TRIES && !wordGuessed) {
            // Print current state
            System.out.print("Word: ");
            for (char c : guessedWord) {
                System.out.print(c + " ");
            }
            System.out.println("\nIncorrect guesses left: " + (MAX_TRIES - incorrectGuesses));

            // Ask for a guess
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single valid letter.");
                continue;
            }

            char guess = input.charAt(0);
            int index = guess - 'a';

            if (guessedLetters[index]) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            guessedLetters[index] = true;

            // Check if guess is in the word
            boolean correct = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    correct = true;
                }
            }

            if (correct) {
                System.out.println("Good guess!");
            } else {
                incorrectGuesses++;
                System.out.println("Sorry, that letter is not in the word.");
            }

            // Check if the word is fully guessed
            wordGuessed = true;
            for (char c : guessedWord) {
                if (c == '_') {
                    wordGuessed = false;
                    break;
                }
            }
        }

        // End game message
        if (wordGuessed) {
            System.out.println("Congratulations! You guessed the word: " + secretWord);
        } else {
            System.out.println("Game over! The word was: " + secretWord);
        }

        scanner.close();
    }
}
