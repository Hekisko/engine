package io.lumeer.core.model.types.user;

public class UserExample {

    private String user;

    public UserExample() {
        this("lumeer@email.com");
    }

    public UserExample(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserExample{" +
                "user='" + user + '\'' +
                '}';
    }
}
