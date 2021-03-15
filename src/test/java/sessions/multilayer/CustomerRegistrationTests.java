package sessions.multilayer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sessions.multilayer.data.Customer;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomerRegistrationTests extends CommonTest {

    @ParameterizedTest
    @MethodSource("customersProvider")
    public void canRegisterCustomer(Customer customer) {
        app.loginToAdminPane();
        Set<String> oldIds = app.getCustomerIds();
        app.registerNewCustomer(customer);
        Set<String> newIds = app.getCustomerIds();
        assertThat(oldIds, everyItem(is(in(newIds))));
        assertThat(newIds.size(), equalTo(oldIds.size() + 1));
    }

}
