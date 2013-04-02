package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class ProductsListIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

        String sortListBy = request.getParameter("sortListBy");
        if(sortListBy == null || sortListBy.length() < 1)
            sortListBy = "id";
        String sortMonotony = request.getParameter("sortMonotony");
        if(sortMonotony == null || sortListBy.length() < 1)
            sortMonotony = "asc";
        String pageSizeStr = request.getParameter("pageSize");
        if(pageSizeStr != null && pageSizeStr.startsWith("CAT"))
        {
            List categoriesRelationList = MenusManagerFactory.getManager().getProductsByCategory(pageSizeStr.substring(3));
            request.setAttribute("categoriesRelationList", categoriesRelationList);
        }
        else
        {
            int pageSize = 10, pageNumber = 1, totalPages = 0;
            try
            {
                pageSize = Integer.parseInt(request.getParameter("pageSize"));
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
                totalPages = Integer.parseInt(request.getParameter("totalPages"));
            }
            catch (NumberFormatException nfe)
            {
            }
            if(pageNumber < 1)
                pageNumber = 1;
            else if(totalPages != 0 && pageNumber > totalPages)
                pageNumber = totalPages;

            StringBuffer productsSum = new StringBuffer();
            List productsList = MenusManagerFactory.getManager().getProducts(pageSize, pageNumber - 1, sortListBy, sortMonotony, productsSum);
            request.setAttribute("productsList", productsList);

            try
            {
                int productsSumInt = Integer.parseInt(productsSum.toString());
                totalPages = productsSumInt / pageSize + (productsSumInt % pageSize == 0 ? 0 : 1);
            }
            catch (NumberFormatException nfe)
            {
            }
            request.setAttribute("productsSum", productsSum.toString());
            request.setAttribute("totalPages", totalPages + "");
            request.setAttribute("pageSize", pageSize + "");
            request.setAttribute("pageNumber", pageNumber + "");
            request.setAttribute("sortListBy", sortListBy);
            request.setAttribute("sortMonotony", sortMonotony);
        }

        List categoriesList = MenusManagerFactory.getManager().getCategories();
        request.setAttribute("categoriesList", categoriesList);

        // Forward control to the specified success URI
        return (mapping.findForward("success"));
    }

}