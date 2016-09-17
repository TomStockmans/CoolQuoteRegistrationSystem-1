package be.swsb.cqrs.conversation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;


public class Conversation {

    @Id
    private String id;
    private List<Line> lines;
    private Line punchLine;

    @JsonCreator
    public Conversation(@JsonProperty("id") String id, @JsonProperty("lines") List<Line> lines, @JsonProperty("punchLine") Line punchLine) {
        this.id = id;
        this.lines = lines;
        this.punchLine = punchLine;
    }

    public String getId() {
        return id;
    }

    public List<Line> getLines() {
        return lines;
    }

    public Line getPunchLine() {
        return punchLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lines, that.lines) &&
                Objects.equals(punchLine, that.punchLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lines, punchLine);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                ", lines=" + lines +
                ", punchLine=" + punchLine +
                '}';
    }
}
