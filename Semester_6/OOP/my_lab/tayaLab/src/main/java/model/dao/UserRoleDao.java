package model.dao;

import model.entity.Role;
import model.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends Dao<UserRole> {
    List<Role> findRolesByUser(Long userId);
}
