package be.swsb.cqrs.conversation;

import java.util.Collection;
import java.util.Objects;

public class Line {

    private String text;
    private LineType lineType;
    private Collection<Participant> participants;
    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public Collection<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return order == line.order &&
                Objects.equals(text, line.text) &&
                lineType == line.lineType &&
                Objects.equals(participants, line.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, lineType, participants, order);
    }

    @Override
    public String toString() {
        return "Line{" +
                "text='" + text + '\'' +
                ", lineType=" + lineType +
                ", participants=" + participants +
                ", order=" + order +
                '}';
    }
}
