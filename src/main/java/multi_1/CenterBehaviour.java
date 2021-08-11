package multi_1;

import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.mail.MessagingException;
import javax.swing.*;

public class CenterBehaviour extends TickerBehaviour {
    private final Center agent;
    private final int count = multi_1.MainController.numberOfAgents/5;
    private int current = 0;
    private static int average = 0;

    CenterBehaviour(Center agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
    }

    @Override
    protected void onTick() {
        // Timer
        System.out.println("Center: tick=" + getTickCount());

        if(current==count){
            // Output the total average
            average = average/multi_1.MainController.numberOfAgents;
            JPanel myRootPane = new JPanel();
            System.out.println("Found and delivered Average count is: "+average);
            JOptionPane.showMessageDialog( myRootPane,
                    "Found and delivered Average count is: "+average,
                    "Average-calc Done", JOptionPane.QUESTION_MESSAGE);

            // Emailing
            try {
                new YandexEmailSender(average);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            this.stop();
        } else {
            // Receiving messages
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage receiveMsg  = this.agent.receive(mt);

            if(receiveMsg!=null){
                System.out.println("Center is receiving MSG from Agent " + receiveMsg.getSender()+" with content: " + receiveMsg.getContent());
                average+=Integer.parseInt(receiveMsg.getContent());
                current++;

                receiveMsg.reset();
            }
        }
    }
}
