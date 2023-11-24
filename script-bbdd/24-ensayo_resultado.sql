CREATE TABLE public.ensayo_resultado
(
    id_ensayo_resultado bigint NOT NULL,
    resultado double precision NOT NULL,
    redondeo double precision NOT NULL,
    id_ensayo bigint NOT NULL,
    id_config_prueba_param bigint NOT NULL,
    CONSTRAINT pk_ensayo_resultado PRIMARY KEY (id_ensayo_resultado),
    CONSTRAINT fk_ensayo_res_ensayo FOREIGN KEY (id_ensayo)
        REFERENCES public.ensayo (id_ensayo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_ensayo_conf_pru_param FOREIGN KEY (id_config_prueba_param)
        REFERENCES public.conf_prueba_param (id_conf_prueba_param) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE SEQUENCE public.ensayo_resultado_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

