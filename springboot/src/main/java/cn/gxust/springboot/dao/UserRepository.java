package cn.gxust.springboot.dao;

import cn.gxust.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhone(String phone);

    User findByPhone(String phone);
}
