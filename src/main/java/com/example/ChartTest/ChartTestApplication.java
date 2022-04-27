package com.example.ChartTest;


import com.example.ChartTest.Entity.*;
import com.example.ChartTest.Service.initImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.sql.*;

@SpringBootApplication
public class ChartTestApplication implements CommandLineRunner {

	@Autowired
	public RepositoryRestConfiguration restConfiguration ;
@Autowired
public initImpl initImpl;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(ChartTestApplication.class, args);

	/*	String url="jdbc:mysql://localhost:8889/MaBanque";
		String username="root";
				String password="root";
				String query="SELECT mail from client";

				Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection(url,username,password);
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()){
			String mail= rs.getString(1);
			System.out.println(mail);
		}
		st.close();
		con.close();
		*/

	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Dashboard.class);
		restConfiguration.exposeIdsFor(Chart.class);
		restConfiguration.exposeIdsFor(ChartDataset.class);
		restConfiguration.exposeIdsFor(DataPoint.class);
		restConfiguration.exposeIdsFor(Label.class);
		restConfiguration.exposeIdsFor(Config.class);
//initImpl.initDashboard();
//initImpl.initChart();
//initImpl.initLabel();


	}
}
