package com.example.ChartTest.Controller;

import com.example.ChartTest.DAO.AppUserRepository;
import com.example.ChartTest.DTO.FormUserRole;
import com.example.ChartTest.Entity.AppUser;
import com.example.ChartTest.Entity.Role;
import com.example.ChartTest.Service.AccountServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserResource {
    @Autowired
    private AccountServiceImpl accountService;
@Autowired
private AppUserRepository appUserRepository;

@GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return  ResponseEntity.ok().body(accountService.getUsers()) ;
    }
    @CrossOrigin("*")
    @GetMapping("/userById/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable(name ="id") Long id){
        AppUser appUser =appUserRepository.findById(id).get();
        return  ResponseEntity.ok(appUser)  ;
    }

@PostMapping("/saveUser")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
    return ResponseEntity.ok().body(accountService.saveUser(user));
    }

    @PostMapping("/saveRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok().body(accountService.saveRole(role));
    }
    @CrossOrigin("*")
    @PutMapping("/UpdateUser/{id}")
    public ResponseEntity<AppUser> UpdateUser(@PathVariable(name ="id") Long id,@RequestBody AppUser user){
    AppUser appUser =appUserRepository.findById(id).get();
    appUser.setFirstName(user.getFirstName());
    appUser.setLastName(user.getLastName());
    appUser.setRoles(user.getRoles());
    appUser.setEmail(user.getEmail());
    appUser.setPassword(user.getPassword());
        return ResponseEntity.ok(appUser)  ;
    }

    @PostMapping("/addRole")
    public ResponseEntity<Role> addRoleToUser(@RequestBody FormUserRole formUserRole ){
   String username= formUserRole.getUsername();
   String roleName=formUserRole.getRoleName();
   accountService.addRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }





}
