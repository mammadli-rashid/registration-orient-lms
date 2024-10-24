package az.edu.orient.lms.repository;

import az.edu.orient.lms.entity.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {
    List<RegistrationRequest> findAllByRequestStatus(int requestStatus);

    Optional<RegistrationRequest> findByRequestToken(String requestToken);
}
