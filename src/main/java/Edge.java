import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Edge.class, name="edge")
})
public class Edge implements Serializable {
    public int a;
    public int b;
    public int n;

    public Edge() {}

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getN() {
        return n;
    }
}
