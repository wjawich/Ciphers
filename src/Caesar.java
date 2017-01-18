
public class Caesar {

   public static void main(String[] args) {
       System.out.println(encrypt('X', 3));
       System.out.println(decrypt(encrypt('X',3), 3));

       //System.out.println(testBed("Hello Word!"));
   }

   private static String lAlphabet = "abcdefghijklmnopqrstuvwxyz";
   private static String uAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private static int defaultShift = 3;

   //encodes a string by applying a given shift to each character.
   //The encryption is done using the formula (x+n) mod 26
   public static String encrypt(String message, int shift) {

       String encryptedMessage = "";
       char character;
       int pos, newPos;

       for (int i =0; i<message.length(); i++) {

           //extract the character
           character = message.charAt(i);

           //get the appropriate alphabet: uppercase or lowercase
           String alphabet = getAlphabet(isUpperCase(character));

           // get the position of the letter (x)
           pos = alphabet.indexOf(character);

           //shift the letter by the given amount. take into account overflowing
           newPos = ((pos + shift) % 26 + 26) %26;

           // add the encrypted character to the encrypted message
           encryptedMessage += alphabet.charAt(newPos);

       }

       return  encryptedMessage;
   }

   //encodes a string by applying a given shift to each character.
   //The encryption is done using the formula (x+n) mod 26
   public static char encrypt(char character, int shift) {

       char encryptedCharacter;

       int pos, newPos;


       //get the appropriate alphabet: uppercase or lowercase
       String alphabet = getAlphabet(isUpperCase(character));

       // get the position of the letter (x)
       pos = alphabet.indexOf(character);

       //shift the letter by the given amount. take into account overflowing
       newPos = ((pos + shift) % 26 + 26) %26;

       // add the encrypted character to the encrypted message
       encryptedCharacter = alphabet.charAt(newPos);


       return  encryptedCharacter;
   }


   //encodes a string by applying a the default shift of 3 to each character
   public static String encrypt(String message) {
       String encryptedMessage = "";
       char character;
       int pos, newPos;

       for (int i =0; i<message.length(); i++) {

           //extract the character
           character = message.charAt(i);

           //get the appropriate alphabet: uppercase or lowercase
           String alphabet = getAlphabet(isUpperCase(character));

           // get the position of the letter (x)
           pos = alphabet.indexOf(character);

           //shift the letter by the given amount. take into account overflowing
           newPos = ((pos + defaultShift) % 26 + 26) %26;

           // add the encrypted character to the encrypted message
           encryptedMessage += alphabet.charAt(newPos);

       }

       return  encryptedMessage;
   }

   private static String getAlphabet(boolean upperCase) {

       if (upperCase) {
           return uAlphabet;
       } else {
           return lAlphabet;
       }
   }

   private static boolean isUpperCase(char letter) {
       return uAlphabet.indexOf(letter) != -1;
   }

   //decodes an encrypted string by applying the given shift to the opposite direction
   public static String decrypt(String encryptedMessage, int shift) {

       String plaintextMessage = "";
       char character;
       int pos, newPos;

       for (int i =0; i<encryptedMessage.length(); i++) {

           //extract the character
           character = encryptedMessage.charAt(i);

           //get the appropriate alphabet: uppercase or lowercase
           String alphabet = getAlphabet(isUpperCase(character));

           // get the position of the letter (x)
           pos = alphabet.indexOf(character);

           //shift the letter by the given amount. take into account overflowing
           newPos = ((pos - shift) % 26 + 26) %26;

           // add the encrypted character to the encrypted message
           plaintextMessage += alphabet.charAt(newPos);

       }

       return  plaintextMessage;

   }

   //encodes a string by applying a given shift to each character.
   //The encryption is done using the formula (x+n) mod 26
   public static char decrypt(char character, int shift) {

       char encryptedCharacter;

       int pos, newPos;


       //get the appropriate alphabet: uppercase or lowercase
       String alphabet = getAlphabet(isUpperCase(character));

       // get the position of the letter (x)
       pos = alphabet.indexOf(character);

       //shift the letter by the given amount. take into account overflowing
       newPos = ((pos - shift) % 26 + 26) %26;

       // add the encrypted character to the encrypted message
       encryptedCharacter = alphabet.charAt(newPos);


       return  encryptedCharacter;
   }


   //decodes an encrypted string by applying the default shift if 3 to the opposite direction
   public static String decrypt(String encryptedMessage) {

       String plaintextMessage = "";
       char character;
       int pos, newPos;

       for (int i =0; i<encryptedMessage.length(); i++) {

           //extract the character
           character = encryptedMessage.charAt(i);

           //get the appropriate alphabet: uppercase or lowercase
           String alphabet = getAlphabet(isUpperCase(character));

           // get the position of the letter (x)
           pos = alphabet.indexOf(character);

           //shift the letter by the given amount. take into account overflowing
           newPos = ((pos - defaultShift) % 26 + 26) %26;

           // add the encrypted character to the encrypted message
           plaintextMessage += alphabet.charAt(newPos);

       }

       return  plaintextMessage;

   }

   public static String testBed(String word){
       String test = "";
       for (int i = 0; i<word.length(); i++) {
           test += word.charAt(i) + ": " + (word.charAt(i) + 0) + " --> " + (word.charAt(i) + defaultShift) + "\n";
       }

       return test;
   }

}