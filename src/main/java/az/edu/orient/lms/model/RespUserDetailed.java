package az.edu.orient.lms.model;

import az.edu.orient.lms.constant.ActiveStatus;
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
public class RespUserDetailed {
    private Long id;
    private String username;
    private RespPerson personalInfo;
    private ActiveStatus activeStatus;
}