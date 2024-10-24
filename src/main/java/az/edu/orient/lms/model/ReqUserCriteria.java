package az.edu.orient.lms.model;

import az.edu.orient.lms.constant.ActiveStatus;
import az.edu.orient.lms.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqUserCriteria {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private Gender gender;
    private Date birthDate;
    private String address;
    private List<ReqEducationDetail> educationDetailsCriteria;
    private List<ReqCareerDetail> careerDetailsCriteria;
    private List<ActiveStatus> activeStatuses;
    private Integer offset;
    private Integer pageSize;
    private List<String> sortFields;
    private List<String> sortDirections;
}
