package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.model.hibernate.Category;
import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.struts.forms.SearchStatsConsumptionProductsForm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class SearchStatsConsumptionProductsAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        SearchStatsConsumptionProductsForm searchStatsConsumptionProductsForm = (SearchStatsConsumptionProductsForm) form;
        if(searchStatsConsumptionProductsForm.getActionDo() == null || searchStatsConsumptionProductsForm.getActionDo().toUpperCase().equals("DISPLAY"))
        {
            return (new ActionForward(mapping.getInput()));
        }

        String selectBy = searchStatsConsumptionProductsForm.getSelectBy();
        String code = searchStatsConsumptionProductsForm.getCode();
        String quantityOperator = searchStatsConsumptionProductsForm.getQuantityOperator();
        Float quantity = searchStatsConsumptionProductsForm.getQuantity();
        Short day = searchStatsConsumptionProductsForm.getDay();
        Short month = searchStatsConsumptionProductsForm.getMonth();
        Short year = searchStatsConsumptionProductsForm.getYear();

	    if(day.floatValue()==-1)
	        day = null; 
	    if(month.floatValue()==-1)
	        month = null; 
	    if(year.floatValue()==-1)
	        year = null; 

        List list = null;
        if(selectBy!=null)
        {
            if(selectBy.equals("NULL"))
                list = MenusManagerFactory.getManager().getProductsNotConsumed(day, month, year);
            else if(selectBy.equals("DELETED"))
                list = MenusManagerFactory.getManager().getStatsConsumptionProducts(code, quantity, day, month, year, "DESC", quantityOperator, new Boolean(true));
            else if(selectBy.equals("ALL"))
                list = MenusManagerFactory.getManager().getStatsConsumptionProducts(code, quantity, day, month, year, "DESC", quantityOperator, new Boolean(false));
            else if(selectBy.startsWith("CAT"))
            {
                Long categoryId = null;
                try{categoryId = new Long(Long.parseLong(selectBy.substring("CAT".length())));}catch(Exception e){}
                if(categoryId!=null)
                {
                    list = MenusManagerFactory.getManager().getStatsConsumptionProducts(code, quantity, day, month, year, "DESC", quantityOperator, new Boolean(false), categoryId);
                    Category category = MenusManagerFactory.getManager().getCategory(categoryId);
                    request.setAttribute("category", category);
                }
            }
        }
        request.setAttribute("statsConsumptionProductsList", list);

        // Forward control to the specified success URI
        return (mapping.findForward("success"));
    }
    
}
