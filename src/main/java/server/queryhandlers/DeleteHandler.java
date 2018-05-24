package server.queryhandlers;

import server.dboperation.UserAccountHqlSelector;
import server.dboperation.UserAccountRemover;
import server.persistentclasses.UsersPersistentClass;

import java.util.List;

public class DeleteHandler {
    public static String exec(String userName) {
        String response;
        UsersPersistentClass user;
        List userData = UserAccountHqlSelector.exec(userName);
        if (userData == null) {
            response = "database operation error";
        } else if (userData.isEmpty()) {
            response = "username not found";
        } else {
            user = (UsersPersistentClass) userData.get(0);
            response = UserAccountRemover.exec(user);
        }

        return response;
    }
}
