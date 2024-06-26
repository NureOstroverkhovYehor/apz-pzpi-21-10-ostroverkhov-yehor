package plantlightcycle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import plantlightcycle.model.Role;
import plantlightcycle.model.UserEntity;
import plantlightcycle.service.UserService;

import java.util.List;
import java.util.Set;

/**
 * Controller managing user-related endpoints.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    /**
     * Service handling user-related operations.
     */
    private final UserService userService;

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing a list of UserEntity representing all users
     */
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves a user by their login.
     *
     * @param login User login ID
     * @return ResponseEntity containing a UserEntity with the given login
     */
    @GetMapping("/{login}")
    public ResponseEntity<UserEntity> getUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok(userService.getByLogin(login));
    }

    @GetMapping("/{login}/roles")
    public ResponseEntity<Set<Role>> getAllUserRoles(@PathVariable String login) {
        return ResponseEntity.ok(userService.getAllUserRoles(login));
    }

    /**
     * Adds a role to a specific user.
     *
     * @param login User login ID
     * @param role  Role to add to the user
     * @return ResponseEntity containing the updated UserEntity after adding the role, HTTP status CREATED
     */
    @PostMapping("/{login}/roles")
    public ResponseEntity<Set<Role>> addRoleToUser(@PathVariable String login, @RequestBody String role) {
        return new ResponseEntity<>(userService.addRoleToUser(login, role), HttpStatus.CREATED);
    }

    /**
     * Removes a role from a specific user.
     *
     * @param login User login ID
     * @param role  Role to remove from the user
     * @return ResponseEntity containing the updated UserEntity after removing the role
     */
    @DeleteMapping("/{login}/roles")
    public ResponseEntity<Set<Role>> removeRoleFromUser(@PathVariable String login, @RequestBody String role) {
        return ResponseEntity.ok(userService.removeRoleFromUser(login, role));
    }

    /**
     * Deletes a user by their login.
     *
     * @param login User login ID
     * @return ResponseEntity with no content and HTTP status NO_CONTENT
     * @throws AccessDeniedException if access to delete the user is denied
     */
    @DeleteMapping("/{login}")
    public ResponseEntity<List<UserEntity>> deleteUserByLogin(@PathVariable String login) throws AccessDeniedException {
        userService.deleteByLogin(login);

        return ResponseEntity.ok(userService.getAllUsers());
    }
}