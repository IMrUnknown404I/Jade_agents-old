package multi_1;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AgentsSendToLocal extends jade.core.behaviours.OneShotBehaviour  {
    private final DefaultAgent agent;
    private boolean local;
    private int number;

    private int receiver = 0;

    public AgentsSendToLocal(DefaultAgent agent, int num, boolean IsLocal){
        super(agent);
        this.agent = agent;
        this.local = IsLocal;
        this.number = num;
    }
    public void action() {
        System.out.println("Agent " + this.agent.getLocalName() + ": tick=1");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

        // Setting up receivers up on id-s
        if(!this.local) {
            if(Integer.parseInt(this.agent.getLocalName())<5) {
                msg.addReceiver(new AID("5", AID.ISLOCALNAME));
                receiver = 5;
            }
            else if(Integer.parseInt(this.agent.getLocalName())!=5 && Integer.parseInt(this.agent.getLocalName())<10){
                msg.addReceiver(new AID("10",AID.ISLOCALNAME));
                receiver = 10;
            }
            else if(Integer.parseInt(this.agent.getLocalName())!=10 && Integer.parseInt(this.agent.getLocalName())<15){
                msg.addReceiver(new AID("15",AID.ISLOCALNAME));
                receiver = 15;
            }
            else if(Integer.parseInt(this.agent.getLocalName())!=15 && Integer.parseInt(this.agent.getLocalName())<20){
                msg.addReceiver(new AID("20",AID.ISLOCALNAME));
                receiver = 20;
            }

            msg.setLanguage("English");
            msg.setOntology("Barter-ontology");
            msg.setContent(Integer.toString(this.number));
            myAgent.send(msg);

            System.out.println("Agent " + this.agent.getLocalName() + ": is sending MSG to Agent " + receiver);
        }
    }
}
