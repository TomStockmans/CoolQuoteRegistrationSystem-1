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

    @JsonCreator
    public Conversation(@JsonProperty("id") String id, @JsonProperty("lines") List<Line> lines) {
        this.id = id;
        this.lines = lines;
    }

    public String getId() {
        return id;
    }

    public List<Line> getLines() {
        return lines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lines);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                ", lines=" + lines +
                '}';
    }
}
