import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    String fileName;
    char[] inputText;
    int letter = 0;
    int state = 0;
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

        inputText = stringBuilder.toString().toCharArray();

        System.out.println(inputText);
    }

    private void addToken(char c, Type type) {
        System.out.println("Add token " + c + " " + type);
        tokens.add(new Token(Character.toString(c), type));
        buffer = new StringBuilder();
    }

    private void addToBuffer(char c, int state) {
        System.out.println("Add buffer " + c);
        buffer.append(c);
        this.state = state;
    }

    public void generateTokens() {
        //inputText += " ";
        letter = 0;

        while (letter < inputText.length) {
            char c = inputText[letter];
            switch (state) {
                case 0:
                    startState_0(c);
                    break;
            }
        }
    }

    public void startState_0(char c) {

        switch(c) {
            case '/': {
                addToBuffer(c, 1);
                break;
            }
            case '0': {
                addToBuffer(c, 3);
                break;
            }
            case '\'': {
                addToBuffer(c, 5);
                break;
            }
            case'\"': {
                addToBuffer(c, 6);
                break;
            }
            case'.': {
                addToBuffer(c, 7);
                break;
            }
            case '>': {
                addToBuffer(c, 8);
                break;
            }
            case '<': {
                addToBuffer(c, 9);
                break;
            }
            case '&': {
                addToBuffer(c, 10);
                break;
            }
            case '^':
            case '!':
            case '*':
            case '=':
            case '%': {
                addToBuffer(c, 11);
                break;
            }
            case ':': {
                addToBuffer(c, 12);
                break;
            }
            case '+': {
                addToBuffer(c, 13);
                break;
            }
            case'-': {
                addToBuffer(c, 14);
                break;
            }
            case '?':
            case '~': {
                addToken(c, Type.OPERATOR);
                state = 0;
                break;
            }
            case '#': {
                addToBuffer(c, -1);
                break;
            }
            case '|': {
                addToBuffer(c, 20);
                break;
            }
        }

        if (AdditionalSymbols.whitespace(c) == c) {
            addToken(c, Type.WHITESPACE);
            state = 0;
        } else if (Character.isJavaIdentifierStart(c)) {
            addToBuffer(c, 2);
        } else if (AdditionalSymbols.digits(c) == c) {
            addToBuffer(c, 4);
        } else if (AdditionalSymbols.separator(c) == c) {
            addToken(c, Type.SEPARATOR);
            state = 0;
        }
    }
}
