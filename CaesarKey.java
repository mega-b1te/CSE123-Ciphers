// Krish Doshi
// 10/4/2024
// CSE 123 
// P0: Ciphers
// TA: Cynthia


// Represents a classical cipher in which a client can define a key that will be
// placed at the beginning of a shifter and is followed by the rest of the characters
// in the cipher's character range in order (excluding the ones in the key)
public class CaesarKey extends Substitution {
    
    
    // Behavior: Constructs a new Caesar Key cipher using the client provided key
    //           to create a shifter in which the characters from the input are replaced with
    //           the corresponding characters in the shifter on encryption
    // Exceptions: Throws an IllegalArgumentException if the key is empty, if the shifter 
    //           is a different length than the encodable range of characters, if any character 
    //           in the shifter falls outside of our encodable range of characters, 
    //           and if there are any duplicate characters in the provided shifter
    // Parameters:
    //           'key': the key that is put at the beginning of the shifter
    public CaesarKey(String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException();
        }

        setShifter(formShifter(key));
    }

    // Behavior: Form the shifter based on the key provided by the client. This happens
    //           by placing the key at the beginning of the shifter and then adding the rest
    //           of the characters in the encodable range into the shifter in order (leaving out 
    //           the characters that are already within the key at the beginning of the shifter)
    // Returns: The completed shifter based on the key provided by the client
    // Parameters:
    //          'key': the key provided by the client
    private String formShifter(String key) {
        String shifter = key;

        for (int i = 0; i < Cipher.TOTAL_CHARS; i++) {
            char currChar = (char)(Cipher.MIN_CHAR + i);

            if (shifter.indexOf(currChar) == -1) {
                shifter += (char)(Cipher.MIN_CHAR + i);
            }
        }

        return shifter;
    }

}