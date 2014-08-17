package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.services.users.UsersManagerFactory;

public final class UsersListIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        List<User> usersList = UsersManagerFactory.getManager().findUsers();
        request.setAttribute("usersList", usersList);

        // Forward control to the specified success URI
        return (mapping.findForward("success"));
    }

}