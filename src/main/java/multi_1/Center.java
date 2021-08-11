package multi_1;

import jade.core.Agent;
import java.util.concurrent.TimeUnit;

public class Center extends Agent {
    @Override
    protected void setup() {
        System.out.println("Center initialized");

        addBehaviour(new CenterBehaviour(this, TimeUnit.SECONDS.toMillis(1)));
    }
}
