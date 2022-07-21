package be.swsb.cqrs.conversation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private boolean victim;

    @JsonCreator
    public Participant(@JsonProperty("name") String name, @JsonProperty("victim") boolean victim) {
        this.name = name;
        this.victim = victim;
    }

    //hibernate
    protected Participant() {
    }

    public String getName() {
        return name;
    }

    public boolean isVictim() {
        return victim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVictim(boolean victim) {
        this.victim = victim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return victim == that.victim &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, victim);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", victim=" + victim +
                '}';
    }
}
