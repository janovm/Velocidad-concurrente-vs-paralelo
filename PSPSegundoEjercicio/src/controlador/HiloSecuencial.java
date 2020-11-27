package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiloSecuencial extends Thread {
	
	int numeroEntradas=0;
	

    public int getNumeroEntradas() {
		return numeroEntradas;
	}


	public void run() {

        String url = "jdbc:mysql://localhost:3306/bbdd_psp_1";
        String usuario = "DAM2020_PSP";
        String password = "DAM2020_PSP";
        long tiempo = System.currentTimeMillis();
        Ejecutable ejecutable= new Ejecutable();
        int suma=0;
        

        try {
            Connection connection = DriverManager.getConnection(url, usuario, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM empleados";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	
                suma+= resultSet.getInt("INGRESOS");
                numeroEntradas++;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
        	
        }
        System.out.println("La suma es: "+ suma);
    	System.out.println("Ha tardado: "+ (System.currentTimeMillis()-tiempo));
    }

}
