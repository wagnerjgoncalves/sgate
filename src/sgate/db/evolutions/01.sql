# Repositories schema
 
# --- !Ups
 
CREATE SCHEMA "sgate";

CREATE TABLE "sgate"."endereco" (
    "id" serial NOT NULL,
    "logradouro" text NOT NULL,
    "numero" int4 NOT NULL,
    "bairro" text NOT NULL,
    "complemento" text NULL,
    "cidade" text NOT NULL,
    "uf" text NOT NULL,
    "cep" text NOT NULL,
    PRIMARY KEY ("id")
)
WITH (OIDS=FALSE); 


CREATE TABLE "sgate"."cliente" (
    "id" serial NOT NULL,
    "nome" text NOT NULL,
    "cpf" text NOT NULL,
    "rg" text NOT NULL,
    "filiacao" text NOT NULL,
    "telcelular" text NULL,
    "telfixo" text NULL,
    "telreferencia" text NOT NULL,
    "email" text NULL,
    "id_endereco" int4 NOT NULL REFERENCES "sgate"."endereco"("id"),
    PRIMARY KEY ("id")
)
WITH (OIDS=FALSE); 

# --- !Downs
 
DROP TABLE "sgate"."cliente";
DROP TABLE "sgate"."endereco";
DROP SCHEMA "sgate";
