package be.swsb.cqrs.conversation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate conversationDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "FK_CONVERSATION_ID", referencedColumnName = "ID")
    private List<Line> lines;

    @JsonCreator
    public Conversation(@JsonProperty("lines") List<Line> lines,
                        @JsonProperty("createdOn") LocalDateTime createdOn,
                        @JsonProperty("conversationDate") LocalDate conversationDate
                        ) {
        this.createdOn = createdOn == null ? LocalDateTime.now() : createdOn;
        this.conversationDate = conversationDate;
        this.lines = lines;
    }

    //hibernate
    protected Conversation() {}


    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDate getConversationDate() {
        return conversationDate;
    }

    public List<Line> getLines() {
        return lines;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lines, that.lines) &&
                Objects.equals(conversationDate, that.conversationDate) &&
                Objects.equals(createdOn, that.createdOn)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lines, conversationDate, createdOn);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", conversationDate=" + conversationDate +
                ", lines=" + lines +
                ", Test" +
                '}';
    }
}
