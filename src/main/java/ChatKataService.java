import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import core.MessagesPersistence;
import resources.ChatKataConfig;
import resources.ChatKataResource;

/**
 * Created by frutos on 11/12/13.
 */
public class ChatKataService  extends Service<ChatKataConfig> {

    public static void main(String[] args) throws Exception {
        new ChatKataService().run(args);
    }

    @Override
    public void initialize(Bootstrap bootstrap) {
        bootstrap.setName("chat kata server");
    }

    @Override
    public void run(ChatKataConfig chatKataConfig, Environment environment) throws Exception {
        environment.addResource(new ChatKataResource(new MessagesPersistence()));
    }
}