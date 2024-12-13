import java.util.Scanner;

public class SafeInput
{

    /**
     * get a String value from the user which must be at least one character
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return String that is at least one character
     */

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retVal = "";

        do
        {
            System.out.print(prompt + ": ");
            retVal = pipe.nextLine();
            if(retVal.isEmpty())
            {
                System.out.println("You must enter at least one character!");
            }

        }while(retVal.isEmpty());
        return retVal;

    }
    /**
     * gets an unconstrained int value from the user
     * @param pipe Scanner to use for input
     * @param prompt prompt that tells the user what to input
     * @return an unconstrained int value
     */

    public static int getInt(Scanner pipe, String prompt)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer, not " + trash);
            }

        }while(!done);

        return retVal;

    }
    /**
     * gets an unconstrained double value from the user
     * @param pipe Scanner to use for input
     * @param prompt prompt that tells the user what to input
     * @return an unconstrained double value
     */

    public static double getDouble(Scanner pipe, String prompt)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double, not " + trash);
            }

        }while(!done);

        return retVal;

    }
    /**
     * gets an int value from the user within the specified inclusive range
     * @param pipe Scanner to use for input
     * @param prompt prompt that tells the user what to input
     * @return an int value within the specified inclusive range
     */

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + "[low - high]: ");
            if (pipe.hasNextInt())
            {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a valid integer in range [" + low + " - " + high + "]: not " + retVal);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer, not " + trash);
            }

        }while(!done);

        return retVal;

    }
    /**
     * gets a double value from the user within the specified inclusive range
     * @param pipe Scanner to use for input
     * @param prompt prompt that tells the user what to input
     * @return a double value within the specified inclusive range
     */

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + "[low - high]: ");
            if (pipe.hasNextDouble())
            {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("You must enter a valid double in range [" + low + " - " + high + "]: not " + retVal);
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double, not " + trash);
            }

        }while(!done);

        return retVal;

    }

    /**
     * get a String value from the user of [YyNn]
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return true for Yy (yes) and false for Nn (no)
     */

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean retVal = false;
        String input = "";
        boolean done = false;


        do
        {
            System.out.print(prompt + "[YyNn]: ");
            input = pipe.nextLine();
            if(input.isEmpty())
            {
                System.out.println("You must enter Y or N!");
            }
            else if(input.equalsIgnoreCase("Y") )
            {
                retVal = true;
                done = true;
            }
            else if(input.equalsIgnoreCase("N") )
            {
                retVal = false;
                done = true;
            }
            else
            {
                System.out.println("You must enter Y or N! Not " + input);
            }


        }while(!done);
        return retVal;

    }
    /**
     * get a String value from the user that matches the required format
     *
     * @param pipe scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @param regEx java style regex pattern to constrain the input
     * @return String that matches supplied pattern
     */

    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String retVal = "";
        boolean gotValue = false;


        do
        {
            System.out.print(prompt + ": ");
            retVal = pipe.nextLine();
            if(retVal.matches(regEx))
            {
                gotValue = true;
            }
            else
            {
                System.out.println("\n" + retVal + " must match the pattern " + regEx);
                System.out.println("Please try again.");
            }

        }while(!gotValue);

        return retVal;


    }
    /**
     * get a String value from the user and place it in a header
     *
     * @param pipe   scanner to use to read the input
     * @param prompt prompt to tell the user what to input
     * @return Makes a header out of asterisks above and below the input text
     */

    public static void prettyHeader(Scanner pipe, String prompt)
    {
        String retVal = "";
        final int HEADER_WIDTH = 54;
        int msgLength = 0;
        int remainder = 0;
        int before = 0;
        int after = 0;

        System.out.println(prompt + ": ");
        retVal = pipe.nextLine();
        msgLength = retVal.length();

        if (msgLength > HEADER_WIDTH)
        {
            System.out.println("Failed to create header, msg is too long!");
            return;
        }
        else {
            remainder = HEADER_WIDTH - msgLength;
            if (remainder % 2 == 0)
            {
                before = remainder / 2;
                after = remainder /2;
            }
            else
            {
                before = remainder / 2;
                after = remainder / 2 + 1;
            }
            for (int row = 0; row < 60; row++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.print("***");

            for (int row = 0; row < before; row++) {
                System.out.print(" ");
            }
            System.out.print(retVal);

            for (int row = 0; row < after; row++) {
                System.out.print(" ");
            }

            System.out.print("***");
            System.out.println();
            for (int row = 0; row < 60; row++) {
                System.out.print("*");
            }
        }

    }


}
