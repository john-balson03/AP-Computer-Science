

/**
 * This class provides methods to encrypt and decrypt messages using a Vigenere
 * Cipher. A Vigenere Cipher changes each letter in a message to a letter some
 * number away alphabetically from the original letter. The number of spaces
 * away is based off of the position in the alphabet of the corresponding letter
 * in the keyword, with "a" representing a shift of at 0. For instance if the
 * first letter of the keyword is "b," the first letter in the message will be
 * shifted by 1. If the keyword is shorter than the message, it will be repeated
 * until the end of the message is reached.
 * @author johnbalson
 * @version 1/28/2020
 */
public class VigenereCipher extends Cipher
{	
	/** This method encrypts a message using the Vigenere Cipher algorithm. The
	 * message returned will have all whitespace and special characters removed
	 * (non-letters) and will be in all uppercase letters.
	 * @param plaintext the message to be encrypted
	 * @param keyword the keyword to be used
	 * @return the ciphertext
	 */
	public String encrypt(String plaintext, String keyword)
	{
		String ciphertext = "";
		plaintext = super.format(plaintext);
		keyword = super.format(keyword);
		CaesarCipher c = new CaesarCipher();
		
		for(int i = 0; i < plaintext.length(); i++)
		{
			ciphertext = ciphertext + c.encrypt(Character.toString(plaintext.charAt(i)),
												"" + alphabet.indexOf(keyword.charAt((i) % keyword.length())));
		}	
		return ciphertext;
	}
	
	/**
	 * This method decrypts a message using the Vigenere Cipher algorithm. The
	 * message returned will have all whitespace and special characters removed
	 * (non-letters) and will be in all uppercase letters.
	 * @param ciphertext the text to be decrypted
	 * @param keyword the keyword used to encrypt the original plaintext
	 * @return the plaintext
	 */
	public String decrypt(String ciphertext, String keyword)
	{
		keyword = super.format(keyword);
		String negKeyword = "";
		
		for(int i = 0; i < keyword.length(); i++)
		{
			negKeyword = negKeyword + alphabet.charAt(25 - alphabet.indexOf(keyword.charAt(i)));
		}
		
		return encrypt(ciphertext, negKeyword);	
	}
}
