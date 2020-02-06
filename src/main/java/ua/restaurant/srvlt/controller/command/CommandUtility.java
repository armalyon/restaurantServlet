package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.model.entity.types.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

import static ua.restaurant.srvlt.constants.TextConstants.*;

public class CommandUtility {
   static void setUserAttributes(HttpServletRequest request,
                                 Role role, String username) {
        HttpSession session = request.getSession();
        session.setAttribute(ROLE_ATTRIBUTE, role);
        session.setAttribute(USERNAME_ATTRIBUTE, username);

    }


   static void addUserToLoggedUsers(HttpServletRequest request, String userName) {
        Set<String> loggedUsers = getLoggedUsersSet(request);
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute(LOGGED_USERS_ATTRIBUTE, loggedUsers);

    }

   public static void removeUserFromSessionAndContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Set<String> loggedUsers = CommandUtility.getLoggedUsersSet(request);
        loggedUsers.remove(request.getSession().getAttribute(USERNAME_ATTRIBUTE));
        session.removeAttribute(ROLE_ATTRIBUTE);
        session.removeAttribute(USERNAME_ATTRIBUTE);
        session.invalidate();
    }



    private static Set<String> getLoggedUsersSet(HttpServletRequest request) {
        return (HashSet<String>) request.getSession().getServletContext()
                .getAttribute(LOGGED_USERS_ATTRIBUTE);
    }
}
