package ua.restaurant.srvlt.controller;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.type.Command;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static ua.restaurant.srvlt.constants.TextConstants.INDEX_PAGE;
import static ua.restaurant.srvlt.constants.TextConstants.LOGGED_USERS_ATTRIBUTE;

public class DispatcherServlet extends HttpServlet {
    private Map<String, ua.restaurant.srvlt.controller.command.Command> commands = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class);

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute(LOGGED_USERS_ATTRIBUTE, new HashSet<String>());

        Arrays.stream(Command.values())
                .forEach(
                        c -> commands.put(
                                c.name().toLowerCase(), c.getCommand())
                );
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "");
        LOGGER.debug(path);
        ua.restaurant.srvlt.controller.command.Command command = commands.getOrDefault(path,
                (req) -> INDEX_PAGE);
        String page = command.execute(request);
        if (page.contains("redirect:")){
            page = page.replace("redirect:", "");
            response.sendRedirect(page);
        } else
        request.getRequestDispatcher(page).forward(request, response);
    }


}
