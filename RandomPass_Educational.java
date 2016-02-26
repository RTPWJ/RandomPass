/*
Name: Alexander Georgiadis (RezTech)
Date Started: 2/25/2016
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
        c.println ("Welcome to JARPG!"); //Just Another Random Password Generator
        c.println ("How long would you like the password to be?");
        passLength = c.readInt ();
        c.clear (); //Clean up any old promps so we don't fill the screen

        //Make sure we are not working with 0 or negative numbers
        while (passLength <= 0)
        {
            c.println ("Please enter a number greater then 0!");
            passLength = c.readInt ();
            c.clear ();
        }
        return passLength; //We will be needing this int later
    }


    public static void makePass (int passLength)  //We can use passLength twice since it is a local variable, unlike length, which is passed onto this method
    {
        //Varibales
        int option;
        char passChar[] = new char [passLength]; //Create an array for us to store the password in
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
        c.println ("Which of the following formats would you like your password to be displayed using?");
        c.println ("1. Text File");
        c.println ("2. Console");
        option = c.readInt ();

        switch (option) //We can use either a switch, or a combination of if, else if, and else. We will stick with a switch, it looks cleaner
        {
            case 1:
                {
                    //Variable
                    String fileName;
                    
                    //Prompt for the .TXT name
                    c.println("What would you like the file to be called?");
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

                        out.close ();
                    }
                    catch (IOException e)
                    {
                        c.clear ();
                        c.println ("General I/O exception! Please re-launch the program.");
                        System.exit (0);
                    }

                    //Print our output
                    c.clear ();
                    c.println ("Your randomly generated password has been saved to the program runtime directory!"); //Just a nice prompt so the customer knows what the output is
                    break;
                }

            case 2:
                {
                    c.clear ();
                    c.println ("Your randomly generated password is:"); //Just a nice prompt so the customer knows what the output is

                    for (int i = 0 ; i <= (passLength - 1) ; i++) //Another loop, just to print the password
                    {
                        c.print (passChar [i]); //Need to use print here, so it is not one char per line
                    }
                    break;
                }
            default:
                {
                    c.clear ();
                    c.println ("Please read the following prompt, and input either a 1, or a 2 depending on which output option you would like.");
                    makePass (passLength); //We will need to manually re-pass this to make sure that it remembers the length of the password we want to generate
                }
        }
    }
}
