package sn.isi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sn.isi.domaine.Role;
import sn.isi.service.RoleService;

@RestController
@RequestMapping("roles")
@AllArgsConstructor
public class RoleController {
	
	RoleService roleService;

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/{role_title}")
    public ResponseEntity<Role> getRole(@PathVariable("role_title") String role_title) {
        return ResponseEntity.ok(roleService.getRole(role_title));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{role_titler}")
    public Role updateRole(@PathVariable("role_title") String role_title, @Valid @RequestBody Role role) {
        return roleService.updateRole(role_title, role);
    }

    @DeleteMapping("/{role_title}")
    public void deleteRole(@PathVariable("role_title") String role_title) {
    //    roleService.deleteRole(role_title);
    }

}
