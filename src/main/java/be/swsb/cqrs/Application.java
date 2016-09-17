package be.swsb.cqrs;

import be.swsb.cqrs.conversation.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
//        implements CommandLineRunner
{

    @Autowired
    private ConversationRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Conversation conv = new Conversation();
//        Line punchLine = new Line();
//        punchLine.setOrder(9);
//        punchLine.setLineType(LineType.SPEECH);
//        punchLine.setParticipants(Arrays.asList(new Participant("Dickbutt", false)));
//        punchLine.setText("testing...1..2..3..");
//
//        conv.setPunchLine(punchLine);
//
//        Line line0 = new Line();
//        line0.setText("contextLine");
//        line0.setLineType(LineType.CONTEXT);
//        line0.setOrder(0);
//
//        Line line1 = new Line();
//        line1.setText("firstLine");
//        line1.setParticipants(Arrays.asList(new Participant("Gianni", false)));
//        line1.setLineType(LineType.SPEECH);
//        line1.setOrder(1);
//
//        Line line2 = new Line();
//        line2.setText("secondLine");
//        line2.setParticipants(Arrays.asList(new Participant("Pablo", false)));
//        line2.setLineType(LineType.SPEECH);
//        line1.setOrder(2);
//
//        conv.setLines(Arrays.asList(line1, line2));
//
//        Conversation savedConvo = repo.save(conv);
//        System.out.println("Persisted convo with id: "+savedConvo.getId());
//    }
}
