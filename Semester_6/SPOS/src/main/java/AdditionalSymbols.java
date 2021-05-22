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

    public static char special(String sequence) {
        return ("\\b" == sequence || "\\t" == sequence
                || "\\n" == sequence || "\\" == sequence
                || "'" == sequence || "\"" == sequence
                || "\\r" == sequence || "\\f" == sequence) ? sequence.charAt(0) : Character.MIN_VALUE;
    }

    public static char octal(char c) {
        return Pattern.matches("[0-7]", Character.toString(c)) ? c : Character.MIN_VALUE;
    }

    public static char binary(char c) {
        return (c == '0' || c == '1') ? c : Character.MIN_VALUE;
    }

    public static char hex(char c) {
        return Pattern.matches("\\d|[a-fA-F]", Character.toString(c)) ? c : Character.MIN_VALUE;
    }

    public static char doubleOrFloat(char c) {
        return (c == 'f' || c == 'F' || c == 'd' || c == 'D') ? c : Character.MIN_VALUE;
    }


}
