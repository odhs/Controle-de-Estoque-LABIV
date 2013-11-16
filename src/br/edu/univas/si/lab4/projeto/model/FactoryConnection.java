package br.edu.univas.si.lab4.projeto.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class FactoryConnection {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/controledeestoque","postgres","abc123");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}