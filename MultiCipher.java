// Krish Doshi
// 10/4/2024
// CSE 123 
// P0: Ciphers
// TA: Cynthia

import java.util.*;

// Represents a combination of Substitution, CaesarKey, and CaesarShift ciphers in 
// which a client can define a sequence of ciphers which will be run through to encrypt 
// and decrypt a message
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    // Behavior: Constructs a new Multi Cipher using the client provided list of
    //           ciphers (the client can choose from either the Substitution cipher, 
    //           the Caesar Key cipher, or the Caesar Shift cipher)
    // Exceptions: Throws an IllegalArgumentException if client provided list of ciphers is 
    //           empty
    // Parameters:
    //           'ciphers': the client provided list of ciphers being chained together 
    //            to form the multi cipher
    public MultiCipher(List<Cipher> ciphers){

        if (ciphers == null) {
            throw new IllegalArgumentException();
        }

        this.ciphers = ciphers;
    }

    @Override
    // Behavior: Encrypts the provided 'input' by running it through the encryption
    //           of each of the ciphers in the list in order
    // Exceptions: Throws an IllegalStateException if
    //           a shifter was never set to a valid shifter (empty shifter)
    // Returns: The input after being encrypted
    // Parameters:
    //          'input': the text input to encrypt. Should be non-null and all provided 
    //           characters within 'input' should be within the encodable range.
    public String encrypt(String input) {
        for (int i = 0; i < ciphers.size(); i++) {
            input = ciphers.get(i).encrypt(input);
        }

        return input;
    }

    @Override
    // Behavior: Decrypt the provided 'input' by running it through decryption
    //           in the reverse order of the ciphers in the list (starting by decrypting
    //           with the final cipher in the list and ending with decrypting with the first
    //           cipher in the list
    // Exceptions: Throws an IllegalStateException if
    //            a shifter was never set to a valid shifter (empty shifter)
    // Returns: The input after being decrypted
    // Parameters:
    //          'input': the text input to decrypt. Should be non-null and all provided 
    //           characters within 'input' should be within the encodable range.
    public String decrypt(String input) {
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            input = ciphers.get(i).decrypt(input);
        }

        return input;
    }

}