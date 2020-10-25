public class CryptoTest {
  
  public static void main(String[] args) throws Exception {

    //When running "java CryptoTest" receive an ArrayIndexOutOfBoundsException
    //When running "java CryptoTest encrypt.txt decrypt.txt" the program executes
    //and creates an encrypt.txt and decrypt.txt

    String inFile = args[0];
    String outFile = args[1];

    CryptoEngine cryptoEngine = new CryptoEngine(inFile, outFile);
    cryptoEngine.encrypt();
    cryptoEngine.decrypt();
  }
}