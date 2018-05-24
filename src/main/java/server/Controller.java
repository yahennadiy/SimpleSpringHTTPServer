package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.queryhandlers.DeleteHandler;
import server.queryhandlers.GetHandler;
import server.queryhandlers.PostHandler;
import server.queryhandlers.PutHandler;
import server.queryhandlers.checkers.PostPutQueryStructureChecker;
import server.queryhandlers.checkers.QueryOnEmptyChecker;
import server.queryhandlers.dataconverters.QueryConverter;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

        Main.getLogger().info("Response for GET query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }

    @RequestMapping(method = POST)
    public Response post(@RequestParam(value = "userData", defaultValue = "???") String userDataOnQuery) {
        Main.getLogger().info("POST query, userData: " + userDataOnQuery);
        String responseOnInvalidQuery = "enter user data as userName,firstName,lastName";
        if (QueryOnEmptyChecker.isEmpty(userDataOnQuery)) {
            response = responseOnInvalidQuery;
        } else {
            String[] userDataArr = QueryConverter.convert(userDataOnQuery);
            if (!PostPutQueryStructureChecker.isValid(userDataArr)) {
                response = responseOnInvalidQuery;
            } else {
                response = PostHandler.exec(userDataArr);
            }
        }

        Main.getLogger().info("Response for POST query: " + response);
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
            if (!PostPutQueryStructureChecker.isValid(userDataArr)) {
                response = responseOnInvalidQuery;
            } else {
                response = PutHandler.exec(userDataArr);
            }
        }

        Main.getLogger().info("Response for PUT query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }

    @RequestMapping(method = DELETE)
    public Response delete(@RequestParam(value = "userName", defaultValue = "???") String userName) {
        Main.getLogger().info("DELETE query, userName: " + userName);
        if (QueryOnEmptyChecker.isEmpty(userName)) {
            response = "enter user name, please";
        } else {
            response = DeleteHandler.exec(userName.trim());
        }

        Main.getLogger().info("Response for DELETE query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }
}
