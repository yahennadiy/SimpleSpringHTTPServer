package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.queryhandlers.DeleteHandler;
import server.queryhandlers.GetHandler;
import server.queryhandlers.PostHandler;
import server.queryhandlers.PutHandler;
import server.queryhandlers.checkers.ParamOnEmptyChecker;
import server.queryhandlers.checkers.PostPutQueryStructureChecker;
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
        if (ParamOnEmptyChecker.isEmpty(userName)) {
            response = "enter user name, please";
        } else {
            response = GetHandler.exec(userName.trim());
        }

        Main.getLogger().info("Response for GET query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }

    @RequestMapping(method = POST)
    public Response post(@RequestParam(value = "userName", defaultValue = "???") String userName,
                         @RequestParam(value = "firstName", defaultValue = "???") String firstName,
                         @RequestParam(value = "lastName", defaultValue = "???") String lastName) {
        Main.getLogger().info("POST query, userName: " + userName + " ,firstName: " + firstName + " lastName: " +
                lastName);
        if (ParamOnEmptyChecker.isEmpty(userName) || ParamOnEmptyChecker.isEmpty(firstName) || ParamOnEmptyChecker
                .isEmpty(lastName)) {
            response = "enter user data as userName,firstName,lastName";
        } else {
            response = PostHandler.exec(userName.trim(), firstName.trim(), lastName.trim());
        }

        Main.getLogger().info("Response for POST query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }

    @RequestMapping(method = PUT)
    public Response put(@RequestParam(value = "userName", defaultValue = "???") String userName,
                        @RequestParam(value = "firstName", defaultValue = "???") String firstName,
                        @RequestParam(value = "lastName", defaultValue = "???") String lastName) {
        Main.getLogger().info("PUT query, userName: " + userName + " ,firstName: " + firstName + " lastName: " +
                lastName);
        if (ParamOnEmptyChecker.isEmpty(userName) || ParamOnEmptyChecker.isEmpty(firstName) || ParamOnEmptyChecker
                .isEmpty(lastName)) {
            response = "enter user data as userName,firstName,lastName";
        } else {
            response = PutHandler.exec(userName.trim(), firstName.trim(), lastName.trim());
        }

        Main.getLogger().info("Response for PUT query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }

    @RequestMapping(method = DELETE)
    public Response delete(@RequestParam(value = "userName", defaultValue = "???") String userName) {
        Main.getLogger().info("DELETE query, userName: " + userName);
        if (ParamOnEmptyChecker.isEmpty(userName)) {
            response = "enter user name, please";
        } else {
            response = DeleteHandler.exec(userName.trim());
        }

        Main.getLogger().info("Response for DELETE query: " + response);
        return new Response(queryCounter.incrementAndGet(), String.format(responseTemplate, response));
    }
}
