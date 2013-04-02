package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class EntryPointAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
/*

		System.out.println("request.getAuthType() " + request.getAuthType());
		System.out.println("request.getContextPath() " + request.getContextPath());
		System.out.println("request.getPathInfo() " + request.getPathInfo());
		System.out.println("request.getPathTranslated() " + request.getPathTranslated());
		System.out.println("request.getProtocol() " + request.getProtocol());
		System.out.println("request.getRemoteAddr() " + request.getRemoteAddr());
		System.out.println("request.getRemoteHost() " + request.getRemoteHost());
		System.out.println("request.getRemoteUser() " + request.getRemoteUser());
		System.out.println("request.getRequestURI() " + request.getRequestURI());
		System.out.println("request.getRequestURL() " + request.getRequestURL());
		System.out.println("request.getServerName() " + request.getServerName());
		System.out.println("request.getServerPort() " + request.getServerPort());
		System.out.println("request.getServletPath() " + request.getServletPath());
*/
		// Forward control to the specified success URI
		return mapping.findForward("success");
		//return mapping.findForward("successTest");
	}
}
