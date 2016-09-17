package be.swsb.cqrs.conversation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;


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
}
