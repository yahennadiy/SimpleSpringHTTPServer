package server.queryhandlers;

import server.dboperation.UserAccountHqlSelector;
import server.dboperation.UserAccountUpdater;
import server.persistentclasses.UsersPersistentClass;

import java.util.List;

public class PutHandler {
    public static String exec(String userName, String firstName, String lastName) {
        UsersPersistentClass user;
        String response;
        List userData = UserAccountHqlSelector.exec(userName);
        if (userData == null) {
            response = "database operation error";
        } else if (userData.isEmpty()) {
            response = "username not exists";
        } else {
            user = (UsersPersistentClass) userData.get(0);
            response = UserAccountUpdater.exec(user, userName, firstName, lastName);
        }

        return response;
    }
}
