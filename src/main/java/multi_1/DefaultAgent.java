package multi_1;

import jade.core.Agent;
import java.util.concurrent.TimeUnit;
import java.security.SecureRandom;

public class DefaultAgent extends Agent {
    public static int[] linkedAgents = new int[4];
    private int number;
    private boolean isLocal;
    private boolean isLast;
    private SecureRandom secureRandom = new SecureRandom();

    @Override
    protected void setup() {
        int id = Integer.parseInt(getAID().getLocalName());
        System.out.println("Agent #" + id + " initialized");

        this.isLocal = this.linkedAgents[this.linkedAgents.length-1]!=0 ? true:false;
        this.number = secureRandom.nextInt(101);
        this.isLast = id==20?true:false;

        if(id<6){
            if(id!=5) addBehaviour(new AgentsSendToLocal(this, this.number, this.isLocal));
            else addBehaviour(new AgentsTickBehaviour(this, TimeUnit.SECONDS.toMillis(1), this.number));

            for(int t=1;t<=id;t++) if(id-t!=0) {
                this.linkedAgents[t-1] = t;
            }
        } else if(id<11){
            if(id!=10) addBehaviour(new AgentsSendToLocal(this, this.number, this.isLocal));
            else addBehaviour(new AgentsTickBehaviour(this, TimeUnit.SECONDS.toMillis(1), this.number));

            for(int t=6;t<=id;t++) if(id-t!=0) {
                this.linkedAgents[t-6] = t;
            }
        } else if(id<16){
            if(id!=15) addBehaviour(new AgentsSendToLocal(this, this.number, this.isLocal));
            else addBehaviour(new AgentsTickBehaviour(this, TimeUnit.SECONDS.toMillis(1), this.number));

            for(int t=11;t<=id;t++) if(id-t!=0) {
                this.linkedAgents[t-11] = t;
            }
        }else if(id<21){
            if(id!=20) addBehaviour(new AgentsSendToLocal(this, this.number, this.isLocal));
            else addBehaviour(new AgentsTickBehaviour(this, TimeUnit.SECONDS.toMillis(1), this.number));

            for(int t=16;t<=id;t++) if(id-t!=0) {
                this.linkedAgents[t-16] = t;
            }
        }
    }
}


//        addBehaviour(new Behaviour() {
//        });