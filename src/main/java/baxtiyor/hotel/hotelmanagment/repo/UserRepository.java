package baxtiyor.hotel.hotelmanagment.repo;

import baxtiyor.hotel.hotelmanagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String username);

    @Query(nativeQuery = true,value = "select * from users where email=:email ")
    User findByUsername(String email);

    @Query(nativeQuery = true, value = "select u.* from users u join users_roles ur on u.id = ur.user_id join roles r on ur.roles_id = r.id where role_name='ROLE_CUSTOMER'")
    List<User> findAllCustomers();
    @Query(nativeQuery = true, value = "select u.* from users u join users_roles ur on u.id = ur.user_id join roles r on ur.roles_id = r.id where role_name='ROLE_ADMIN'")
    List<User> findAllAdmins();
}