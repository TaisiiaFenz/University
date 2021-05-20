import java.util.List;

public class LexerWrapper {
    String fileName;
    List<Token> tokens;

    public LexerWrapper(String fileName) {
        this.fileName = fileName;
    }

    public void printTokens() {

    }

    public void highlightCode() {

    }

    public void sortTokens() {

    }

    public void createTokens() {
        Lexer lexer = new Lexer(fileName);
        lexer.initialize();
        //tokens = lexer.generateTokens();
    }
}

