import java.util.List;

public class LexerWrapper {
    String fileName;
    List<Token> tokens;

    public LexerWrapper(String fileName) {
        this.fileName = fileName;
    }

    public void printTokens() {
        int i = 0;
        for (Token token : tokens) {
            System.out.println((++i) + "   " + token.toString() + "\n");
        }
    }

    public void highlightCode() {

    }

    public void sortTokens() {

    }

    public void createTokens() {
        Lexer lexer = new Lexer(fileName);
        lexer.initialize();
        tokens = lexer.generateTokens();
    }
}

