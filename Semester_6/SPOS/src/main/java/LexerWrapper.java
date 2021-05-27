import com.hp.gagawa.java.elements.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
        assert out != null;
        out.println(html.write());
        out.close();
    }

    public void sortTokens() {
        Map<Type, List<Token>> mappedTokens = tokens.stream().collect(groupingBy(Token::getType));


        mappedTokens.forEach((key, value) -> {
            if (key != Type.WHITESPACE) {
                System.out.println("key : " + key);

                for (Token v : value) {
                    System.out.println("\t" + v.tokenStr);
                }
            }
        });
    }


    public void createTokens() {
        Lexer lexer = new Lexer(fileName);
        lexer.initialize();
        tokens = lexer.generateTokens();
    }
}

