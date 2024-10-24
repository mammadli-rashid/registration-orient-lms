package az.edu.orient.lms.model;

import az.edu.orient.lms.constant.EducationLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Data
@ApiModel(description = "Request model for education details")
public class ReqEducationDetail {

    @NotNull(message = "Education level is required.")
    @ApiModelProperty(value = "Level of education", example = "MASTERS_DEGREE", required = true)
    EducationLevel educationLevel;

    @NotBlank(message = "Institution name must not be blank.")
    @ApiModelProperty(value = "Name of the educational institution", example = "Baku State University")
    String institution;

    @ApiModelProperty(value = "Name of the faculty", example = "Applied Mathematics and Cybernetics")
    String faculty;

    @ApiModelProperty(value = "Specialization name", example = "Computer Science")
    String specialization;

    @NotNull(message = "Start date is required.")
    @PastOrPresent(message = "Start date must be in the past or present.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Start date of the education", example = "2010-09-01", required = true)
    Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "End date of the education", example = "2015-06-30")
    Date endDate;
}