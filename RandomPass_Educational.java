/*
Name: Alexander Georgiadis (RezTech)
Date Started: 2/25/2016
Description: This is a partialy complex random password generator, to be used with RTPWJ (Ready To Program With Java).
*/

import java.awt.*;
import hsa.Console;

public class RandomPass_Educational
{
    static Console c;

    public static void main (String[] args)
    {
	//Open The Console
	c = new Console ();

	//Variables
	int length = 0; //length needs to be assigned a value before it can be used to store the return of a method

	//Let's make out password
	length = getLength (length);
	makePass (length);
    }


    public static int getLength (int passLength)
    {
	c.println ("Welcome to JARPG!"); //Rust Another Random Password Generator
	c.println ("How long would you like the password to be?");
	passLength = c.readInt ();

	//Make sure we are not working with 0 or negative numbers
	while (passLength <= 0)
	{
	    c.clear (); //Clean up any old promps so we don't fill the screen
	    c.println ("Please enter a number greater then 0!");
	    passLength = c.readInt ();
	    c.clear ();
	}
	return passLength; //We will be needing this int later
    }


    public static void makePass (int passLength)  //We can use passLength twice since it is a local variable, unlike legnth, which is passed onto this method
    {
	//Varibales
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

	//Print our output
	c.clear ();
	c.println ("Your randomly generated password with " + passLength + " characters is:"); //Just a nice prompt so the customer knows what the output is

	for (int i = 0 ; i <= (passLength - 1) ; i++) //Another loop, just to print the password
	{
	    c.print (passChar [i]); //Need to use print here, so it is not one char per line
	}
    }
}
