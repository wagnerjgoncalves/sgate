# SGate schema

# --- !Ups

CREATE TABLE "sgate"."venda" (
    "id" serial NOT NULL,
    "id_cliente" int4 NOT NULL REFERENCES "sgate"."cliente"("id"),
    "id_plano" int4 NOT NULL REFERENCES "sgate"."planos"("id"),
    "data" date NOT NULL,
    "valor" real NOT NULL,
    "desconto" real NOT NULL DEFAULT 0,
    "total" real NOT NULL,
    PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

# --- !Downs

DROP TABLE "sgate"."venda";
