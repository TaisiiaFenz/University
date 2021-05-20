import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    String fileName;
    String inputText;
    private StringBuilder buffer = new StringBuilder();
    private List<Token> tokens = new ArrayList<Token>();

    public Lexer(String fileName) {
        this.fileName = fileName;
    }

    public void initialize() {
        File f = new File("src/main/resources/" + fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
        Reader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();

        String line = "";
        while (true) {
            try {
                if (!((line = ((BufferedReader) bufferedReader).readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(line);
        }

        inputText = stringBuilder.toString();

        System.out.println(inputText);
    }

    public void generateTokens() {

        //return null;
    }
}
