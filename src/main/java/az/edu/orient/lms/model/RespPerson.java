package az.edu.orient.lms.model;

import az.edu.orient.lms.constant.Gender;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespPerson {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String email;
    private String mobile;
    private String pin;
    private String address;
    private JsonNode educationData;
    private JsonNode careerData;
}
