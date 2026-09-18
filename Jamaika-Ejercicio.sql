drop database if exists jamaikaejercicio;
create database jamaikaejercicio;
use jamaikaejercicio;


/* Tabla Transacciones
 * End-Points
 * Insert
 * Select
 */

create table productos(
	id_producto int primary key auto_increment,
	stock int not null,
	activo bool not null default true,
	constraint chk_positive_productos check(stock >= 0)
);

create table transacciones(
	id_transaccion int primary key auto_increment,
	id_producto int not null,
	cantidad int not null,
	fecha datetime default now() not null,
	constraint fk_transacciones_productos foreign key (id_producto) references productos(id_producto),
	constraint chk_positive_transacciones check(cantidad >= 0)
);

delimiter //

create trigger trg_productos_after_insert
after insert on productos
for each row
begin
    insert into transacciones (id_producto, cantidad, fecha)
    values (new.id_producto, new.stock, now());
end//

create trigger trg_productos_after_update
after update on productos
for each row
begin
    if old.stock <> new.stock then
        insert into transacciones (id_producto, cantidad, fecha)
        values (new.id_producto, new.stock, now());
    end if;
end//

delimiter ;

/* Roles/Seguridad
 */

create role if not exists 'logi_admin';
create role if not exists 'logi_backend';
create role if not exists 'logi_auditor';
create role if not exists 'logi_reportes';


/* Privilegios */

grant all privileges on Logitrack.* to 'logi_admin';

grant select, insert, update, delete on Logitrack.usuarios to 'logi_backend';
grant select, insert, update, delete on Logitrack.roles to 'logi_backend';
grant select, insert, update, delete on Logitrack.categorias to 'logi_backend';
grant select, insert, update, delete on Logitrack.catalogos to 'logi_backend';
grant select, insert, update, delete on Logitrack.bodegas to 'logi_backend';
grant select, insert, update, delete on Logitrack.productos to 'logi_backend';

grant select on Logitrack.audit_usuarios to 'logi_backend';
grant select on Logitrack.audit_general to 'logi_backend';
grant select, insert on Logitrack.audit_transacciones to 'logi_backend';

grant select on Logitrack.audit_usuarios to 'logi_auditor';
grant select on Logitrack.audit_general to 'logi_auditor';
grant select on Logitrack.audit_transacciones to 'logi_auditor';

grant select on Logitrack.catalogos to 'logi_reportes';
grant select on Logitrack.categorias to 'logi_reportes';
grant select on Logitrack.bodegas to 'logi_reportes';
grant select on Logitrack.productos to 'logi_reportes';

/* Creacion y activacion de usuarios*/

create user if not exists 'usr_dba'@'%' identified by 'AdminPass123!';
create user if not exists 'usr_api_service'@'%' identified by 'BackendPass123!';
create user if not exists 'usr_auditor_qa'@'%' identified by 'AuditPass123!';

grant 'logi_admin' to 'usr_dba'@'%';
grant 'logi_backend' to 'usr_api_service'@'%';
grant 'logi_auditor' to 'usr_auditor_qa'@'%';

set default role 'logi_admin' to 'usr_dba'@'%';
set default role 'logi_backend' to 'usr_api_service'@'%';
set default role 'logi_auditor' to 'usr_auditor_qa'@'%';