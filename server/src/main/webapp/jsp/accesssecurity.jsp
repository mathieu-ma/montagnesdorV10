<%
	System.out.println("Accesssecurity.jsp");
	if(request.getAttribute(fr.montagnesdor.restaurant.controleur.MontagnesDOrActionServlet.TAG_ACCESS_IN_JSP)==null)
	{
		response.sendRedirect(fr.montagnesdor.restaurant.controleur.MontagnesDOrActionServlet.getSchemeHTTP() + "://" + request.getServerName() + ":" + fr.montagnesdor.restaurant.controleur.MontagnesDOrActionServlet.getPortHTTP()+ request.getContextPath() + fr.montagnesdor.restaurant.controleur.MontagnesDOrActionServlet.getIndex());
	}
%>
