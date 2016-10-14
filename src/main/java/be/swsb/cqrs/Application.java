package be.swsb.cqrs;

import be.swsb.cqrs.conversation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class Application
        implements CommandLineRunner
{

    @Autowired
    private ConversationRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repo.deleteAll();
        Line line0 = new Line();
        line0.setText("contextLine");
        line0.setLineType(LineType.CONTEXT);

        Line line1 = new Line();
        line1.setText("firstLine");
        line1.setParticipants(Arrays.asList(new Participant("Gianni")));
        line1.setLineType(LineType.SPEECH);

        Line line2 = new Line();
        line2.setText("secondLine");
        line2.setParticipants(Arrays.asList(new Participant("Pablo")));
        line2.setLineType(LineType.SPEECH);

        Line punchLine = new Line();
        punchLine.setLineType(LineType.SPEECH);
        punchLine.setParticipants(Arrays.asList(new Participant("Dickbutt")));
        punchLine.setText("testing...1..2..3..");
        punchLine.setPunchLine(true);

        LocalDateTime createdOn = LocalDateTime.now();
        LocalDate conversationDate = LocalDate.now();
        Conversation conv = new Conversation(UUID.randomUUID().toString(), Arrays.asList(line1, line2, punchLine), createdOn, conversationDate);
        Conversation savedConvo = repo.save(conv);
        System.out.println("Persisted convo with id: "+savedConvo.getId());
    }
}
