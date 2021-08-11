package multi_1;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentsTickBehaviour extends TickerBehaviour {
    private final DefaultAgent agent;
    private static int sum = 0;

    private int time = 0;

    AgentsTickBehaviour(DefaultAgent agent, long period, int num) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.sum = num;
    }
    private void Sum(int x){
        sum += x;
    }

    @Override
    protected void onTick() {
        // Timer
        System.out.println("Agent " + this.agent.getLocalName() + ": tick=" + (getTickCount()+1));

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

        // SENDING TO CENTER
        if (time==4){
            msg.addReceiver(new AID("Center",AID.ISLOCALNAME));
            msg.setLanguage("English");
            msg.setOntology("Barter-ontology");
            msg.setContent(Integer.toString(sum));

            myAgent.send(msg);
            System.out.println("Agent " + this.agent.getLocalName() + ": is sending SUM to Center!");

            this.stop();
        }
        // RECEIVING INCOME MSG
        else {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage receiveMsg  = myAgent.receive(mt);
            if(receiveMsg!=null){
                System.out.println("Agent " + this.agent.getLocalName() + ": is receiving MSG from Agent " + receiveMsg.getSender());

                Sum(Integer.parseInt(receiveMsg.getContent()));
                time++;
                receiveMsg.reset();
            }
        }
    }
}