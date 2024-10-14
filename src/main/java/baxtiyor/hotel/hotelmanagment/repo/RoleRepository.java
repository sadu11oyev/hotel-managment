package baxtiyor.hotel.hotelmanagment.repo;

import baxtiyor.hotel.hotelmanagment.entity.Role;
import baxtiyor.hotel.hotelmanagment.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRoleName(RoleName roleName);
}