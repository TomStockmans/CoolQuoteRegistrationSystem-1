package be.swsb.cqrs.conversation;

import java.util.Collection;


public class Conversation {

    private Collection<Line> lines;
    private String id;
    private Line punchLine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLines(Collection<Line> lines) {
        this.lines = lines;
    }

    public Collection<Line> getLines() {
        return lines;
    }

    public void setPunchLine(Line punchLine) {
        this.punchLine = punchLine;
    }

    public Line getPunchLine() {
        return punchLine;
    }
}
