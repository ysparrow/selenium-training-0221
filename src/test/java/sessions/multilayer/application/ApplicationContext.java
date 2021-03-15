package sessions.multilayer.application;

import org.openqa.selenium.WebDriver;


public class ApplicationContext {

    private WebDriver driver;
    private String baseUrl;
    private AppUser user ;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String BASE_URL) {
        this.baseUrl = BASE_URL;
    }

    public AppUser getAppUser() {
        return user;
    }

    public void setAppUser(AppUser user) {
        this.user = user;
    }

    public static class AppUser {
        String loginName;
        String loginPass;

        public AppUser(String loginName, String loginPass) {
            this.loginName = loginName;
            this.loginPass = loginPass;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getLoginPass() {
            return loginPass;
        }

        public void setLoginPass(String loginPass) {
            this.loginPass = loginPass;
        }

        @Override
        public String toString() {
            return "AppUser{" +
                    "loginName='" + loginName + '\'' +
                    '}';
        }
    }
}

