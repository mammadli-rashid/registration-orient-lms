package az.edu.orient.lms.mapper;

import az.edu.orient.lms.constant.ActiveStatus;
import az.edu.orient.lms.entity.Person;
import az.edu.orient.lms.entity.User;
import az.edu.orient.lms.model.RespPerson;
import az.edu.orient.lms.model.RespUserBrief;
import az.edu.orient.lms.model.RespUserDetailed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "person", target = "personalInfo", qualifiedByName = "convertPerson")
    @Mapping(source = "activeStatus", target = "activeStatus", qualifiedByName = "defineActiveStatus")
    RespUserDetailed toRespUserDetailed(User user);

    @Mapping(source = "person.firstName", target = "firstName")
    @Mapping(source = "person.lastName", target = "lastName")
    @Mapping(source = "person.email", target = "email")
    @Mapping(source = "person.mobile", target = "mobile")
    @Mapping(source = "activeStatus", target = "activeStatus", qualifiedByName = "defineActiveStatus")
    RespUserBrief toRespUserBrief(User user);

    @Named("defineActiveStatus")
    default ActiveStatus defineActiveStatus(int value) {
        switch (value) {
            case 1:
                return ActiveStatus.ACTIVE;
            case 2:
                return ActiveStatus.BLOCKED;
            case 3:
                return ActiveStatus.DELETED;
            default:
                return null;
        }
    }

    @Named("convertPerson")
    default RespPerson convertPerson(Person person) {
        if (person == null) {
            return null;
        }
        return PersonMapper.INSTANCE.toRespPerson(person);
    }
}