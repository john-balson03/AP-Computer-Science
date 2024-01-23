

/**
 * This class provides methods to encrypt and decrypt messages using a Caesar
 * Cipher. A Caesar Cipher changes each letter in a message to a letter some
 * number away alphabetically from the original letter. The number of spaces
 * away is called the key.
 * @author johnbalson
 * @version 1/15/2020
 */
public class CaesarCipher extends Cipher
{
	/** This method encrypts a message using the Caesar Cipher algorithm. The
	 * message returned will have all whitespace and special characters removed
	 * (non-letters) and will be in all uppercase letters.
	 * @param plaintext the message to be encrypted
	 * @param key the key to be used
	 * @return the ciphertext
	 * 
	 */
	public String encrypt(String plaintext, String key)
	{
		String encryptedMessage = "";
		String toAdd = "";
		plaintext = super.format(plaintext);
		int intKey = Integer.parseInt(key);
		
		if(intKey < 0)
		{
			intKey = 26 + intKey;
		}
		
		for(int i = 0; i < plaintext.length(); i++)
		{
			if(!(alphabet.indexOf(plaintext.charAt(i)) == -1))
			{
				toAdd = "" + alphabet.charAt((alphabet.indexOf(plaintext.charAt(i)) + intKey) % 26);
				encryptedMessage = encryptedMessage + toAdd;
			}
		}
		
		return encryptedMessage;
	}
	
	/**
	 * This method decrypts a message using the Caesar Cipher algorithm. The
	 * message returned will have all whitespace and special characters removed
	 * (non-letters) and will be in all uppercase letters.
	 * @param ciphertext the text to be decrypted
	 * @param key the key used to encrypt the original plaintext
	 * @return the plaintext
	 */
	public String decrypt(String ciphertext, String key)
	{
		if(Integer.parseInt(key) < 0)
		{
			return encrypt(ciphertext, key.substring(1, key.length()));
		}
		return encrypt(ciphertext, "-" + key);
	}
}


