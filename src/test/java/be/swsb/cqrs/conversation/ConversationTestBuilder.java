package be.swsb.cqrs.conversation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
        Line context = new Line();
        context.setText("context");
        Line punchLine = new Line();
        punchLine.setText("punch");
        List<Line> lines = Arrays.asList(context);
        return aConversation().withId(UUID.randomUUID().toString())
                .withLines(lines)
                .withPunchLine(punchLine);
    }

    public Conversation build() {
        return new Conversation(id, lines, punchLine);
    }

    public ConversationTestBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ConversationTestBuilder withLines(List<Line> lines) {
        this.lines = lines;
        return this;
    }

    public ConversationTestBuilder withPunchLine(Line punchLine) {
        this.punchLine = punchLine;
        return this;
    }
}