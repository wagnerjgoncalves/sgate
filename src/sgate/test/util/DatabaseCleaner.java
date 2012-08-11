package util;

import play.db.jpa.JPA;
import play.test.*;
import models.*;

public class DatabaseCleaner {

	public static void cleanUp() {

		Fixtures.delete(Venda.class,
					    Cliente.class,
						Endereco.class,
						Plano.class,
						TipoPlano.class);
	}
}
