import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class allows users to pass various command line arguments to encrypt or 
 * decrypt messages either typed directly into the command line or in a specified 
 * file, using either a Vigenere or Caesar Cipher.
 * @author johnbalson
 * @version 2/24/2021
 */
public class Main
{
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println(usage());
		}
		else if(args[0].equals("-s"))
		{
			if(!(args.length == 5))
			{
				System.out.println(usage());
			}
			else if(args[3].equals("-v"))
			{
				if(args[4].equals("-e"))
				{
					VigenereCipher v = new VigenereCipher();
					System.out.println(v.encrypt(args[2], args[1]));
				}
				else if(args[4].equals("-d"))
				{
					VigenereCipher v = new VigenereCipher();
					System.out.println(v.decrypt(args[2], args[1]));
				}
				else
				{
					System.out.println(usage());
				}
			}
			else if(args[3].equals("-c"))
			{
				if(args[4].equals("-e"))
				{
					CaesarCipher c = new CaesarCipher();
					try
					{
						System.out.println(c.encrypt(args[2], args[1]));
					}
					catch(NumberFormatException e)
					{
						System.out.println("Your key must be an int when using a CaesarCipher");
					}
				}
				else if(args[4].equals("-d"))
				{
					CaesarCipher c = new CaesarCipher();
					try
					{
						System.out.println(c.decrypt(args[2], args[1]));
					}
					catch(NumberFormatException e)
					{
						System.out.println("Your key must be an int when using a CaesarCipher");
					}
				}
				else
				{
					System.out.println(usage());
				}
			}
			else
			{
				System.out.println(usage());
			}
		}
		else if(args[0].equals("-f"))
		{
			if(!(args.length == 4))
			{
				System.out.println(usage());
			}
			else if(args[2].equals("-v"))
			{
				if(args[3].equals("-e"))
				{
					Cipher v = new VigenereCipher();
					System.out.println(encryptFile(args[1], v));
				}
				else if(args[3].equals("-d"))
				{
					Cipher v = new VigenereCipher();
					System.out.println(decryptFile(args[1], v));
				}
				else
				{
				System.out.println(usage());
				}
			}
			else if(args[2].equals("-c"))
			{
				if(args[3].equals("-e"))
				{
					Cipher c = new CaesarCipher();
					System.out.println(encryptFile(args[1], c));
				}
				else if(args[3].equals("-d"))
				{
					Cipher c = new CaesarCipher();
					System.out.println(decryptFile(args[1], c));
				}
				else
				{
					System.out.println(usage());
				}
			}
		}
		else
		{
			System.out.println(usage());
		}
		
	}
	
	/**
	 * This method encrypts files of any length, given a Cipher c. Each line of
	 * the file must start with the key used to encrypt that line, followed by
	 * a space, followed by the message to be encrypted.
	 * @param filename the name of the file to be encrypted
	 * @param c the Cipher to be used to encrypt
	 * @return the encrypted version of all the messages in the file, each on its own line
	 * @throws FileNotFoundException
	 */
	private static String encryptFile(String filename, Cipher c)
	{
		Scanner inFile;
		try
		{
			inFile = new Scanner(new File(filename));
			String key;
			String message;
			String encryptedFile = "";
			
			while(inFile.hasNextLine())
			{
				key = inFile.next();
				message = inFile.nextLine();
				encryptedFile = encryptedFile + c.encrypt(message, key) + "\n";
			}
			return encryptedFile;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: file \"" + filename + "\" does not exist");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Your key must be an int when using a CaesarCipher");
		}
		return "";
	}
	
	/**
	 * This method decrypts files of any length, given a Cipher c. Each line of
	 * the file must start with the key to be used to decrypt that line, 
	 * followed by a space, followed by the message to be decrypted.
	 * @param filename the name of the file to be decrypted
	 * @param c the Cipher to be used to decrypt
	 * @return the decrypted version of all the messages in the file, each on its own line
	 * @throws FileNotFoundException
	 */
	private static String decryptFile(String filename, Cipher c)
	{
		try {
			Scanner inFile = new Scanner(new File(filename));
			String key;
			String message;
			String decryptedFile = "";
			
			while(inFile.hasNextLine())
			{
				key = inFile.next();
				message = inFile.nextLine();
				decryptedFile = decryptedFile + c.decrypt(message, key) + "\n";
			}
			return decryptedFile;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: file \"" + filename + "\" does not exist");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Your key must be an int when using a CaesarCipher");
		}
		return "";
	}
	
	/**
	 * This method returns the usage instructions for this class.
	 * @return the possible command line arguments and what they do
	 */
	private static String usage()
	{
		return "Usage:\n	Main (-f <filename> | -s <key> <message>) (-v | -c)"
			 + "(-e | -d)\n\n-f	message to encrypt/decrypt is contained in a "
			 + "file.\n 	each line of the file must start with the key used to "
			 + "encrypt that\n 	line, followed by a space, followed by the message"
			 + " to be encrypted.\n-s	message to encrypt/decrypt is a string "
			 + "inputed as an argument\n-v	message to encrypt/decrypt uses a "
			 + "Vigenere Cipher\n-c	message to encrypt/decrypt uses a Caesar "
			 + "Cipher\n-e	encrypt message\n-d	decrypt message";
	}
}
