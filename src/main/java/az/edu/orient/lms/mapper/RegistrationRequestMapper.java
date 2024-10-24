package az.edu.orient.lms.mapper;

import az.edu.orient.lms.constant.RegistrationRequestStatus;
import az.edu.orient.lms.entity.Person;
import az.edu.orient.lms.entity.RegistrationRequest;
import az.edu.orient.lms.model.ReqRegister;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegistrationRequestMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static RegistrationRequest toRegistrationRequest(ReqRegister reqRegister) {
        return RegistrationRequest.builder()
                .username(reqRegister.getUsername())
                .password(passwordEncoder.encode(reqRegister.getPassword()))
                .email(reqRegister.getEmail())
                .mobile(reqRegister.getMobile())
                .firstName(reqRegister.getFirstName())
                .lastName(reqRegister.getLastName())
                .birthDate(reqRegister.getBirthDate())
                .gender(reqRegister.getGender().getSymbol())
                .pin(reqRegister.getPin())
                .address(reqRegister.getAddress())
                .educationData(convertListToJson(reqRegister.getEducationDetails()))
                .careerData(convertListToJson(reqRegister.getCareerDetails()))
                .requestToken(generateRequestToken())
                .requestStatus(RegistrationRequestStatus.ACCEPTED.getValue())
                .build();
    }

    public static Person toPerson(RegistrationRequest registrationRequest) {
        return Person.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .mobile(registrationRequest.getMobile())
                .birthDate(registrationRequest.getBirthDate())
                .pin(registrationRequest.getPin())
                .educationData(registrationRequest.getEducationData())
                .careerData(registrationRequest.getCareerData())
                .gender(registrationRequest.getGender())
                .address(registrationRequest.getAddress())
                .build();
    }

    // Utility method to convert a list of objects to JSON
    private static JsonNode convertListToJson(List<?> list) {
        if (list == null || list.isEmpty()) {
            return objectMapper.createArrayNode(); // Return an empty JSON array for null or empty lists
        }
        return objectMapper.convertValue(list, JsonNode.class);
    }

    // Method for request token generation
    private static String generateRequestToken() {
        return java.util.UUID.randomUUID().toString();
    }
}