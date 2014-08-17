ALTER TABLE t_cashing ADD COLUMN csh_online numeric(10,2) NOT NULL DEFAULT 0.00;

ALTER TABLE t_day_revenue ADD COLUMN drv_online numeric(10,2) NOT NULL DEFAULT 0.00;
