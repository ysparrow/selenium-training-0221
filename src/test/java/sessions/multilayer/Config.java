package sessions.multilayer;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.Classpath("litecart.properties")
public interface Config {

    @Property("baseUrl")
    String getBaseUrl();

    @Property("adminName")
    String getAdminUserName();

    @Property("adminPass")
    String getAdminUserPass();
}
