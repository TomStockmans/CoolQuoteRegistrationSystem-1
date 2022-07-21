package be.swsb.cqrs.conversation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Line {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String text;
    private boolean punchLine;
    private LineType lineType;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "FK_PARTICIPANT_ID", referencedColumnName = "ID")
    private List<Participant> participants;

    //hibernate
    public Line() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPunchLine() {
        return punchLine;
    }

    public void setPunchLine(boolean punchLine) {
        this.punchLine = punchLine;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    @JsonIgnore
    public boolean isSpeechLine() {
        return LineType.SPEECH.equals(lineType);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(text, line.text) &&
                lineType == line.lineType &&
                punchLine == line.punchLine &&
                Objects.equals(participants, line.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, lineType, punchLine, participants);
    }

    @Override
    public String toString() {
        return "Line{" +
                "text='" + text + '\'' +
                ", lineType=" + lineType +
                ", isPunchLine=" + punchLine +
                ", participants=" + participants +
                '}';
    }

    public static boolean isValid(Line line) {
        return !line.isSpeechLine() || !line.getParticipants().isEmpty();
    }
}
