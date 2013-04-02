package fr.montagnesdor.restaurant.services.menus;

import java.util.List;

import fr.montagnesdor.restaurant.model.hibernate.Category;
import fr.montagnesdor.restaurant.model.hibernate.Product;
import fr.montagnesdor.restaurant.services.menus.spi.DeleteProductException;

public interface MenusManager
{
	public List getProducts(int pageSize, int pageNumber, String sortListBy, String sortMonotony, StringBuffer productsSum);
	public List getVats();
	public List searchProducts(String searchedField, String searchedValue, String sortMonotony, String language);
	public Product getProduct(String code);
	public List getProductsByCategory(String categoryId);	
	public List getCategoriesRelation(String code);	
	public List getCategories();
	public void saveOrUpdateProduct(Product product, List categoriesRelationCheckedList, Long vatId, String action);
	public void deleteProduct(Product product) throws DeleteProductException;
	public void saveOrUpdateCategory(Category category, String action);
	public void deleteCategory(Category category);
	public Category getCategory(Long id);
	public List searchStatsConsumptionProducts(String searchedField, String searchedValue, String sortMonotony, String language);
	public List getStatsConsumptionProducts(int pageSize, int pageNumber, String sortListBy, String sortMonotony, String[] statsConsumptionProductsSum);
	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony, String quantityHavingOperator, Boolean isExceptProduct);
	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony, String quantityHavingOperator, Boolean isExceptProduct, Long categoryId);
	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony, String quantityHavingOperator);
	public List getStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year, String sortMonotony);
	public List getStatsConsumptionProducts(Short day, Short month, Short year, String sortMonotony);
	public List getProductsNotConsumed(Short day, Short month, Short year);
	public List getStatsConsumptionProductsByCategory(String categoryId);
	public String[] getStatsConsumptionProductsMinYearMaxMonthMaxYear();
	public void purgeStatsConsumptionProducts(String code, Float quantity, Short day, Short month, Short year);
}

