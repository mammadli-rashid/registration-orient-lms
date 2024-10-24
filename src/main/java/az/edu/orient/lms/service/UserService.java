package az.edu.orient.lms.service;

import az.edu.orient.lms.constant.ActiveStatus;
import az.edu.orient.lms.entity.User;
import az.edu.orient.lms.exception.block.UserBlockedException;
import az.edu.orient.lms.exception.notfound.UserNotFoundException;
import az.edu.orient.lms.mapper.UserMapper;
import az.edu.orient.lms.model.*;
import az.edu.orient.lms.repository.UserRepository;
import az.edu.orient.lms.specification.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.checkUserIsBlockedByUsername(username)) {
            throw new UserBlockedException(username);
        }
        User user = userRepository.findUserByUsernameAndActiveStatus(username, ActiveStatus.ACTIVE.getValue())
                .orElseThrow(() -> new UserNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public List<RespUserBrief> getAllUsers(ReqUserCriteria criteria) {
        Specification<User> spec = Specification.where(null);

        // Add specifications based on criteria
        if (criteria.getUsername() != null) {
            spec = spec.and(UserSpecifications.searchByUsername(criteria.getUsername()));
        }
        if (criteria.getFirstName() != null) {
            spec = spec.and(UserSpecifications.searchByPersonFirstName(criteria.getFirstName()));
        }
        if (criteria.getLastName() != null) {
            spec = spec.and(UserSpecifications.searchByPersonLastName(criteria.getLastName()));
        }
        if (criteria.getEmail() != null) {
            spec = spec.and(UserSpecifications.searchByPersonEmail(criteria.getEmail()));
        }
        if (criteria.getMobile() != null) {
            spec = spec.and(UserSpecifications.searchByMobile(criteria.getMobile()));
        }
        if (criteria.getGender() != null) {
            spec = spec.and(UserSpecifications.searchByGender(criteria.getGender().getSymbol()));
        }
        if (criteria.getBirthDate() != null) {
            spec = spec.and(UserSpecifications.searchByBirthDate(criteria.getBirthDate()));
        }
        if (criteria.getAddress() != null) {
            spec = spec.and(UserSpecifications.searchByAddress(criteria.getAddress()));
        }
        if (criteria.getEducationDetailsCriteria() != null && !criteria.getEducationDetailsCriteria().isEmpty()) {
            for (ReqEducationDetail educationDetail : criteria.getEducationDetailsCriteria()) {
                spec = spec.and(UserSpecifications.searchByEducationCriteria(educationDetail));
            }
        }
        if (criteria.getCareerDetailsCriteria() != null && !criteria.getCareerDetailsCriteria().isEmpty()) {
            for (ReqCareerDetail careerDetail : criteria.getCareerDetailsCriteria()) {
                spec = spec.and(UserSpecifications.searchByCareerCriteria(careerDetail));
            }
        }
        if (criteria.getActiveStatuses() != null && !criteria.getActiveStatuses().isEmpty()) {
            for (ActiveStatus activeStatus : criteria.getActiveStatuses()) {
                spec = spec.and(UserSpecifications.searchByActiveStatus(activeStatus.getValue()));
            }
        }

        // Apply pagination and sorting
        // Construct Sort object based on criteria
        List<Sort.Order> sortOrders = IntStream.range(0, criteria.getSortFields().size())
                .mapToObj(i -> new Sort.Order(Sort.Direction.fromString(criteria.getSortDirections().get(i)), criteria.getSortFields().get(i)))
                .collect(Collectors.toList());

        // Create PageRequest
        PageRequest pageRequest = PageRequest.of(criteria.getOffset(), criteria.getPageSize(), Sort.by(sortOrders));

        // Fetching users
        return userRepository.findAll(spec, pageRequest).getContent().stream()
                .map(UserMapper.INSTANCE::toRespUserBrief)
                .collect(Collectors.toList());
    }

    public RespUserDetailed getUserByIdDetailed(long id){
        return UserMapper.INSTANCE.toRespUserDetailed
                (userRepository.findByIdAndActiveStatus(id, ActiveStatus.ACTIVE.getValue())
                    .orElseThrow(() -> new UserNotFoundException(id)));
    }

}