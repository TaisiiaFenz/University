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
                case -1:
                    incorrectState_minus1(c);
                    break;
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
                case 4:
                    nonzeroDigit_4(c);
                    break;
                case 5:
                    charLiteral_5(c);
                    break;
                case 6:
                    stringLiteral_6(c);
                    break;
                case 7:
                    dot_7(c);
                    break;
                case 8:
                    greater_8(c);
                    break;
                case 9:
                    less_9(c);
                    break;
                case 10:
                    ampersand_10(c);
                    break;
                case 11:
                    singleOperator_11(c);
                    break;
                case 12:
                    colon_12(c);
                    break;
                case 13:
                    plus_13(c);
                    break;
                case 14:
                    minus_14(c);
                    break;
                case 15:
                    singleLineComment_15(c);
                    break;
                case 16:
                    multiLineComment_16(c);
                    break;
                case 17:
                    starInMultiLineComment_17(c);
                    break;
                case 18:
                    divideEqual_18(c);
                    break;
                case 19:
                    maybeComment_19(c);
                    break;
                case 20:
                    pipe_20(c);
                    break;
                case 21:
                    colonOrSeparator_21(c);
                    break;
                case 22:
                    greaterGreater_22(c);
                    break;
                case 23:
                    pointInDigit_23(c);
                    break;
                case 24:
                    lessLess_24(c);
                    break;
                case 25:
                    dotDot_25(c);
                    break;
                case 26:
                    ampersandOrPipe_26(c);
                    break;
                case 27:
                    dotDotDot_27(c);
                    break;
                case 28:
                    maybeCommentAfterIdentifier_28(c);
                    break;
            }
            ++letter;
        }
    }

    public void incorrectState_minus1(char c) {
        char lastElement = buffer.toString().substring(buffer.length() - 1).charAt(0);
        if (AdditionalSymbols.whitespace(c) == c
                || AdditionalSymbols.separator(c) == c
                || c == '.'
                || AdditionalSymbols.operator(c) == c
                && AdditionalSymbols.operator(lastElement) != c) {
            letter--;
            addToken(buffer.toString(), Type.ERROR);
            state = 0;
        } else if (buffer.length() > 0 && lastElement == '/' && (c == '/' || c == '*')) {
            buffer.deleteCharAt(buffer.length() - 1);
            addToken(buffer.toString(), Type.ERROR);
            buffer.append('/');
            if (c == '/') {
                addToBuffer(c, 15);
            } else {
                addToBuffer(c, 16);
            }
        } else {
            addToBuffer(c, -1);
        }
    }

    public void startState_0(char c) {

        switch (c) {
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
            case '\"': {
                addToBuffer(c, 6);
                break;
            }
            case '.': {
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
            case '-': {
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
                || (AdditionalSymbols.operator(c) == c)
                || (AdditionalSymbols.separator(c) == c)) {
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

    public void nonzeroDigit_4(char c) {
        if (c == '_') {
            addToBuffer(c, 34);
        } else if (AdditionalSymbols.digits(c) == c) {
            addToBuffer(c, 4);
        } else if (Character.isJavaIdentifierPart(c)) {
            addToBuffer(c, -1);
        } else if (c == '.') {
            addToBuffer(c, 23);
        } else if (c == 'l' || c == 'L') {
            addToBuffer(c, 41);
        } else {
            addToken(buffer.toString(), Type.INT_LITERAL);
            letter--;
            state = 0;
        }
    }

    public void charLiteral_5(char c) {
        if (c == '\\') {
            addToBuffer(c, 31);
        } else if (AdditionalSymbols.whitespace(c) == c && c != ' ' && c != '\t') {
            addToken(buffer.toString(), Type.ERROR);
            addToken(c, Type.WHITESPACE);
            state = 0;
        } else {
            addToBuffer(c, 32);
        }
    }

    public void stringLiteral_6(char c) {
        if (c == '\\') {
            addToBuffer(c, 30);
        } else if (c == '\"') {
            buffer.append(c);
            addToken(buffer.toString(), Type.STRING_LITERAL);
            state = 0;
        } else if ((AdditionalSymbols.whitespace(c) == c) && c != ' ' && c != '\t') {
            addToken(buffer.toString(), Type.ERROR);
            addToken(c, Type.WHITESPACE);
            state = 0;
        } else {
            addToBuffer(c, 6);
        }
    }

    public void dot_7(char c) {
        if (AdditionalSymbols.digits(c) == c) {
            addToBuffer(c, 23);
        } else if (c == '.') {
            addToBuffer(c, 25);
        } else {
            letter--;
            addToken(buffer.toString(), Type.SEPARATOR);
            state = 0;
        }
    }

    public void greater_8(char c) {
        switch (c) {
            case '=': {
                addToBuffer(c, 18);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
            case '>': {
                addToBuffer(c, 22);
                return;
            }
            case '/': {
                addToBuffer(c, 19);
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

    public void less_9(char c) {
        switch (c) {
            case '=': {
                addToBuffer(c, 18);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
            case '<': {
                addToBuffer(c, 24);
                return;
            }
            case '/': {
                addToBuffer(c, 19);
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

    public void ampersand_10(char c) {
        switch (c) {
            case '&': {
                addToBuffer(c, 27);
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
            case '/': {
                addToBuffer(c, 19);
                return;
            }
        }
        if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void singleOperator_11(char c) {
        if (c == '=') {
            addToBuffer(c, 18);
        } else if (c == ':') {
            addToBuffer(c, 21);
        } else if (c == '/') {
            addToBuffer(c, 19);
        } else if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void colon_12(char c) {
        if (c == ':') {
            addToken("::", Type.SEPARATOR);
            state = 0;
        } else if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
            letter--;
        }
    }

    public void plus_13(char c) {
        switch (c) {
            case '+': {
                addToBuffer(c, 11);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
            case '=': {
                addToBuffer(c, 18);
                return;
            }
            case '/': {
                addToBuffer(c, 19);
                return;
            }
        }
        if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void minus_14(char c) {
        switch (c) {
            case '-': {
                addToBuffer(c, 11);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
            case '=':
            case '>': {
                addToBuffer(c, 18);
                return;
            }
            case '/': {
                addToBuffer(c, 19);
                return;
            }
        }
        if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void singleLineComment_15(char c) {
        if (AdditionalSymbols.whitespace(c) == c && c != '\t' && c != ' ') {
            addToken(buffer.toString(), Type.COMMENT);
            addToken(c, Type.WHITESPACE);
            state = 0;
        } else {
            addToBuffer(c, 15);
        }
    }

    public void multiLineComment_16(char c) {
        if (c == '*') {
            addToBuffer(c, 17);
        } else {
            addToBuffer(c, 16);
        }
    }

    public void starInMultiLineComment_17(char c) {
        if (c == '/') {
            addToBuffer(c, 0);
            addToken(buffer.toString(), Type.COMMENT);
        } else {
            addToBuffer(c, 16);
        }
    }

    public void divideEqual_18(char c) {
        if (c == '/') {
            addToBuffer(c, 19);
        } else if (c == ':') {
            addToBuffer(c, 21);
        } else if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void maybeComment_19(char c) {
        if (c == '/' || c == '*') {
            buffer.deleteCharAt(buffer.length() - 1);
            addToken(buffer.toString(), Type.OPERATOR);
            buffer.append('/');
            if (c == '/') {
                addToBuffer(c, 15);
            } else {
                addToBuffer(c, 16);
            }
        } else {
            addToBuffer(c, -1);
        }
    }

    public void pipe_20(char c) {
        switch (c) {
            case '|': {
                addToBuffer(c, 28);
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
            case '/': {
                addToBuffer(c, 19);
                return;
            }
        }
        if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void colonOrSeparator_21(char c) {
        if (c == ':') {
            buffer.deleteCharAt(buffer.length() - 1);
            addToken(buffer.toString(), Type.OPERATOR);
            addToken("::", Type.SEPARATOR);
            state = 0;
        } else {
            letter--;
            state = -1;
        }
    }

    public void greaterGreater_22(char c) {
        switch (c) {
            case '=': {
                addToBuffer(c, 18);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
            case '>': {
                addToBuffer(c, 11);
                return;
            }
            case '/': {
                addToBuffer(c, 19);
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

    public void pointInDigit_23(char c) {
        if (AdditionalSymbols.digits(c) == c) {
            addToBuffer(c, 23);
        } else if (AdditionalSymbols.doubleOrFloat(c) == c) {
            addToBuffer(c, 42);
        } else if (Character.isJavaIdentifierPart(c) || c == '.') {
            addToBuffer(c, -1);
        } else if (c == '_') {
            addToBuffer(c, 43);
        } else {
            letter--;
            addToken(buffer.toString(), Type.FLOAT_LITERAL);
            state = 0;
        }
    }

    public void lessLess_24(char c) {
        switch (c) {
            case '=': {
                addToBuffer(c, 18);
                return;
            }
            case ':': {
                addToBuffer(c, 21);
                return;
            }
            case '<': {
                addToBuffer(c, 11);
                return;
            }
            case '/': {
                addToBuffer(c, 19);
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

    public void dotDot_25(char c) {
        if (c == '.') {
            addToBuffer(c, 28);
        } else {
            addToken(buffer.toString().charAt(0), Type.SEPARATOR);
            addToken(buffer.toString().charAt(1), Type.SEPARATOR);
            state = 0;
        }
    }

    public void ampersandOrPipe_26(char c) {
        if (c == ':') {
            addToBuffer(c, 21);
        } else if (c == '/') {
            addToBuffer(c, 19);
        } else if (AdditionalSymbols.operator(c) == c) {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.OPERATOR);
            state = 0;
        }
    }

    public void dotDotDot_27(char c) {
        if (c == '.') {
            addToBuffer(c, -1);
        } else {
            letter--;
            addToken(buffer.toString(), Type.SEPARATOR);
            state = 0;
        }
    }

    public void maybeCommentAfterIdentifier_28(char c) {
        if (c == '/' || c == '*') {
            buffer.deleteCharAt(buffer.length() - 1);

            if (isNullLiteral(buffer.toString())) {
                addToken(buffer.toString(), Type.NULL_LITERAL);
            } else if (isBooleanLiteral(buffer.toString())) {
                addToken(buffer.toString(), Type.BOOLEAN_LITERAL);
            } else if (IsKeyword.parse(buffer.toString())) {
                addToken(buffer.toString(), Type.KEYWORD);
            } else {
                addToken(buffer.toString(), Type.IDENTIFIER);
            }
            buffer.append('/');
            if (c == '/') {
                addToBuffer(c, 15);
            } else {
                addToBuffer(c, 16);
            }
        } else {
            letter -= 2;
            buffer.deleteCharAt(buffer.length() - 1);

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
        }
    }
}
