package az.edu.orient.lms.model;

import az.edu.orient.lms.constant.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespUserBrief {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private ActiveStatus activeStatus;
}
