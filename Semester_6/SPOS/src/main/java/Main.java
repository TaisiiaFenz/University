public class Main {
    public static void main(String[] args) {
        LexerWrapper lexer = new LexerWrapper("kotlin.txt");
        lexer.printTokens();
        lexer.highlightCode();
        lexer.sortTokens();
    }
}
