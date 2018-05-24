package server.queryhandlers;

import server.dboperation.UserAccountHqlSelector;
import server.dboperation.UserAccountUpdater;
import server.persistentclasses.UsersPersistentClass;

import java.util.List;

public class PutHandler {
    public static String exec(String[] userDataArr) {
        UsersPersistentClass user;
        String response;
        List userData = UserAccountHqlSelector.exec(userDataArr[0]);
        if (userData == null) {
            response = "database operation error";
        } else if (userData.isEmpty()) {
            response = "username not exists";
        } else {
            user = (UsersPersistentClass) userData.get(0);
            response = UserAccountUpdater.exec(user, userDataArr);
        }

        return response;
    }
}
