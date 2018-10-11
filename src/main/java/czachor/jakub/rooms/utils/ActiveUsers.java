package czachor.jakub.rooms.utils;

import java.util.*;

public class ActiveUsers {
    private List<User> users;

    public ActiveUsers() {
        this.users = new LinkedList<>();
    }

    public void putNewActiveUser(String sessionId, String username){
        this.users.add(new User(sessionId, username));
    }

    public String getUsernameBySessionId(String sessionId){
        for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.sessionId.equals(sessionId)) {
                return user.username;
            }
        }
        return null;
    }

    public String getSessionIdByUsername(String username){
        for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.username.equals(username)) {
                return user.sessionId;
            }
        }
        return null;
    }

    public void changeActiveUserUsername(String sessionId, String newUsername) {
        for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.sessionId.equals(sessionId)) {
                user.username = newUsername;
            }
        }
    }

    private class User {
        public User(String sessionId, String username) {
            this.sessionId = sessionId;
            this.username = username;
        }

        private String sessionId;
        private String username;
    }
}
