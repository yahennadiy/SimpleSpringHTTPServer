package server.queryhandlers;

import server.dboperation.UserAccountHqlSelector;
import server.dboperation.UserAccountSaver;

import java.util.List;

public class PostHandler {
    public static String exec(String[] userDataArr) {
        String response;
        List userData = UserAccountHqlSelector.exec(userDataArr[0]);
        if (userData == null) {
            response = "database operation error";
        } else if (!userData.isEmpty()) {
            response = "username already exists";
        } else {
            response = UserAccountSaver.exec(userDataArr);
        }

        return response;
    }
}
