package controlador;

import java.sql.*;
import java.util.ArrayList;

public class HiloConcurrente extends Thread {

    private int maximo;
    private int inicio;
    int suma = 0;
    
   
    public int getSuma() {
		return suma;
	}


	public HiloConcurrente(int inicio, int maximo) {
        this.inicio = inicio;
        this.maximo = maximo;
    }

    
    public void run() {
    	
        String url = "jdbc:mysql://localhost:3306/bbdd_psp_1";
        String usuario = "DAM2020_PSP";
        String password = "DAM2020_PSP";
        
        long tiempo = System.currentTimeMillis();

        try {

            Connection connection = DriverManager.getConnection(url, usuario, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM empleados WHERE ID >= " + inicio + " AND ID<" + maximo;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                suma += resultSet.getInt("INGRESOS");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
    }


}