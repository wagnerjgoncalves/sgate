# --- !Ups

ALTER TABLE "sgate"."planos" DROP "preco";
ALTER TABLE "sgate"."planos" ADD "preco" real not null default '0';

# --- !Downs

ALTER TABLE "sgate"."planos" ADD "preco" numeric(2) not null;
ALTER TABLE "sgate"."planos" DROP "preco";

