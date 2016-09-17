package be.swsb.cqrs.conversation;

import org.junit.Before;
import org.junit.Test;

import static be.swsb.cqrs.conversation.ConversationTestBuilder.aDefaultConversation;
import static org.assertj.core.api.Assertions.assertThat;

public class ConversationValidatorTest {

    private ConversationValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new ConversationValidator();
    }

    @Test
    public void validate_AnyLineWithNegativeOrder_DoesNotValidate() throws Exception {
        Line punchLine = new Line();
        punchLine.setOrder(-1);
        Conversation conversation = aDefaultConversation().withId(null).withPunchLine(punchLine).build();

        assertThat(validator.validate(conversation)).isFalse();
    }

    @Test
    public void validate_AllLineWithPositiveOrder_Validates() throws Exception {
        Line punchLine = new Line();
        punchLine.setOrder(0);
        Line textLine = new Line();
        textLine.setOrder(1);
        Conversation conversation = aDefaultConversation().withId(null).withLines(textLine).withPunchLine(punchLine).build();

        assertThat(validator.validate(conversation)).isTrue();
    }
}