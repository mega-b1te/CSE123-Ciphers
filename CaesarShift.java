// Krish Doshi
// 10/4/2024
// CSE 123 
// P0: Ciphers
// TA: Cynthia

import java.util.LinkedList;
import java.util.Queue;


// Represents a classical cipher in which a client can define how much to shift
// the cipher's character range by and that becomes the shifter to which the
// corresponding characters in the input are substituted with
public class CaesarShift extends Substitution {

    // Behavior: Constructs a new Caesar Shift cipher using the client shift amount
    //           to create a shifter by which the characters from the input are replaced with
    //           the corresponding characters in the shifter on encryption
    // Exceptions: Throws an IllegalArgumentException if the shift is less than or 
    //           equal to 0, if the shifter is a different length
    //           than the encodable range of characters, if any character
    //           in the shifter falls outside of our encodable range of 
    //           characters, and if there are any duplicate characters in the provided shifter
    // Parameters:
    //           'shift': the amount that the encodable range is shifted by to form the 
    //                    Caesar Shift cipher shifter
    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException();
        }

        setShifter(formShifter(shift));        
    }

    // Behavior: Constructs a new Caesar Shift cipher using the client shift amount
    //           to create a shifter by moving all the characters from the encodable range 
    //           to the left the amount of times specified by the client (moving the 
    //           values at the front to the end each time) and with this shifter 
    //           the characters from the input are replaced with
    //           the corresponding characters in the shifter on encryption
    // Returns: The completed shifter based on the shift amount provided by the client
    // Parameters:
    //           'shift': the client provided shift amount
    private String formShifter(int shift) {
        Queue<Character> creatingShifter = new LinkedList<>();

        for (int i = 0; i < Cipher.TOTAL_CHARS; i++) {
            creatingShifter.add((char)(Cipher.MIN_CHAR + i));
        }

        for (int i = 0; i < shift; i++) {
            creatingShifter.add(creatingShifter.remove());
        }

        String shifter = "";

        while (creatingShifter.size() > 0) {
            shifter += creatingShifter.remove();
        }

        return shifter;
    }

}