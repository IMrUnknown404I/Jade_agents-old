package multi_1;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

class MainController {
    public static final int numberOfAgents = 20;

    void initAgents() {
// Retrieve the singleton instance of the JADE Runtime
        Runtime rt = Runtime.instance();

// Create a container to host the Default Agent
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "10098");
        p.setParameter(Profile.GUI, "true");
        ContainerController cc = rt.createMainContainer(p);

        try {
            // All agents incoming
            for(int i=1; i <= MainController.numberOfAgents; i++) {
                AgentController agent = cc.createNewAgent(Integer.toString(i),
                        "multi_1.DefaultAgent", null);
                agent.start();
            }
            // Center incoming
            AgentController center = cc.createNewAgent("Center","multi_1.Center",null);
            center.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}