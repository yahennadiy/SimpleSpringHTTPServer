package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.queryhandlers.GetHandler;
import server.queryhandlers.PutHandler;
import server.queryhandlers.checkers.PutQueryStructureChecker;
import server.queryhandlers.checkers.QueryOnEmptyChecker;
import server.queryhandlers.dataconverters.QueryConverter;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class Controller {
    private static final String responseTemplate = "%s";
    private final AtomicLong queryCounter = new AtomicLong();
    private String response;

    @RequestMapping(method = GET)
    public Response get(@RequestParam(value = "userName", defaultValue = "???") String userName) {
        Main.getLogger().info("GET query, userName: " + userName);
        if (QueryOnEmptyChecker.isEmpty(userName)) {
            response = "enter user name, please";
        } else {
            response = GetHandler.exec(userName.trim());
        }

        Main.getLogger().info("Response on GET query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }

    @RequestMapping(method = PUT)
    public Response put(@RequestParam(value = "userData", defaultValue = "???") String userDataOnQuery) {
        Main.getLogger().info("PUT query, userData: " + userDataOnQuery);
        String responseOnInvalidQuery = "enter user data as userName,firstName,lastName";
        if (QueryOnEmptyChecker.isEmpty(userDataOnQuery)) {
            response = responseOnInvalidQuery;
        } else {
            String[] userDataArr = QueryConverter.convert(userDataOnQuery);
            if (!PutQueryStructureChecker.isValid(userDataArr)) {
                response = responseOnInvalidQuery;
            } else {
                response = PutHandler.exec(userDataArr);
            }
        }

        Main.getLogger().info("Response on PUT query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }
}
