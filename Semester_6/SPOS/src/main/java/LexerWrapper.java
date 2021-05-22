import com.hp.gagawa.java.elements.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class LexerWrapper {
    String fileName;
    List<Token> tokens;

    public LexerWrapper(String fileName) {
        this.fileName = fileName;
    }

    public void printTokens() {
        int i = 0;
        System.out.println(tokens.size());
        for (Token token : tokens) {
            System.out.println((++i) + "   " + token.toString() + "\n");
        }
    }

    public void highlightCode() {
        File f = new File("./src/main/resources/index.html");
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Html html = new Html();
        Head head = new Head();
        Title title = new Title().appendChild(new Text("Reporting Page"));

        Link link = new Link().setRel("stylesheet").setHref("style.css");
        head.appendChild(title);
        head.appendChild(link);
        head.appendText("<meta charset='UTF-8'>");
        html.appendChild(head);

        Body body = new Body();

        for (Token token : tokens) {
            Span span = new Span().setCSSClass(token.type.toString().toLowerCase()).appendText(token.tokenStr);
            body.appendChild(span);
        }

        html.appendChild(body);
        out.println(html.write());
        out.close();
    }

    public void sortTokens() {

    }

    public void createTokens() {
        Lexer lexer = new Lexer(fileName);
        lexer.initialize();
        tokens = lexer.generateTokens();
    }
}

