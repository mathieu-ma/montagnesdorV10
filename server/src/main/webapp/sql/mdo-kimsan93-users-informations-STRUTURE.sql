ALTER TABLE t_user ALTER COLUMN aut_id DROP NOT NULL;

DROP TABLE t_user_phone;
DROP TABLE t_user_email;
DROP TABLE t_user_address;


--uph_id : table idetntifier
--uph_number : Phone number
CREATE TABLE t_user_phone
(
  uph_id serial,
  usr_id int8 NOT NULL,
  uph_number varchar(20) NOT NULL,  
  CONSTRAINT uph_id_pk PRIMARY KEY (uph_id),
  CONSTRAINT uph_usr_id_fk FOREIGN KEY (usr_id) REFERENCES t_user (usr_id) ON UPDATE RESTRICT ON DELETE RESTRICT
) WITHOUT OIDS;

--uem_id : table idetntifier
--uem_mail : email
CREATE TABLE t_user_email
(
  uem_id serial,
  usr_id int8 NOT NULL,
  uem_mail varchar(255) NOT NULL,  
  CONSTRAINT uem_id_pk PRIMARY KEY (uem_id),
  CONSTRAINT uem_usr_id_fk FOREIGN KEY (usr_id) REFERENCES t_user (usr_id) ON UPDATE RESTRICT ON DELETE RESTRICT
) WITHOUT OIDS;

--uad_id : table idetntifier
--uad_staircase escalier,
--uad_stage étage,
--uad_building batiment,
CREATE TABLE t_user_address
(
  uad_id serial,
  usr_id int8 NOT NULL,
  uad_address1 varchar(255) NOT NULL,  
  uad_address2 varchar(255),  
  uad_address3 varchar(255),  
  uad_postcode varchar(16),
  uad_city varchar(255),  
  uad_state varchar(255),  
  uad_country varchar(255),
  uad_digital_code varchar(80),
  uad_staircase varchar(10),
  uad_stage varchar(10),
  uad_building varchar(10),
  CONSTRAINT uad_id_pk PRIMARY KEY (uad_id),
  CONSTRAINT uad_usr_id_fk FOREIGN KEY (usr_id) REFERENCES t_user (usr_id) ON UPDATE RESTRICT ON DELETE RESTRICT
) WITHOUT OIDS;

