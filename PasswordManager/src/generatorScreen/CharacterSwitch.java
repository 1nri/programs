package generatorScreen;

/**
 *
 * @author henrijuvonen
 *
 * created: 30.7.2015
 *
 * edited 14.6.2016
 *
 * edited 16.7.2016 - added comments and refined code
 *
 * edited 20.7.2016 - added switch rule for all finnish alpahabets and included
 * logic to switch the character to upper case character. created a method to
 * create random double instead of having it  created for every character
 * separately
 *
 * edited 26.7.2016 - added comments, adjusted the logic followed in switching
 * the character
 *
 * 22.7.2019 - re-imported to netbeans, tested git version control, updated
 * comments
 * all seems to be fine at this point of time
 *
 * 23.7.2019 - re-evaluated part of the functionality and comments
 *
 */


// the main functionality of this class is to semi-randomly change the input
// characters to other characters
public class CharacterSwitch {

    // default initializer
    public CharacterSwitch()
    {

    }

    // standard call for Math.random() functionality
    private double rndm()
    {
        double rndm = Math.random() * 1;
        return rndm;
    }

    // method for switching characters provided as input
    public char charToSwitch(char c)
    {

        boolean numeric = false, special = false, upper = false;
        double rndm = rndm();
        Character ch;
        ch = c;

        // switch rules for each finnish character
        //
        // suggestion for improvement : add numbers and a couple of
        // special characters, too?
        switch (c)
        {

            case 'a':

                if (rndm > 0.5)
                {
                    ch = '4';
                    numeric = true;
                }

                else if(rndm > 0.8)
                    ch = 'ä';

                else if(rndm > 0.9)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }
                break;

            case 'b':

                if(rndm > 0.6)
                {
                    ch = '6';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }
                break;

            case 'c':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'd':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'e':

                if (rndm < 0.3)
                {
                    ch = '3';
                    numeric = true;
                }

                else if(rndm >= 0.3 && rndm < 0.6)
                {
                    ch = '€';
                    special = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }
                break;

            case 'f':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'g':

                if (rndm < 0.4)
                {
                    ch = '6';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }
                break;

            case 'h':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'i':

                if (rndm < 0.4)
                {
                    ch = '1';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                else
                {
                    ch = '!';
                    special = true;
                }
                break;


            case 'j':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'k':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'l':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'm':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'n':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'o':

                if (rndm < 0.4)
                {
                    ch = '0';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'p':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'q':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'r':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 's':

                if (rndm < 0.4)
                {
                    ch = '5';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                else {
                    ch = '$';
                    special = true;
                }

                break;

            case 't':

                if (rndm < 0.4)
                {
                    ch = '7';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;


            case 'u':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'v':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'w':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'x':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'y':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;


            case 'z':

                if (rndm < 0.4)
                {
                    ch = '2';
                    numeric = true;
                }

                else if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;


            case 'å':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'ä':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            case 'ö':

                if(rndm > 0.8)
                {
                    ch = Character.toUpperCase(ch);
                    upper = true;
                }

                break;

            default:

                break;
        }

        return ch;
    }
}
