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

    private boolean isBooleanLiteral(String value) {
        return ("true" == value || "false" == value) ? true : false;
    }

    private boolean isNullLiteral(String value) {
        return ("null" == value) ? true : false;
    }

    private void addToken(char c, Type type) {
        System.out.println("Add token " + c + " " + type);
        tokens.add(new Token(Character.toString(c), type));
        buffer = new StringBuilder();
    }

    private void addToken(String c, Type type) {
        System.out.println("Add token " + c + " " + type);
        tokens.add(new Token(c, type));
        buffer = new StringBuilder();
    }

    private void addToBuffer(char c, int state) {
        System.out.println("Add buffer " + c + " " + state);
        buffer.append(c);
        this.state = state;
    }

    public void generateTokens() {
        letter = 0;

        while (letter < inputText.length) {
            char c = inputText[letter];
            switch (state) {
                case 0:
                    startState_0(c);
                    break;
                case 1:
                    slash_1(c);
                    break;
                case 2:
                    identifier_2(c);
                    break;
                case 3:
                    zeroFirst_3(c);
                    break;
            }
            ++letter;
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

    public void slash_1(char c) {
        switch (c) {
            case '/': {
                addToBuffer(c, 15);
                return;
            }
            case '*': {
                addToBuffer(c, 16);
                return;
            }
            case '=': {
                addToBuffer(c, 18);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
        }
         if (AdditionalSymbols.operator(c) == c) {
                addToBuffer(c, -1);
         } else {
             addToken(buffer.toString(), Type.OPERATOR);
             letter--;
             state = 0;
         }
    }

    public void identifier_2(char c) {
        if (Character.isJavaIdentifierPart(c)) {
            addToBuffer(c, 2);
            return;
        }
        switch (c) {
            case '#': {
                addToBuffer(c, -1);
                return;
            }
            case '/': {
                addToBuffer(c, 29);
                return;
            }
        }
        if ((AdditionalSymbols.whitespace(c) == c)
                ||(AdditionalSymbols.operator(c) == c)
                ||(AdditionalSymbols.separator(c) == c)) {
            if (isNullLiteral(buffer.toString())) {
                addToken(buffer.toString(), Type.NULL_LITERAL);
            } else if (isBooleanLiteral(buffer.toString())) {
                addToken(buffer.toString(), Type.BOOLEAN_LITERAL);
            } else if (IsKeyword.parse(buffer.toString())) {
                addToken(buffer.toString(), Type.KEYWORD);
            } else {
                addToken(buffer.toString(), Type.IDENTIFIER);
            }
            state = 0;
            letter--;
            return;
        } else {
            addToBuffer(c, -1);
        }
    }

    public void zeroFirst_3(char c) {
        if (AdditionalSymbols.octal(c) == c) {
            addToBuffer(c, 37);
            return;
        }
        switch (c) {
            case '_': {
                addToBuffer(c, 38);
                return;
            }
            case 'b':
            case 'B': {
                addToBuffer(c, 35);
                return;
            }
            case 'x':
            case 'X': {
                addToBuffer(c, 36);
                return;
            }
            case '.': {
                addToBuffer(c, 23);
                return;
            }
            case 'l':
            case 'L': {
                addToBuffer(c, 41);
                return;
            }
        }
        if (Character.isJavaIdentifierPart(c) || c == '8' || c == '9') {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.INT_LITERAL);
            state = 0;
        }
    }
}
