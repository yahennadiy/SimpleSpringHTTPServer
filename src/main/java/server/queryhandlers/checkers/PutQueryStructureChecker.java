package server.queryhandlers.checkers;

import server.persistentclasses.UsersPersistentClass;

public class PutQueryStructureChecker {
    public static boolean isValid(String[] userDataArr) {
        boolean result = false;
        if (userDataArr.length == UsersPersistentClass.getNumberOfFields()) {
            result = true;
        }

        return result;
    }
}
