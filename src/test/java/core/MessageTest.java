package core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by frutos on 12/12/13.
 */
public class MessageTest {

    @Test
    public void serializesToJSON() throws Exception {
        Message message = new Message("user1", "hi there");
        assertThat("a Messages can be serialized to JSON",
                asJson(message),
                is(equalTo(jsonFixture("fixtures/message.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        Message message = new Message("user1", "hi there");
        assertThat("a Messages can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/message.json"), Message.class),
                is(message));
    }


}
