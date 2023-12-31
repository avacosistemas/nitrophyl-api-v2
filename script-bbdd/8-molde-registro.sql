CREATE TABLE molderegistro
(
    ID_MOLDE_REGISTRO bigint NOT NULL,
    FECHA date NOT NULL,
    ID_MOLDE integer NOT NULL,
    TIPOREGISTRO character varying(50) COLLATE pg_catalog."default" NOT NULL,
    COMENTARIOS text COLLATE pg_catalog."default",
    CONSTRAINT ID_MOLDE_REGISTRO_PK PRIMARY KEY (ID_MOLDE_REGISTRO),
    CONSTRAINT "MOLDE_REGISTRO_MOLDE_FK" FOREIGN KEY (ID_MOLDE)
        REFERENCES public.moldes (id_molde) MATCH SIMPLE
        
);
	
CREATE SEQUENCE public.molderegistro_seq
    INCREMENT 1
    START 1000
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;