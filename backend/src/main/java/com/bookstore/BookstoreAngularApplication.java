package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreAngularApplication {

	/*
	 * @Autowired private UserService userService;
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreAngularApplication.class, args);
	}
	
	/*
	 * @Override public void run(String...args) throws Exception { User user1 = new
	 * User(); user1.setFirstName("Hung"); user1.setLastName("Ta");
	 * user1.setUsername("hung");
	 * user1.setPassword(SecurityUtility.passwordEncoder().encode("hung"));
	 * user1.setEmail("hungict100@gmail.com"); Set<UserRole> userRoles = new
	 * HashSet<>(); Role role1 = new Role(); role1.setRoleId(1);
	 * role1.setName("ROLE_USER"); userRoles.add(new UserRole(user1, role1));
	 * 
	 * userService.createUser(user1, userRoles);
	 * 
	 * userRoles.clear();
	 * 
	 * User user2 = new User(); user2.setFirstName("Admin");
	 * user2.setLastName("Admin"); user2.setUsername("admin");
	 * user2.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
	 * user2.setEmail("admin@gmail.com"); Role role2 = new Role();
	 * role2.setRoleId(2); role2.setName("ROLE_ADMIN"); userRoles.add(new
	 * UserRole(user2, role2));
	 * 
	 * userService.createUser(user2, userRoles); }
	 */

}
