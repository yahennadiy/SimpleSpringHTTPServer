package server.queryhandlers;

import server.dboperation.UserHqlSelector;
import server.dboperation.UserSaver;

import java.util.List;

public class PutHandler {
    public static String exec(String[] userDataArr) {
        String response;
        List userData = UserHqlSelector.exec(userDataArr[0]);
        if (userData == null) {
            response = "database operation error";
        } else if (!userData.isEmpty()) {
            response = "user name already exists";
        } else {
            response = UserSaver.exec(userDataArr);
        }

        return response;
    }
}
