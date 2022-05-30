package com.example.ChartTest;


import com.example.ChartTest.Controller.UserResource;
import com.example.ChartTest.DTO.FormUserRole;
import com.example.ChartTest.Entity.*;
import com.example.ChartTest.Service.UserService;
import com.example.ChartTest.Service.initImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

@SpringBootApplication
public class ChartTestApplication implements CommandLineRunner {

	@Autowired
	public RepositoryRestConfiguration restConfiguration ;
	@Autowired
	private UserResource userService;
@Autowired
public initImpl initImpl;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(ChartTestApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {

/*
		userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
		userService.saveRole(new Role(null,"ROLE_ADMIN"));
		userService.saveRole(new Role(null,"ROLE_USER"));
		userService.saveUser(new AppUser("superadmin","12345", "superAdmin@superAdmin.com"  ));
		userService.saveUser(new AppUser("admin","12345", "admin@admin.com" ));
		userService.saveUser(new AppUser("user","12345","user@user.com"));

		userService.addRoleToUser(new FormUserRole("superadmin","ROLE_SUPER_ADMIN"));

		userService.addRoleToUser(new FormUserRole("admin","ROLE_ADMIN"));
		userService.addRoleToUser(new FormUserRole("user","ROLE_USER"));
*/


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
