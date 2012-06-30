# --- !Ups

CREATE TABLE "sgate"."tipos_plano" (
    "id" serial NOT NULL,
    "nome" text NOT NULL,
    PRIMARY KEY ("id")
)
WITH (OIDS=FALSE); 


CREATE TABLE "sgate"."planos" (
    "id" serial NOT NULL,
    "nome" text NOT NULL,
    "descricao" text NULL,
    "preco" NUMERIC(2) NOT NULL,
    "banda" text NULL,
    "id_tipo_plano" int4 NOT NULL REFERENCES "sgate"."tipos_plano"("id"),
    PRIMARY KEY ("id")
)
WITH (OIDS=FALSE); 

# --- !Downs
 
DROP TABLE "sgate"."planos";
DROP TABLE "sgate"."tipos_planos";
