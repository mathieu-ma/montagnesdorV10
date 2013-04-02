package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.Category;
import fr.montagnesdor.restaurant.model.hibernate.CategoryRelation;
import fr.montagnesdor.restaurant.model.hibernate.Product;
import fr.montagnesdor.restaurant.model.hibernate.ValueAddedTax;
import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.services.menus.spi.DeleteProductException;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.SaveOrUpdateProductForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SaveOrUpdateProductAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		ActionErrors errors = new ActionErrors();
		SaveOrUpdateProductForm saveOrUpdateProductForm = (SaveOrUpdateProductForm) form;
		List categoriesList = processRequiredAttribute(request, saveOrUpdateProductForm.getCode());
		if(saveOrUpdateProductForm.getActionDo()==null || saveOrUpdateProductForm.getActionDo().toUpperCase().equals("DISPLAY"))
	    {
		    return (new ActionForward(mapping.getInput()));
	    }
		else
		{
			String code = saveOrUpdateProductForm.getCode();
			Product product = MenusManagerFactory.getManager().getProduct(code);
			
			List categoriesRelationCheckedList = null;
			if(categoriesList!=null)
			{    
			    String categoryChecked = null;
			    String[] categoryCheckedArray = null;
			    Category category = null;
			    CategoryRelation categoryRelation = null;
			    categoriesRelationCheckedList = new ArrayList(categoriesList.size());
			    for(int i=0; i<categoriesList.size(); i++)
			    {
			        category = (Category)categoriesList.get(i);
			        if((categoryChecked = request.getParameter("category"+category.getId().longValue()))!=null)
			        {   
			            categoryCheckedArray = categoryChecked.split("-"); 
				        categoryRelation = new CategoryRelation();
				        categoryRelation.setCategory(category);
			            categoryRelation.setProduct(product);
				        if(categoryCheckedArray.length==2 && categoryCheckedArray[1]!=null && !categoryCheckedArray[1].equals(""))
				        {
				            categoryRelation.setQuantity(Float.parseFloat(categoryCheckedArray[1]));
				        }
				        categoriesRelationCheckedList.add(categoryRelation);
			        }    
			    }
			}    
				
			
			if(saveOrUpdateProductForm.getActionDo().toUpperCase().equals("CREATE"))
			{
			    if(product==null)
			    {
			         //Création du produit
			        product = new Product();

			        product.setId(saveOrUpdateProductForm.getCode());
					product.setCurrentLanguage(userSession.getCurrentLanguage());
					product.setCurrentLabel(saveOrUpdateProductForm.getLabel());
					product.setColorRGB(saveOrUpdateProductForm.getColorRGB());
					product.setPrice(saveOrUpdateProductForm.getPrice());
					MenusManagerFactory.getManager().saveOrUpdateProduct(product, categoriesRelationCheckedList, saveOrUpdateProductForm.getVatId(), "SAVE");
			    }
			    else
			    {
			        request.setAttribute("product", null);
			        //Impossible de créer ce produit car il existe déjà
			        errors.add("saveOrUpdateProduct.jsp.error.create.forbidden",  new ActionError("saveOrUpdateProduct.jsp.error.create.forbidden"));
			    }
			}
			else if(saveOrUpdateProductForm.getActionDo().toUpperCase().equals("UPDATE"))
			{    
			    if(product==null)
			    {
			        request.setAttribute("product", null);
			        //Impossible de mettre à jour ce produit car il n'existe plus
			        errors.add("saveOrUpdateProduct.jsp.error.update.forbidden",  new ActionError("saveOrUpdateProduct.jsp.error.update.forbidden"));
			    }
			    else
			    {
			        //Mise à jour du produit
					ValueAddedTax valueAddedTax = new ValueAddedTax();
					valueAddedTax.setId(saveOrUpdateProductForm.getVatId());
					product.setVat(valueAddedTax);
			        
			        product.setCurrentLanguage(userSession.getCurrentLanguage());
					product.setCurrentLabel(saveOrUpdateProductForm.getLabel());
			        product.setColorRGB(saveOrUpdateProductForm.getColorRGB());
			        product.setPrice(saveOrUpdateProductForm.getPrice());
			        MenusManagerFactory.getManager().saveOrUpdateProduct(product, categoriesRelationCheckedList, saveOrUpdateProductForm.getVatId(), "UPDATE");
			    }
			}
			else if(saveOrUpdateProductForm.getActionDo().toUpperCase().equals("DELETE"))
			{
			    if(product!=null)
			    {
			        try
			        {
			            MenusManagerFactory.getManager().deleteProduct(product);
			        }
			        catch(DeleteProductException dpe)
			        {
			            //Le produit est rattaché à d'autres catégories
					    errors.add(dpe.getMessage(),  new ActionError(dpe.getMessage()));
					    saveErrors(request, errors);
						return (mapping.findForward("error"));
			        }
			    }
			    else
			    {
				    //Le produit n'existe plus
				    errors.add("saveOrUpdateProduct.jsp.error.delete.forbidden",  new ActionError("saveOrUpdateProduct.jsp.error.delete.forbidden"));
				    saveErrors(request, errors);
					return (mapping.findForward("error"));
			    }
			        
			}
			else
			{
			    //Autres actions impossibles saveOrUpdateProductForm.getActionDo()
			    errors.add("error.params.forbidden",  new ActionError("error.params.forbidden"));
			    saveErrors(request, errors);
				return (mapping.findForward("error"));
			}
		    
		}
		
		if(!errors.isEmpty())
		{
		    //Renvoyer vers la page de création ou bien de mise à jour du produit
		    saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}
		
		String requestedPage = "success";
		if(request.getParameter("searchedField")!=null && !request.getParameter("searchedField").toUpperCase().equals("NONE"))
		    requestedPage = "successSearched";
		// Forward control to the specified success URI
		return (mapping.findForward(requestedPage));
	}
	
    private List processRequiredAttribute(HttpServletRequest request, String code)
    {
		Product product = new Product();
		List categoriesList = MenusManagerFactory.getManager().getCategories();
		List categoriesRelationListTemp = new ArrayList();
		if(code!=null)
		{    
		    product = MenusManagerFactory.getManager().getProduct(code);
			categoriesRelationListTemp = MenusManagerFactory.getManager().getCategoriesRelation(code);
		}

		List categoriesRelationListForProduct = new ArrayList(categoriesList.size());
		CategoryRelation categoryRelation = null;
		Category category = null;
		for(int i=0; i<categoriesList.size(); i++)
		{
		    category = (Category)categoriesList.get(i);
		    int j=0;
		    if(code==null)
		        j=categoriesRelationListTemp.size();
		    else
		    {
				for(; j<categoriesRelationListTemp.size(); j++)
				{
				    categoryRelation = (CategoryRelation)categoriesRelationListTemp.get(j);
	
				    if(category.getId().longValue()==categoryRelation.getCategory().getId().longValue())
				        break;
				    
				}
		    }	
			if(j==categoriesRelationListTemp.size())
			{
			    categoryRelation = new CategoryRelation();
			    categoryRelation.setCategory(category);
			    categoriesRelationListForProduct.add(i, categoryRelation);
			}
			else
			    categoriesRelationListForProduct.add(i, categoryRelation);
		}
		
		request.setAttribute("product", product);
		request.setAttribute("categoriesRelationListForProduct", categoriesRelationListForProduct);
		
		return categoriesList;
    }

}
