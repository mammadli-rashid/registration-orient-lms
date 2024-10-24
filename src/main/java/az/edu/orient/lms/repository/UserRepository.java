package az.edu.orient.lms.repository;

import az.edu.orient.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByIdAndActiveStatus(Long id, int activeStatus);

    Optional<User> findUserByUsernameAndActiveStatus(String username, int activeStatus);

    @Query("SELECT COUNT(u) > 0 " +
            "FROM User u WHERE u.username = :username AND u.activeStatus = 2")
    boolean checkUserIsBlockedByUsername(@Param("username") String username);

    @Query("SELECT COUNT(u) = 0 " +
            "FROM User u WHERE u.username = :username AND u.activeStatus != 0")
    boolean checkUsernameIsAvailable(@Param("username") String username);

    @Query("SELECT COUNT(u) = 0 " +
            "FROM User u WHERE u.person.email = :email AND u.activeStatus != 0")
    boolean checkEmailIsAvailable(@Param("email") String email);

    @Query("SELECT COUNT(u) = 0 " +
            "FROM User u WHERE u.person.mobile = :mobile AND u.activeStatus != 0")
    boolean checkMobileIsAvailable(@Param("mobile") String mobile);

    @Query("SELECT COUNT(u) = 0 " +
            "FROM User u WHERE u.person.pin = :pin AND u.activeStatus != 0")
    boolean findAllNonDeletedPins(@Param("pin") String pin);
}
