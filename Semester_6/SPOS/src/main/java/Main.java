public class Main {
    public static void main(String[] args) {
        LexerWrapper lexer = new LexerWrapper("kotlin.txt");
        lexer.createTokens();
        lexer.printTokens();
        lexer.highlightCode();
        lexer.sortTokens();
    }
}
