CREATE TABLE public.ensayo
(
    id_ensayo bigint NOT NULL,
    id_lote bigint NOT NULL,
    fecha date NOT NULL,
    observaciones text,
    id_config_prueba bigint NOT NULL,
    CONSTRAINT pk_ensayo PRIMARY KEY (id_ensayo),
    CONSTRAINT fk_ensayo_lote FOREIGN KEY (id_lote)
        REFERENCES public.lote (id_lote) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_ensayo_conf_prueba FOREIGN KEY (id_config_prueba)
        REFERENCES public.conf_prueba (id_conf_prueba) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE SEQUENCE public.ensayo_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999999;