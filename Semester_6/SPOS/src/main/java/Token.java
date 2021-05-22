public class Token {

    String tokenStr;
    Type type;

    public Token(String tokenStr, Type type) {
        this.tokenStr = tokenStr;
        this.type = type;
    }

    public String toString() {
        return "Token{" +
                "type=" + type +
                ", tokenString='" + tokenStr + '\'' +
                '}';
    }
}
