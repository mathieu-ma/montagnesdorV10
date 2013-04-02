package fr.montagnesdor.restaurant.services.orders;

import java.util.List;


import fr.montagnesdor.restaurant.model.hibernate.Cashing;
import fr.montagnesdor.restaurant.model.hibernate.DinnerTable;
import fr.montagnesdor.restaurant.model.hibernate.OrderLine;
import fr.montagnesdor.restaurant.model.hibernate.ProductLanguage;

public interface OrdersManager
{
    public boolean isVatByTakeaway();
    public void setVatByTakeaway(boolean vatByTakeaway);
	public ProductLanguage getProduct(String productCode, String locale);
	public ProductLanguage getProduct(String productCode);
	public List getDinnerTablesExcludeCashedTable(int filterList, String sortListBy, String sortMonotony);
	public DinnerTable getDinnerTable(String tableNumber);
	public DinnerTable getDinnerTableById(Long id);	
	public DinnerTable mergeTable(DinnerTable currentTable, DinnerTable tableToMerge);
	public void saveOrUpdateDinnerTable(DinnerTable dinnerTable);
	public OrderLine saveOrUpdateOrderLine(DinnerTable dinnerTable, String orderLineId, String quantity, String code, String label, String price, String amount);
	public void deleteOrderLine(DinnerTable dinnerTable, String orderLineId);
	public void deleteDinnerTable(DinnerTable dinnerTable);
	public void deleteDinnerTableById(Long id);
	
	public List getDayRevenuesByTableId(Long id);
	public void saveOrUpdateCashing(Cashing cashing);
	
	public void saveOrUpdateVatTable(DinnerTable dinnerTable);
	

}

