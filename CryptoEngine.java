//util.* is for importing all the built in utility packages in Java
import java.util.*;
//Used to file data
import java.nio.file.Files;
//Path instance for representing a directory
import java.nio.file.Paths;
//All the crypto classes
import javax.crypto.*;
//Exception when a file doesn't exist
import java.io.FileNotFoundException;
//Exception for when there is no input/input invalid
import java.io.IOException;
//Exception for when the key is not in the normal range
import java.security.InvalidKeyException;
//Exception for wrong algorithm
import java.security.NoSuchAlgorithmException;

public class CryptoEngine {

  //Creates a key
  KeyGenerator keyGen;
  //Stores the encrypted/decrypted file names in Strings
  String encFile, decFile;
  //Creates a secret key 
  SecretKey secKey;
  //Used to perform encryption/decryption with the created secret key
  Cipher aesCipher;

  CryptoEngine(String inFile, String outFile) throws NoSuchAlgorithmException, NoSuchPaddingException {
    encFile = inFile;
    decFile = outFile;

    keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128);
    secKey = keyGen.generateKey();
    aesCipher = Cipher.getInstance("AES");
  }

  public void encrypt() throws InvalidKeyException, IOException {
    byte[] byteText = "This plaintext will be encrypted".getBytes();

    aesCipher.init(Cipher.ENCRYPT_MODE, secKey);

    byte[] byteCipherText = null;

    try {
      byteCipherText = aesCipher.doFinal(byteText);
    }
    catch(IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    Files.write(Paths.get(encFile), byteCipherText);
  }

  public void decrypt() throws IOException, InvalidKeyException {
    byte[] cipherText = Files.readAllBytes(Paths.get(encFile));

    aesCipher.init(Cipher.DECRYPT_MODE, secKey);

    byte[] bytePlainText = null;

    try {
      bytePlainText = aesCipher.doFinal(cipherText);
    }
    catch(IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    Files.write(Paths.get(decFile), bytePlainText);
  }
}