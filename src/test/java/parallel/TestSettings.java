package parallel;

import java.io.File;

public class TestSettings {

    String BASE_URL = "";
    String LOGIN_NAME = "";
    String LOGIN_PASS = "";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public String getLOGIN_PASS() {
        return LOGIN_PASS;
    }

    public String getTestURL() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.html").getFile());
        return "file:///"+
                file.getAbsolutePath();
    }

}
