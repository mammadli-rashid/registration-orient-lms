package az.edu.orient.lms.mapper;

import az.edu.orient.lms.constant.Gender;
import az.edu.orient.lms.entity.Person;
import az.edu.orient.lms.model.RespPerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "gender", source = "gender", qualifiedByName = "toGenderEnum")
    RespPerson toRespPerson(Person person);

    @Named("toGenderEnum")
    default Gender toGenderEnum(Character genderSymbol) {
        if (genderSymbol == null) {
            return Gender.UNDEFINED; // Handle null case
        }
        switch (genderSymbol) {
            case 'M':
                return Gender.MALE;
            case 'F':
                return Gender.FEMALE;
            default:
                return Gender.UNDEFINED; // Handle unexpected cases
        }
    }
}
