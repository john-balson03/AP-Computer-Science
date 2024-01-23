

/**
 * This class creates the object of a Cipher. Ciphers can encrypt and decrypt
 * messages depending on their specific algoritms.
 * @author johnbalson
 * @version 1/28/2020
 */
public abstract class Cipher
{
	protected static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * Creates an instance of a Cipher.
	 */
	public Cipher()
	{
		
	}
	
	/**
	 * This method encrypts a message with the given key, based on the algorithm
	 * of the specific Cipher.
	 * @param plaintext the message to be encrypted
	 * @param key the key used to encrypt
	 * @return the encrypted message
	 */
	public abstract String encrypt(String plaintext, String key);
	
	/**
	 * This method decrypts a message with the given key, based on the algorithm
	 * of the specific Cipher.
	 * @param ciphertext the message to be decrypted
	 * @param key the key used to encrypt
	 * @return the decrypted message
	 */
	public abstract String decrypt(String ciphertext, String key);
	
	/**
	 * Formats Strings by removing whitespace and special characters and
	 * converting all letters to uppercase.
	 * @param s the String to be formatted
	 * @return the formatted String
	 */
	protected String format(String s)
	{
		String formattedS = "";
		
		s = s.toUpperCase();
		for(int i = 0; i < s.length(); i++)
		{
			if(alphabet.contains("" + s.charAt(i)))
			{
				formattedS = formattedS + s.charAt(i);
			}
		}
		return formattedS;
	}

}