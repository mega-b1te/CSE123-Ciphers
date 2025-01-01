// Krish Doshi
// 10/4/2024
// CSE 123 
// P0: Ciphers
// TA: Cynthia

import java.util.*;

// Represents a classical cipher in which a client can define a shifter which
// represents the output characters that correspond with the input characters
public class Substitution extends Cipher {
    private String shifter;

    // Behavior: Constructs a new Substitution cipher in which the shifter is empty
    public Substitution() {
        this.shifter = "";
    }

    // Behavior: Constructs a new Substitution cipher using the client provided shifter
    //           in which the characters from the input are replaced with
    //           the corresponding characters in the shifter on encryption
    // Exceptions: Throws an IllegalArgumentException if the shifter doesn't
    //             meet the criteria to make it valid (defined in comment for 
    //             legalShifterChecker method)
    // Parameters:
    //           'shifter': the substitution shifter to use when encrypting
    public Substitution(String shifter) {
        setShifter(shifter);
    }

    // Behavior: Checks whether the shifter given by the client is a valid shifter
    // Exceptions: Throws an IllegalArgumentException if the shifter is a different length
    //           than the encodable range of characters, if any character in the
    //           shifter falls outside of our encodable range of characters, and if there are any
    //           duplicate characters in the provided shifter
    // Parameters:
    //          'shifter': the shifter that was provided by the client
    private void legalShifterChecker(String shifter) {
        Set<Character> checkDuplicates = new HashSet<>();
        boolean characterNotWithinRange = false;

        for (int i = 0; i < shifter.length(); i++) {
            char currCharacter = shifter.charAt(i);
            checkDuplicates.add(currCharacter);

            if (currCharacter > Cipher.MAX_CHAR || 
                    currCharacter < Cipher.MIN_CHAR) {
                characterNotWithinRange = true;
            }
        }

        if (shifter.length() != Cipher.TOTAL_CHARS ||
                checkDuplicates.size() != shifter.length() ||
                        characterNotWithinRange) {
            throw new IllegalArgumentException();
            
        }
    }

    // Behavior: Checks whether the client gave a shifter to the Substitution cipher
    // Exceptions: Throws an IllegalStateException if a valid shifter wasn't provided 
    //             by the client
    private void shifterNotSetChecker() {
        if (shifter.equals("")) {
            throw new IllegalStateException();
        }
    }

    // Behavior: Updates the shifter for the Substitution cipher 
    //           with a new client-provided shifter
    // Exceptions: Throws an IllegalStateException if a valid shifter wasn't provided 
    //             by the client
    // Parameters:
    //          'shifter': a new shifter provided by the client
    public void setShifter(String shifter) {
        legalShifterChecker(shifter);
        this.shifter = shifter;
    }

    
    @Override
    // Behavior: Encrypts the provided 'input' by substituting characters according to
    //           provided shifter
    // Exceptions: Throws an IllegalStateException if the empty constructor was used and
    //             the shifter was never set to a valid shifter
    // Returns: The input after being encrypted
    // Parameters:
    //          'input': the text input to encrypt. Should be non-null and all provided 
    //           characters within 'input' should be within the encodable range.
    public String encrypt(String input) {
        shifterNotSetChecker();

        String encryptedString = "";

        for (int i = 0; i < input.length(); i++) {
            encryptedString += shifter.charAt(input.charAt(i) - 
                    Cipher.MIN_CHAR);
        }

        return encryptedString;


    }


    @Override
    // Behavior: Decrypt the provided 'input' by reversing the subsitution during
    //           encryption by using the provided shifter
    // Exceptions: Throws an IllegalStateException if the empty constructor was used and
    //             the shifter was never set to a valid shifter
    // Returns: The input after being decrypted
    // Parameters:
    //          'input': the text input to decrypt. Should be non-null and all provided 
    //           characters within 'input' should be within the encodable range.
    public String decrypt(String input) {
        shifterNotSetChecker();

        String decryptedString = "";

        for (int i = 0; i < input.length(); i++) {
            decryptedString += (char)(Cipher.MIN_CHAR + 
                    shifter.indexOf(input.charAt(i)));
        }

        return decryptedString;

    }




}