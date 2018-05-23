package server.queryhandlers.dataconverters;

import server.persistentclasses.UsersPersistentClass;

public class UserStringPicker {
    public static String get(UsersPersistentClass user) {
        return  "userName: " + user.getUserName() + ", userID: " + user.getUserId() + ", userFirstName: "
                + user.getFirstName() + ", userLastName: " + user.getLastName();
    }
}
