package shellhub.github.neteasemusic.model.entities;

import lombok.Data;

@Data
public class NavProfile {
    private String backgroundUrl;
    private String avatarUrl;
    private String nickname;

    public NavProfile(String backgroundUrl, String avatarUrl, String nickname) {
        this.backgroundUrl = backgroundUrl;
        this.avatarUrl = avatarUrl;
        this.nickname = nickname;
    }
}
