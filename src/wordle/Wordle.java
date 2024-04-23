package wordle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wordle {

    //⬛🟨🟩
    String fileName = "wordle/resources/dictionary.txt";
    //String fileName = "wordle/resources/extended-dictionary.txt";
    List<String> dictionary = null;
    final int num_guesses = 5;
    final long seed = 42;
    //Random rand = new Random(seed);
    Random rand = new Random();

    static final String winMessage = "CONGRATULATIONS! YOU WON! :)";
    static final String lostMessage = "YOU LOST :( THE WORD CHOSEN BY THE GAME IS: ";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";

    Wordle() {

        this.dictionary = readDictionary(fileName);

        System.out.println("dict length: " + this.dictionary.size());
        System.out.println("dict: " + dictionary);

    }

    public static void main(String[] args) {
        Wordle game = new Wordle();

        String target = game.getRandomTargetWord();

        //System.out.println("target: " + target);

        game.play(target);

    }

    public void play(String target) {
        // TODO
        // TODO: You have to fill in the code
        /*for(int i = 0; i < num_guesses; ++i) {
            String guess = getGuess();

            if(guess.equals(target)) { // you won!
                win(target);
                return;
            }

            String[] guessArray = new String[5];
            String[] targetArray = new String[5];
            boolean[] isGreen = new boolean[5]; // track matched (green) positions

            for(int j = 0; j < 5; j ++)
            {
                guessArray[j] = String.valueOf(guess.charAt(j));
                targetArray[j] = String.valueOf(target.charAt(j));
            }

            // the hint is a string where green="+", yellow="o", grey="_"
            // didn't win ;(
            String [] hint = {"⬛", "⬛", "⬛", "⬛", "⬛"};

            /*for (int k = 0; k < 5; k++) {
                // TODO:
                if(targetArray[k].equals(guessArray[k]))
                {
                    hint[k] = "+";
                }
            }


            for (int k = 0; k < 5; k++) {
                // TODO:
                if(targetArray[k].equals(guessArray[k]))
                {
                    hint[k] = "\uD83D\uDFE9";
                    isGreen[k] = true;
                    targetArray[k] = "";
                }
            }
            // set the arrays for yellow (present but not in right place), grey (not present)
            // loop over each entry:
            //  if hint == "+" (green) skip it
            //  else check if the letter is present in the target word. If yes, set to "o" (yellow)
            for (int k = 0; k < 5; k++) {
                // TODO:
                for(int j = 0; j < 5; j ++)
                {
                    if(guessArray[k].equals(targetArray[j]) && !(hint[j].equals("\uD83D\uDFE9")))
                    {
                        hint[k] = "\uD83D\uDFE8";
                        targetArray[k] = "";
                        break;
                    }
                }
            }*/

        for (int i = 0; i < num_guesses; ++i) {
            String guess = getGuess();

            if (guess.equals(target)) {
                win(target);
                return;
            }

            String[] guessArray = guess.split("");
            String[] targetArray = target.split("");
            boolean[] isGreen = new boolean[5]; // track matched (green) positions

            // Initialize hints array with all misses
            String[] hint = {"⬛", "⬛", "⬛", "⬛", "⬛"};

            // First, check for correct positions (green)
            for (int k = 0; k < 5; k++) {
                if (guessArray[k].equals(targetArray[k])) {
                    hint[k] = "\uD83D\uDFE9"; // green square
                    isGreen[k] = true;
                    targetArray[k] = ""; // remove the matched letter from the target
                }
            }

            // Next, check for present but misplaced letters (yellow)
            for (int k = 0; k < 5; k++) {
                if (!isGreen[k] && target.contains(guessArray[k])) {
                    int targetIndex = target.indexOf(guessArray[k]);
                    if (targetIndex != -1 && !isGreen[targetIndex]) {
                        hint[k] = "\uD83D\uDFE8"; // yellow square
                        targetArray[targetIndex] = ""; // mark as processed
                    }
                }
            }





            // after setting the yellow and green positions, the remaining hint positions must be "not present" or "_"
            System.out.println("hint: " + Arrays.toString(hint));


            // check for a win
            int num_green = 0;
            for(int k = 0; k < 5; ++k) {
                if(hint[k].equals("\uD83D\uDFE9")) num_green += 1;
            }
            if(num_green == 5) {
                 win(target);
                 return;
            }
        }

        lost(target);
    }

    public void lost(String target) {
        System.out.println();
        System.out.println(lostMessage + target.toUpperCase() + ".");
        System.out.println();

    }
    public void win(String target) {
        System.out.println(ANSI_GREEN_BACKGROUND + target.toUpperCase() + ANSI_RESET);
        System.out.println();
        System.out.println(winMessage);
        System.out.println();
    }

    public String getGuess() {
        Scanner myScanner = new Scanner(System.in, StandardCharsets.UTF_8.displayName());  // Create a Scanner object
        System.out.println("Guess:");

        String userWord = myScanner.nextLine();  // Read user input
        userWord = userWord.toLowerCase(); // covert to lowercase

        // check the length of the word and if it exists
        while ((userWord.length() != 5) || !(dictionary.contains(userWord))) {
            if ((userWord.length() != 5)) {
                System.out.println("The word " + userWord + " does not have 5 letters.");
            } else {
                System.out.println("The word " + userWord + " is not in the word list.");
            }
            // Ask for a new word
            System.out.println("Please enter a new 5-letter word.");
            userWord = myScanner.nextLine();
        }
        return userWord;
    }

    public String getRandomTargetWord() {
        // generate random values from 0 to dictionary size
        return dictionary.get(rand.nextInt(dictionary.size()));
    }
    public List<String> readDictionary(String fileName) {
        List<String> wordList = new ArrayList<>();

        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line
            while ((strLine = reader.readLine()) != null) {
                wordList.add(strLine.toLowerCase());
            }
            //Close the input stream
            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return wordList;
    }
}
