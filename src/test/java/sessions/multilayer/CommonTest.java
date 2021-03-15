package sessions.multilayer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import sessions.multilayer.application.LiteCartApplication;
import sessions.multilayer.data.Customer;

import java.util.stream.Stream;

public class CommonTest {

    static LiteCartApplication app;

    @BeforeAll
    public static void startDriver() {
     app = new LiteCartApplication();
    }

    @AfterAll
    public static void stopDriver() {
        app.quit();
    }

    static Stream<Customer> customersProvider() {
        return Stream.of(
                Customer.newEntity()
                        .withFirstName("test_Adam").withLastName("test_Smith").withPhone("+0123456789")
                        .withAddress("Hidden Place").withPostcode("12345").withCity("New City")
                        .withCountry("US").withZone("KS")
                        .withEmail("test_Adam" + System.currentTimeMillis() + "@smith.me")
                        .withPassword("qwerty").build(),
                Customer.newEntity()
                        .withFirstName("test_Chuck").withLastName("test_Norris").withPhone("+0123456789")
                        .withAddress("Everywhere").withPostcode("12345").withCity("Test City")
                        .withCountry("US").withZone("KS")
                        .withEmail("test_Chuck" + System.currentTimeMillis() + "@norris.is.watching.you")
                        .withPassword("*******").build()
        );
    }

}
