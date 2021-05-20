import java.util.regex.Pattern;

public class AdditionalSymbols {

    public static char whitespace(char c) {
        return Character.isWhitespace(c) ? c : Character.MIN_VALUE;
    }

    public static char digits(char c) {
        return Character.isDigit(c) ? c : Character.MIN_VALUE;
    }

    public static char separator(char c) {
        char res = Character.MIN_VALUE;
        switch (c) {
            case '(':
                 res = '(';
                 break;
            case ')':
                res = ')';
                break;
            case '{':
                res = '{';
                break;
            case '}':
                res = '}';
                break;
            case '[':
                res = '[';
                break;
            case ']':
                res = ']';
                break;
            case ';':
                res = ';';
                break;
            case ',':
                res = ',';
                break;
            case '@':
                res = '@';
                break;
        }
        return res;
    }

    public static char operator(char c) {
        char res = Character.MIN_VALUE;
        switch (c) {
            case '=':
                res = '=';
                break;
            case '>':
                res = '>';
                break;
            case '<':
                res = '<';
                break;
            case '!':
                res = '!';
                break;
            case '~':
                res = '~';
                break;
            case ':':
                res = ':';
                break;
            case '?':
                res = '?';
                break;
            case '&':
                res = '&';
                break;
            case '|':
                res = '|';
                break;
            case '+':
                res = '+';
                break;
            case '-':
                res = '-';
                break;
            case '*':
                res = '*';
                break;
            case '/':
                res = '/';
                break;
            case '^':
                res = '^';
                break;
            case '%':
                res = '%';
                break;
        }
        return  res;
    }
}
