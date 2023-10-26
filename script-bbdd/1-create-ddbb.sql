/***** CREATE TABLE *****/

create table CONTACT_US (ID_CONTACT int8 not null, date timestamp not null, email varchar(255) not null, message varchar(255) not null, name varchar(255) not null, telephone varchar(255), primary key (ID_CONTACT));
create table FAQ (ID_FAQ int8 not null, answer TEXT not null, category varchar(255), favourite boolean, lang varchar(255), order_faq int4, question TEXT not null, subcategory varchar(255), primary key (ID_FAQ));
create table HISTORICO_PASSWORDS (Usuario_ID_SEG_USUARIO int8 not null, historicoPasswords varchar(255));
create table I18N (ID_I18N int8 not null, description varchar(255), lang varchar(255), primary key (ID_I18N));
create table PARAMETER (ID_PARAMETER int4 not null, description varchar(255), key varchar(255), value varchar(255), primary key (ID_PARAMETER));
create table PARAMETRO_GENERAL (ID_PARAMETRO_GENERAL int8 not null, codigo varchar(255), primary key (ID_PARAMETRO_GENERAL));
create table SEG_ACCESO (ID_SEG_ACCESO int8 not null, perfil_ID_SEG_PERFIL int8 not null, usuario_ID_SEG_USUARIO int8, primary key (ID_SEG_ACCESO));
create table SEG_PERFIL (ID_SEG_PERFIL int8 not null, activo boolean, nombre varchar(50), rol_ID_SEG_ROL int8, primary key (ID_SEG_PERFIL));
create table SEG_PERFIL_PERMISO (ID_SEG_PERFIL int8 not null, ID_SEG_PERMISO int8 not null);
create table SEG_PERMISO (ID_SEG_PERMISO int8 not null, activo boolean, codigo varchar(50), descripcion varchar(100), primary key (ID_SEG_PERMISO));
create table SEG_ROL (ID_SEG_ROL int8 not null, CODIGO varchar(10) not null, NOMBRE varchar(50) not null, SUPER_ROL boolean not null, primary key (ID_SEG_ROL));
create table SEG_USR_IMPER (ID_SEG_USUARIO int8 not null, ID_SEG_USUARIO_IMPER int8 not null, primary key (ID_SEG_USUARIO, ID_SEG_USUARIO_IMPER));
create table SEG_USUARIO (ID_SEG_USUARIO int8 not null, apellido varchar(100), bloqueado boolean, email varchar(100) not null, FECHA_ALTA_PASSWORD timestamp, INTENTOS_FALLIDOS_LOGIN int4, interno boolean, nombre varchar(100), password varchar(255), REQUIERE_CAMBIO_PASSWORD boolean, NOMBRE_USUARIO varchar(50), primary key (ID_SEG_USUARIO));
create table Usuario_mailsNotificacion (Usuario_ID_SEG_USUARIO int8 not null, mailsNotificacion varchar(255));
create table WORD (ID_WORD int8 not null, key varchar(255) not null, value varchar(255) not null, i18n_ID_I18N int8 not null, primary key (ID_WORD));

--nitrophyl
create table CLI_ACCESOS_CLIENTE (ID_ACCESO int8 not null, codigo varchar(255), descripcion varchar(255), primary key (ID_ACCESO));
create table CLI_ACCESO_PERMISO (ID_ACCESO int8 not null, ID_PERMISO int8 not null, primary key (ID_ACCESO, ID_PERMISO));
create table CLI_CLIENTE (ID_CLIENTE int8 not null, APELLIDO varchar(255) not null, TIPO_CLIENTE varchar(255) not null, BLOQUEADO boolean, EMAIL varchar(255) not null, FECHA_ALTA_PASSWORD timestamp, FECHA_NAC date, FECHA_REGISTRO timestamp not null, GENERO varchar(255), INTENTOS_FALLIDOS_LOGIN int4, NACIONALIDAD varchar(255), NOMBRE varchar(255) not null, PASSWORD varchar(255) not null, REQUIERE_CAMBIO_PASSWORD boolean, USERNAME varchar(255) not null, ID_ACCESOS int8, primary key (ID_CLIENTE));
create table CLI_CONTACTO (ID_CONTACTO int8 not null, BARRIO varchar(255), CODIGO_POSTAL varchar(255), DOMICILIO varchar(255), LOCALIDAD varchar(255), NOMBRE_CONTACTO varchar(255), PROVINCIA int4, TEL_FIJO varchar(255), TEL_CELULAR varchar(255) not null, primary key (ID_CONTACTO));
create table CLI_IDENTIFICACION (ID_IDENTIFICACION int8 not null, NUMERO_ID varchar(255) not null, TIPO_ID varchar(255) not null, primary key (ID_IDENTIFICACION));
create table CLI_PERMISO_CLIENTE (ID_PERMISO int8 not null, codigo varchar(255), descripcion varchar(255), primary key (ID_PERMISO));


/***** ALTER TABLE  *****/

alter table HISTORICO_PASSWORDS add constraint FK_8xv80lqjcipb4x0nf6wlcy6bt foreign key (Usuario_ID_SEG_USUARIO) references SEG_USUARIO;
alter table SEG_ACCESO add constraint PERFIL_ID_FK foreign key (perfil_ID_SEG_PERFIL) references SEG_PERFIL;
alter table SEG_ACCESO add constraint USUARIO_FK foreign key (usuario_ID_SEG_USUARIO) references SEG_USUARIO;
alter table SEG_PERFIL add constraint SEG_ROL_ID_FK foreign key (rol_ID_SEG_ROL) references SEG_ROL;
alter table SEG_PERFIL add constraint UK_oavyvwgy3ixr7iqlt22hbf7lb  unique (nombre);
alter table SEG_PERFIL_PERMISO add constraint FK_eew8qgnyusg4lldgvuqqqh8og foreign key (ID_SEG_PERFIL) references SEG_PERFIL;
alter table SEG_PERFIL_PERMISO add constraint FK_efq0od05kng8q5ffqaqg6loex foreign key (ID_SEG_PERMISO) references SEG_PERMISO;
alter table SEG_PERMISO add constraint UK_7fi1a6pv7ihhwuk0citxqni09  unique (codigo);
alter table SEG_ROL add constraint UK_hkx42vqw9iyfk1s8ypph5fvwy  unique (CODIGO);
alter table SEG_ROL add constraint UK_oslteevq72x5wnuh9scs9wpbm  unique (NOMBRE);
alter table SEG_USR_IMPER add constraint FK_hoiyu5ftib0ktu93hy3omngmx foreign key (ID_SEG_USUARIO) references SEG_USUARIO;
alter table SEG_USR_IMPER add constraint FK_j7jb24b5sfr9hru75c1iav95y foreign key (ID_SEG_USUARIO_IMPER) references SEG_USUARIO;
alter table SEG_USUARIO add constraint UK_dfyg8t69f0s12bmfqvte93a4t  unique (NOMBRE_USUARIO);
alter table Usuario_mailsNotificacion add constraint FK_ng09bhg580o5wjjkoe806wcmn foreign key (Usuario_ID_SEG_USUARIO) references SEG_USUARIO;
alter table WORD add constraint I18N_ID_FK foreign key (i18n_ID_I18N) references I18N;

--nitrophyl
alter table CLI_CLIENTE add constraint UK_dpfngbuw5kyun9v6nkw9qwxw6  unique (EMAIL);
alter table CLI_CLIENTE add constraint UK_lo3g6pgctqny711lr8wp7f4hr  unique (USERNAME);
alter table CLI_ACCESO_PERMISO add constraint FK_30ns7l5f4j4vp8q0snjitmpju foreign key (ID_PERMISO) references CLI_PERMISO_CLIENTE;
alter table CLI_ACCESO_PERMISO add constraint FK_38ai98wwmvjbajehx5npvc138 foreign key (ID_ACCESO) references CLI_ACCESOS_CLIENTE;
alter table CLI_CLIENTE add constraint FK_qsndlpgcb9rkjbv2y6r1wh8cu foreign key (ID_ACCESOS) references CLI_ACCESOS_CLIENTE;

/***** CREATE SEQUENCE  *****/

create sequence CONTACT_US_SEQ START WITH 1000;
create sequence FAQ_SEQ START WITH 1000;
create sequence I18N_SEQ START WITH 1000;
create sequence PARAMETER_SEQ START WITH 1000;
create sequence PARAMETRO_GENERAL_SEQ START WITH 1000;
create sequence SEG_ACCESO_SEQ START WITH 1000;
create sequence SEG_PERFIL_SEQ START WITH 1000;
create sequence SEG_PERMISO_SEQ START WITH 1000;
create sequence SEG_ROL_SEQ START WITH 1000;
create sequence SEG_USUARIO_SEQ START WITH 1000;
create sequence WORD_SEQ START WITH 1000;

--nitrophyl
create sequence CLIENTE_SEQ START WITH 1000;
