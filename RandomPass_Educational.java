/*
Name: Alexander Georgiadis (RezTech)
Date Started: 2/25/2016
Last Updated: 5/2/2016
Description: This is a partially complex random password generator, to be used with RTPWJ (Ready To Program With Java).
*/

//Needed for the I/O related code
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import hsa.Console;

public class RandomPass_Educational {
    static Console c;

    public static void main (String[] args)
    {
        //Open The Console
        c = new Console ();

        //Variables
        int length = 0, count = 0; //Value needs to be assigned a value before it can be used to store the return of a method

        //Let's make out password
        length = getLength (length);
        count = getCount (count);
        makePass (length, count);
    }


    public static int getLength (int passLength)
    {
        //Variables
        String passLengthStr;
        boolean isValid = false;

        //Prompt the customer
        c.println ("Welcome to JARPG!"); //Just Another Random Password Generator
        do //do while statements will prevent the need to pre-assign a value as a placeholder
        {
            c.println ("How long would you like the password to be?");
            passLengthStr = c.readString ();

            //Check to see if the user inputed an valid integer
            try
            {
                passLength = (Integer.parseInt (passLengthStr)); //If this cannot be done, it will be caught, and we will re-prompt
                if (passLength > 0) //Make sure that the int is greater then 0
                {
                    isValid = true;
                }
                else //Make sure we print an error if the number is negative, not just invalid to convert
                {
                    c.clear ();
                    c.println ("Please chose a valid integer (A number between 1 - 9999999)!");
                }
            }
            catch (NumberFormatException convert)
            {
                c.clear ();
                c.println ("Please chose a valid integer (A number between 1 - 9999999)!");
            }
        }
        while (isValid != true);
        return passLength; //We will be needing this int later
    }


    public static int getCount (int passCount)
    {
        //Variables
        String passCountStr;
        boolean isValid = false;

        //Prompt the customer
        do
        {
            c.println ("Okay, now how many unique passwords should we generate?");
            passCountStr = c.readString ();

            //Check to see if the user inputed an valid integer
            try
            {
                passCount = (Integer.parseInt (passCountStr)); //If this cannot be done, it will be caught, and we will re-prompt
                if (passCount > 0) //Make sure that the int is greater then 0
                {
                    isValid = true;
                }
                else //Make sure we print an error if the number is negative, not just invalid to convert
                {
                    c.clear ();
                    c.println ("Please chose a valid integer (A number between 1 - 9999999)!");
                }
            }
            catch (NumberFormatException convert)
            {
                c.clear ();
                c.println ("Please chose a valid integer (A number between 1 - 9999999)!");
            }
        }

        while (isValid != true);
        return passCount; //We will be needing this int later
    }


    public static void makePass (int passLength, int passCount)  //We can use passLength twice since it is a local variable, unlike length, which is passed onto this method
    {
        //Variables
        char passChar[] [] = new char [passLength] [passCount], genAgain, option; //Create an array for us to store the password in, as well as some checks for our inputs
        double ASCII;

        //Start our password generation
        for (int f = 0 ; f <= (passCount - 1) ; f++) //Using a 3D array, we can generate X number of passwords and store them
        {
            for (int i = 0 ; i <= (passLength - 1) ; i++)
            {
                ASCII = (Math.random () * 140); //The maximum value is 126, so we multiply by it by 140 (.9*140 = 126)

                while (ASCII > 126 || ASCII < 33) //Make sure only visible characters are generated (http://tinyurl.com/RTPJW-ASCII)
                {
                    ASCII = (Math.random () * 140);
                }
                passChar [i] [f] = (char) (ASCII); //Truncate our double, and place it into a char, and move it from the buffer to the array (!-~ [See above URL])
            }
        }

        //Prompt our customer to see what format they would like their output in
        do
        {
            c.clear ();
            c.println ("Which of the following formats would you like your password to be displayed using?");
            c.println ("1. Text File");
            c.println ("2. Console");
            option = c.getChar ();
        }

        while (option != '1' && option != '2');

        switch (option) //We can use either a switch, or a combination of if, else if, and else. We will stick with a switch, it looks cleaner
        {
            case '1':
                {
                    //Variable
                    String fileName;

                    //Prompt for the .TXT name
                    c.clear ();
                    c.println ("What would you like the file to be called?");
                    fileName = c.readString ();

                    //Output out password to a .TXT (To allow for copy and pasting)
                    try
                    {
                        PrintWriter out = new PrintWriter (new FileWriter (fileName + ".txt")); //Create the text file with the specified name

                        out.print ("The following password(s) where generated using RezTech's JARPG:"); //Quick branding on the top of the outputed file
                        for (int f = 0 ; f <= (passCount - 1) ; f++)
                        {
                            out.print ("\n"); //Make sure there is a space between each password
                            for (int i = 0 ; i <= (passLength - 1) ; i++) //Another loop, just to print the password
                            {
                                out.print (passChar [i] [f]); //Need to use print here, so it is not one char per line
                            }
                        }

                        out.close (); //Don't need to keep the file open anymore, so close it
                    }
                    catch (IOException output)
                    {
                        c.clear ();
                        c.println ("Error outputing file!\nPlease check to see that you have write acsess to the disk, or that the disk is not full!"); //Seing as RTPWJ only seems to catch this exception, we will have to keep is fairly generic

                        //Actually give some time before quitting so the customer can read the prompt
                        try
                        {
                            Thread.sleep (6000); //6000 MS = 6 S (MS/1000=S)
                        }
                        catch (InterruptedException interrupt_output)
                        {
                            System.exit (0); //If we can't delay the program shutdown, just forcefully close it
                        }
                        System.exit (0);
                    }

                    //Print our output
                    c.clear ();
                    c.println ("Your randomly generated password has been saved to the program runtime directory!"); //Just a nice prompt so the customer knows where the output is
                    break;
                }

            case '2':
                {
                    c.clear ();
                    c.print ("Your randomly generated password(s) are:"); //Just a nice prompt so the customer knows what the output is

                    for (int f = 0 ; f <= (passCount - 1) ; f++)
                    {
                        c.print ("\n"); //Make sure there is a space between each password
                        for (int i = 0 ; i <= (passLength - 1) ; i++) //Another loop, just to print the password
                        {
                            c.print (passChar [i] [f]); //Need to use print here, so it is not one char per line
                        }
                    }
                    c.print ("\n"); //Make sure that our next prompt will be on the next line
                    break;
                }
        }

        //Multi-generation support
        do
        {
            c.println ("Would you like to generate another password?");
            c.println ("1. Yes");
            c.println ("2. No");
            genAgain = c.getChar ();
            c.clear ();
        }

        while (genAgain != ('1') && genAgain != ('2'));  //Need to use and here, because both conditions need to fail, not just one

        switch (genAgain)
        {
            case '1':
                {
                    passLength = getLength (passLength); //We need to store the password length again, this method should work just fine
                    passCount = getCount (passCount);
                    makePass (passLength, passCount);
                    break;
                }
            case '2':
                {
                    c.println ("Thank you for using JARPG!");

                    //Automatically quit the program after 4 seconds of playing the credits
                    try
                    {
                        Thread.sleep (4000); //4000 MS = 4 S (MS/1000=S)
                    }
                    catch (InterruptedException interuppt_exit)  //Just in case something tries to re-wake the thread
                    {
                        System.exit (0); //If we can't delay the program shutdown, just forcefully close it
                    }
                    System.exit (0); //Because we are exiting the program, we do not need a break, as the code will be terminated
                }
                //Default statement is not needed here, as we force the response of either 1 or 2 in the previous statement
        }
    }
}
