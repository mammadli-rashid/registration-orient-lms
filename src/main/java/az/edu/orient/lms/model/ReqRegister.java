package az.edu.orient.lms.model;

import az.edu.orient.lms.constant.Gender;
import az.edu.orient.lms.validation.annotation.MobileNumber;
import az.edu.orient.lms.validation.annotation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Request model for user registration")
public class ReqRegister {

    @NotBlank(message = "Username is mandatory")
    @ApiModelProperty(value = "Username of the user", required = true, example = "rashid.mammadli")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @ApiModelProperty(value = "Password for the account (min 8 characters)", required = true, example = "R@shid123")
    @ValidPassword
    private String password;

    @NotBlank(message = "First name is mandatory")
    @ApiModelProperty(value = "First name of the user", required = true, example = "Rashid")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @ApiModelProperty(value = "Last name of the user", required = true, example = "Mammadli")
    private String lastName;

    @NotNull(message = "Birthdate is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Birthdate of the user (format: yyyy-MM-dd)", required = true, example = "2004-09-09")
    private Date birthDate;

    @NotNull(message = "Gender is mandatory")
    @ApiModelProperty(value = "Gender of the user", required = true, example = "MALE")
    private Gender gender;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    @ApiModelProperty(value = "Email address of the user", required = true, example = "rashid.mammadli@orient.edu.az")
    private String email;

    @MobileNumber
    @NotBlank(message = "Mobile number is mandatory")
    @ApiModelProperty(value = "Mobile number of the user", required = true, example = "+994558928687")
    private String mobile;

    @Valid
    @ApiModelProperty(value = "List of educational qualifications")
    private List<ReqEducationDetail> educationDetails;

    @Valid
    @ApiModelProperty(value = "List of career experiences")
    private List<ReqCareerDetail> careerDetails;

    @NotBlank(message = "PIN is mandatory")
    @Size(min = 7, max = 7, message = "PIN must be exactly 7 characters")
    @ApiModelProperty(value = "Personal Identification Number (PIN)", required = true, example = "AA12345")
    private String pin;

    @ApiModelProperty(value = "Address of the user", example = "Baku Ave. 113, Sumgait")
    private String address;
}