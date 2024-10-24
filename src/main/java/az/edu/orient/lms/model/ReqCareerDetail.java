package az.edu.orient.lms.model;

import az.edu.orient.lms.validation.annotation.ValidStartEndDates;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
@Data
@ValidStartEndDates
@ApiModel(description = "Request model for career details")
public class ReqCareerDetail {

    @NotBlank(message = "Workplace name is mandatory")
    @ApiModelProperty(value = "Name of the workplace", example = "KapitalBank OJSC", required = true)
    private String workplace;

    @NotBlank(message = "Occupation name is mandatory")
    @ApiModelProperty(value = "Occupation name", example = "Senior Software Engineer", required = true)
    private String occupation;

    @NotNull(message = "Start date is required.")
    @PastOrPresent(message = "Start date must be in the past or present.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Start date of the career", example = "2015-09-01", required = true)
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "End date of the career", example = "2020-05-15")
    private Date endDate;
}
