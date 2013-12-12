package core;

/**
 * Created by frutos on 12/12/13.
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class MessagesTest {

    @Test
    public void serializesToJSON() throws Exception {
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("user1","hi there"));
        messages.add(new Message("user2","hola"));
        final Messages message = new Messages(6,messages);
        assertThat("a Messages can be serialized to JSON",
                asJson(message),
                is(equalTo(jsonFixture("fixtures/messages.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("user1","hi there"));
        messages.add(new Message("user2","hola"));
        final Messages message = new Messages(6,messages);
        assertThat("a Messages can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/messages.json"), Messages.class),
                is(message));
    }


}
