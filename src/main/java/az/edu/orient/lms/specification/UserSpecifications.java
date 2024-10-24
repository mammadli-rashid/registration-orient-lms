package az.edu.orient.lms.specification;

import az.edu.orient.lms.entity.User;
import az.edu.orient.lms.model.ReqCareerDetail;
import az.edu.orient.lms.model.ReqEducationDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> searchByUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if (username == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("username"), "%" + username + "%");
        };
    }

    public static Specification<User> searchByActiveStatus(int activeStatus) {
        return (root, query, criteriaBuilder) -> {
            if (activeStatus < 0) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("activeStatus"), activeStatus);
        };
    }

    public static Specification<User> searchByPersonFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            if (firstName == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("person").get("firstName"), "%" + firstName + "%");
        };
    }

    public static Specification<User> searchByPersonLastName(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (lastName == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("person").get("lastName"), "%" + lastName + "%");
        };
    }

    public static Specification<User> searchByPersonEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("person").get("email"), "%" + email + "%");
        };
    }

    public static Specification<User> searchByMobile(String mobile) {
        return (root, query, criteriaBuilder) -> {
            if (mobile == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("person").get("mobile"), "%" + mobile + "%");
        };
    }

    public static Specification<User> searchByGender(Character gender) {
        return (root, query, criteriaBuilder) -> {
            if (gender == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("person").get("gender"), gender);
        };
    }

    public static Specification<User> searchByBirthDate(Date birthDate) {
        return (root, query, criteriaBuilder) -> {
            if (birthDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("person").get("birthDate"), birthDate);
        };
    }

    public static Specification<User> searchByAddress(String address) {
        return (root, query, criteriaBuilder) -> {
            if (address == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("person").get("address"), "%" + address + "%");
        };
    }

    public static Specification<User> searchByPersonGender(Character gender) {
        return (root, query, criteriaBuilder) -> {
            if (gender == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("person").get("gender"), gender);
        };
    }

    public static Specification<User> searchByPersonBirthDate(Date birthDate) {
        return (root, query, criteriaBuilder) -> {
            if (birthDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("person").get("birthDate"), birthDate);
        };
    }

    public static Specification<User> searchByEducationCriteria(ReqEducationDetail educationCriteria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (educationCriteria.getEducationLevel() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.isTrue(criteriaBuilder.function("jsonb_exists", Boolean.class,
                                root.get("person").get("educationData"),
                                criteriaBuilder.literal(educationCriteria.getEducationLevel().name()))));
            }

            if (educationCriteria.getInstitution() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.isTrue(criteriaBuilder.function("jsonb_exists", Boolean.class,
                                root.get("person").get("educationData"),
                                criteriaBuilder.literal(educationCriteria.getInstitution()))));
            }

            if (educationCriteria.getFaculty() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.isTrue(criteriaBuilder.function("jsonb_exists", Boolean.class,
                                root.get("person").get("educationData"),
                                criteriaBuilder.literal(educationCriteria.getFaculty()))));
            }

            if (educationCriteria.getSpecialization() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.isTrue(criteriaBuilder.function("jsonb_exists", Boolean.class,
                                root.get("person").get("educationData"),
                                criteriaBuilder.literal(educationCriteria.getSpecialization()))));
            }

            return predicate;
        };
    }

    public static Specification<User> searchByCareerCriteria(ReqCareerDetail careerCriteria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (careerCriteria.getWorkplace() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.isTrue(criteriaBuilder.function("jsonb_exists", Boolean.class,
                                root.get("person").get("careerData"),
                                criteriaBuilder.literal(careerCriteria.getWorkplace()))));
            }

            if (careerCriteria.getOccupation() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.isTrue(criteriaBuilder.function("jsonb_exists", Boolean.class,
                                root.get("person").get("careerData"),
                                criteriaBuilder.literal(careerCriteria.getOccupation()))));
            }

            if (careerCriteria.getStartDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThan(root.get("person").get("careerData").get("startDate"), careerCriteria.getStartDate()));
            }

            if (careerCriteria.getEndDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThan(root.get("person").get("careerData").get("endDate"), careerCriteria.getEndDate()));
            }

            return predicate;
        };
    }

    public static Specification<User> combineSpecifications(List<Specification<User>> specs) {
        Specification<User> combined = Specification.where(null);
        for (Specification<User> spec : specs) {
            combined = combined.and(spec);
        }
        return combined;
    }

}