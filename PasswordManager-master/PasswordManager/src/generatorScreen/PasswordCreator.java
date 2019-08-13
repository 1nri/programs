package generatorScreen;

/**
 *
 * @author henrijuvonen
 *
 * created: 29.7.2015
 *
 * edited 14.6.2016
 *
 * edited 15.7.2016 - added comments and refined code
 *
 * edited 19.7.2016 - added comments, refined the code and started the progress
 * to create passwords that are more simple to utilize
 *
 * 20.7.2016 - commented the BufferedReader and its' corresponding import out
 *
 * 26.7.2016 - commented on the logic about the random for loop's if-else part,
 * created a new for loop to go through the characters in order
 *
 * 22.7.2019 : re-imported to netbeans, tested git version control, updated
 * comments
 *
 * 23.7.2019 : refinements and reformatting. all seems to be fine at this point
 * of time
 *
 */

// import java.io.*;

// a class that the UI calls for processing the password generation
// based on the input characters
public class PasswordCreator {

    // temporarily hidden variable to use with trim() later on
    // private static String[] pwdString;

    //array of characters for password
    private static char[] pwdChars;

    // variable to use to randomize the characters from String input
    private static String[] count;

    // variable to save input with
    private static String mem;

    // variable to save the character length of String input
    private static int letters;

    // old variable to save input from terminal
    //private static BufferedReader br = new BufferedReader(new
    //    InputStreamReader(System.in));

    // introduction for CharacterSwitch object
    private static CharacterSwitch cs;

    // protected static String invalidInput = new String(")!\"(&|\\`'<>");

    /**
     *
     * @param pw
     * @param random
     * @return
     */

    public static String createPassword(String pw, boolean random) {

        cs = new CharacterSwitch();

        // this part that has commented out might be from the original
        // command line version of this application
        /**
         * System.out.println();
         * System.out.println("This is a very simple password creator");
         * System.out.println("that will ask for your input next. The given
         * input is then");
         * System.out.println("used to create the password with a randomly
         * re-sorting algorithm.");
         * System.out.println();
         * System.out.println("The following characters are invalid as input:");
         * System.out.println(invalidInput);
         * System.out.println();
         * System.out.println("Please provide a series of characters to use as a
         * source");
         * System.out.println("for your password:");
         */

        /* to be figured out whether or not this try-catch is needed at all */
        try
        {

            // old functionality for input from terminal
            // mem = br.readLine();
            mem = pw;

            // must be initialized to 0 (or anything else) in order to avoid
            // compilation errors
            int charCount = 0;
            letters = mem.length();
            pwdChars = new char[letters];
            count = new String[letters];

            if(random)
            {
                // this for loop goes through each given character in a random
                // order
                for (int i = 0; i < letters; i++)
                {

                    // double with random value
                    double rndm = Math.random() * letters;

                    // previous double forced to integer
                    int rndmInt = (int) rndm;

                    /**
                     * must be checked whether or not this is needed at all /
                     * it seems that this is needed, if the input is through
                     * in random order
                     */
                    if (count[rndmInt] == null)
                    {

                        // character by character the input string is sent to
                        // the method switchOrNot(char c)
                        pwdChars[i] = switchOrNot(mem.charAt(rndmInt));
                        count[rndmInt] = Integer.toString(0);
                        charCount = i + 1;
                    }

                    /* same here */
                    else
                    {
                        i--;
                    }
                }
            }
            else
            {
                // this for loop goes through each given character in a random
                // order
                for (int i = 0; i < letters; i++)
                {

                    /**
                     * optional algorithm that preserves the original order of
                     * characters - way more simple and easier to remember
                     */
                    pwdChars[i] = switchOrNot(mem.charAt(i));
                }
            }

            // print out to terminal - not necessary for GUI version but
            // may be useful in  testing and validation
            System.out.println();
            letters = pwdChars.length;
            System.out.print("The input you provided consists of ");
            System.out.print(charCount);
            System.out.println(" characters.");
            System.out.println("Password should consist of the same amount of "
                    + "characters.");
            System.out.println();
            System.out.println("Password created:");
            System.out.println();

            for (int j = 0; j < letters; j++)
            {
                System.out.print(pwdChars[j]);
                charCount = j + 1;
            }

            System.out.println();
            System.out.print("The password consists of ");
            System.out.print(charCount);
            System.out.print(" characters.");
            System.out.println();
            //terminal output ends here

            // string to be returned to the calling method
            String passwd = new String(pwdChars);
            return passwd;
        }

        // scenario where no input has been provided
        // suggestion for improvement : add functionality where a reasonably
        // low number would end up being used as a length of password that is
        // to be generated
        catch (Exception e)
        {
            System.out.println("Application requires one word of input.");
            return null;
        }
    }

    // a method to randomize the switching of characters by a 0.3 factor
    private static char switchOrNot(char candidate) {

        double random = Math.random() * 1;

        if (random < 0.3)
        {
            candidate = cs.charToSwitch(candidate);
        }

        return candidate;
    }
}
