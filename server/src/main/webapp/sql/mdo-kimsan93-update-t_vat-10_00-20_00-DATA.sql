insert into t_value_added_tax (vat_value) values('10.00');
insert into t_value_added_tax (vat_value) values('20.00');

update t_product set vat_id = (select vat_id from t_value_added_tax where vat_value='10.00') 
where vat_id in (select vat_id from t_value_added_tax where vat_value='7.00');

update t_product set vat_id = (select vat_id from t_value_added_tax where vat_value='20.00') 
where vat_id in (select vat_id from t_value_added_tax where vat_value='19.60');


