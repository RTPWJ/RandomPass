/*
Name: Alexander Georgiadis (RezTech)
Date Started: 2/25/2016
Last Updated: 2/26/2016
Description: This is a partially complex random password generator, to be used with RTPWJ (Ready To Program With Java).
*/

import java.awt.*;
import java.io.*; //Needed for the I/O related code
import hsa.Console;

public class RandomPass_Educational
{
    static Console c;

    public static void main (String[] args)
    {
        //Open The Console
        c = new Console ();

        //Variables
        int length = 0; //Length needs to be assigned a value before it can be used to store the return of a method

        //Let's make out password
        length = getLength (length);
        makePass (length);
    }


    public static int getLength (int passLength)
    {
        //Variables
        String passLengthStr;
        boolean isNumber = false;

        //Prompt the customer
        c.println ("Welcome to JARPG!"); //Just Another Random Password Generator
        while (isNumber != true)
        {
            c.println ("How long would you like the password to be? (Between 1 - 9999999)");
            passLengthStr = c.readString ();

            //Check to see if the user actualy inputed an int
            try
            {
                passLength = (Integer.parseInt (passLengthStr)); //If this cannot be done, it will be caught, and we will re-prompt
                if (passLength > 0) //Make sure that the int is greater then 0
                {
                    isNumber = true;
                }
                c.clear ();
            }
            catch (NumberFormatException e)
            {
                c.clear ();
                c.println ("Please insert a number greater then 0!");
            }
        }
        return passLength; //We will be needing this int later
    }


    public static void makePass (int passLength)  //We can use passLength twice since it is a local variable, unlike length, which is passed onto this method
    {
        //Varibales
        char passChar[] = new char [passLength], genAgain = '0', option = '0'; //Create an array for us to store the password in, as well as some checks for our inputs
        double ASCII;

        //Start our password generation
        for (int i = 0 ; i <= (passLength - 1) ; i++)
        {
            ASCII = (Math.random () * 140); //The maximum value is 126, so we multiply by it by 140 (.9*140 = 126)

            while (ASCII > 126 || ASCII < 33) //Make sure only visible characters are generated (http://tinyurl.com/RTPJW-ASCII)
            {
                ASCII = (Math.random () * 140);
            }
            passChar [i] = (char) (ASCII); //Truncate our double, and place it into a char, this will generate a random char (!-~ [See above URL])
        }

        //Prompt our customer to see what format they would like their output in
        while (option != '1' && option != '2')
        {
            c.println ("Which of the following formats would you like your password to be displayed using?");
            c.println ("1. Text File");
            c.println ("2. Console");
            option = c.getChar ();
        }

        switch (option) //We can use either a switch, or a combination of if, else if, and else. We will stick with a switch, it looks cleaner
        {
            case '1':
                {
                    //Variable
                    String fileName;

                    //Prompt for the .TXT name
                    c.println ("What would you like the file to be called?");
                    fileName = c.readString ();

                    //Output out password to a .TXT (To allow for copy and pasting)
                    try
                    {
                        PrintWriter out = new PrintWriter (new FileWriter (fileName + ".txt"));

                        out.println ("The following password was generated using RezTech's JARPG:");
                        for (int i = 0 ; i <= (passLength - 1) ; i++) //Another loop, just to print the password
                        {
                            out.print (passChar [i]); //Need to use print here, so it is not one char per line
                        }

                        out.close (); //Don't need to keep the file open anymore, so close it
                    }
                    catch (IOException e)
                    {
                        c.clear ();
                        c.println ("General I/O exception! Please re-launch the program."); //Seing as RTPWJ only seems to catch this exception, we will have to keep is fairly generic
                        System.exit (0);
                    }

                    //Print our output
                    c.clear ();
                    c.println ("Your randomly generated password has been saved to the program runtime directory!"); //Just a nice prompt so the customer knows what the output is
                    break;
                }

            case '2':
                {
                    c.clear ();
                    c.println ("Your randomly generated password is:"); //Just a nice prompt so the customer knows what the output is

                    for (int i = 0 ; i <= (passLength - 1) ; i++) //Another loop, just to print the password
                    {
                        c.print (passChar [i]); //Need to use print here, so it is not one char per line
                    }
                    c.println ("\n"); //Make sure that our next prompt will be on the next line
                    break;
                }
        }

        //Multi-generation support
        while (genAgain != ('1') && genAgain != ('2')) //Need to use and here, because both conditions need to fail, not just one
        {
            c.println ("Would you like to generate another password?");
            c.println ("1. Yes");
            c.println ("2. No");
            genAgain = c.getChar ();
            c.clear ();
        }

        switch (genAgain)
        {
            case '1':
                {
                    passLength = getLength (passLength); //We need to store the password length again, this method should work just fine
                    makePass (passLength);
                    break;
                }
            case '2':
                {
                    System.exit (0);
                }
        }
    }
}
