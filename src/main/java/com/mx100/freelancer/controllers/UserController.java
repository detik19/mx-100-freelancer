package com.mx100.freelancer.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mx100.freelancer.domains.UserRequest;
import com.mx100.freelancer.domains.Users;
import com.mx100.freelancer.services.UsersService;






@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UsersService usersService;


  @RequestMapping(method = GET, value = "/user/{userId}")
  public Users loadById(@PathVariable Long userId) {
    return this.usersService.findById(userId);
  }

  @RequestMapping(method = GET, value = "/user/all")
  public List<Users> loadAll() {
    return this.usersService.findAll();
  }

  @RequestMapping(method = GET, value = "/user/reset-credentials")
  public ResponseEntity<Map> resetCredentials() {
    this.usersService.resetCredentials();
    Map<String, String> result = new HashMap<>();
    result.put("result", "success");
    return ResponseEntity.accepted().body(result);
  }


  @RequestMapping(method = POST, value = "/signup")
  public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest,
      UriComponentsBuilder ucBuilder) {

    Users existUser = this.usersService.findByUsername(userRequest.getUsername());
    if (existUser != null) {
//      throw new ResourceConflictException(userRequest.getId(), "Username already exists");
    }
    Users users = this.usersService.save(userRequest);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(users.getId()).toUri());
    return new ResponseEntity<Users>(users, HttpStatus.CREATED);
  }

  /*
   * We are not using userService.findByUsername here(we could), so it is good that we are making
   * sure that the user has role "ROLE_USER" to access this endpoint.
   */
  @RequestMapping("/whoami")
  @PreAuthorize("hasRole('ROLE_EMPLOYER')")
  public Users user() {
    return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
