package shellhub.github.neteasemusic.model;

import lombok.Data;

@Data
public class User {
    private String uid;
    private String username;
    private String password;

    public User(String uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
}
