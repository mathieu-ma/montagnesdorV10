package fr.montagnesdor.restaurant.services.receipts;


import java.util.Date;
import java.util.List;

import fr.montagnesdor.restaurant.model.hibernate.Cashing;

public interface ReceiptsManager
{
    public List getCashingTables(Date date, String sortListBy, String sortMonotony);
    public List getCashingDates();
    public Cashing getCashing(Long id);
    public void deleteCashing(Long id);
    public List getVatDaylyRevenues(Date cashingDate, Boolean isTakeaway);
    public List getVatMonthlyRevenues(Date revenueDate, Boolean isTakeaway);
    public void insertInitDayRevenue(Date cashingDate);    
    public void saveOrUpdateDayRevenue(Date revenueDate);
    public boolean isCashingPrinted(Date cashingDate);
    public boolean isCashingClosed(Date cashingDate);
    public List getVats();
    public Date getMaxRevenueDate();
    public List getDayRevenuesYearList();
    public List getDayRevenuesList(Date revenueDate, Boolean isTakeaway, String sortListBy, String sortMonotony);
    public void saveOrUpdateCashing(Cashing cashing);
}

