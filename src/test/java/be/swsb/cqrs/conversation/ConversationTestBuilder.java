package be.swsb.cqrs.conversation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static be.swsb.cqrs.conversation.LineTestBuilder.aContextLine;
import static be.swsb.cqrs.conversation.LineTestBuilder.aSpeechLine;

public class ConversationTestBuilder {

    private String id;
    private List<Line> lines = new ArrayList<>();
    private Line punchLine;

    private ConversationTestBuilder() {
    }

    public static ConversationTestBuilder aConversation() {
        return new ConversationTestBuilder();
    }

    public static ConversationTestBuilder aDefaultConversation() {
        Line context = aContextLine().withText("context").build();
        Line punchLine = aSpeechLine().asPunchLine().withText("punch").withParticipants(new Participant("Gianni",false)).build();
        List<Line> lines = Arrays.asList(context);
        return aConversation().withId(UUID.randomUUID().toString())
                .withLines(lines)
                .withPunchLine(punchLine);
    }

    public Conversation build() {
        List<Line> allLines = new ArrayList<>();
        allLines.addAll(lines);
        allLines.add(punchLine);
        return new Conversation(id, allLines);
    }

    public ConversationTestBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ConversationTestBuilder withLines(List<Line> lines) {
        this.lines = lines;
        return this;
    }

    public ConversationTestBuilder withLines(Line... lines) {
        this.lines = Arrays.asList(lines);
        return this;
    }

    public ConversationTestBuilder withPunchLine(Line punchLine) {
        this.punchLine = punchLine;
        return this;
    }

    public ConversationTestBuilder withPunchLine(String punchLine) {
        this.punchLine = aContextLine().asPunchLine().withText(punchLine).build();
        return this;
    }
}