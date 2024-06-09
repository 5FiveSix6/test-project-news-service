package yuriy.spring.news_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yuriy.spring.news_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByFirstNameAndLastNameAndMiddleName(String firstName,
                                                      String lastName,
                                                      String middleName);
}
