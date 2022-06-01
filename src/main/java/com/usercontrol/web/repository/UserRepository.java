package com.usercontrol.web.repository;

import com.usercontrol.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
