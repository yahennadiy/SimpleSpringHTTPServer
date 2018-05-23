package server.queryhandlers;

import server.dboperation.UserHqlSelector;
import server.persistentclasses.UsersPersistentClass;
import server.queryhandlers.dataconverters.UserStringPicker;

import java.util.List;

public class GetHandler {
    public static String exec(String userName) {
        String response;
        UsersPersistentClass user;
        List userData = UserHqlSelector.exec(userName);
        if (userData == null) {
            response = "database operation error";
        } else if (userData.isEmpty()) {
            response = "username not found";
        } else {
            user = (UsersPersistentClass) userData.get(0);
            response = UserStringPicker.get(user);
        }

        return response;
    }
}
