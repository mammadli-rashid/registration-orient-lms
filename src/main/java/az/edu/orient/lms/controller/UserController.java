package az.edu.orient.lms.controller;

import az.edu.orient.lms.constant.ActiveStatus;
import az.edu.orient.lms.constant.Gender;
import az.edu.orient.lms.model.*;
import az.edu.orient.lms.service.UserService;
import az.edu.orient.lms.validation.annotation.ValidSearchActiveStatusCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<RespUserBrief>> getAllUsersBrief(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Date birthDate,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) @Valid List<ReqEducationDetail> educationDetailsCriteria,
            @RequestParam(required = false) @Valid List<ReqCareerDetail> careerDetailsCriteria,
            @RequestParam(required = false) @ValidSearchActiveStatusCriteria List<ActiveStatus> activeStatuses,
            @RequestParam(name = "offset", defaultValue = "0", required = false) Integer offset,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortField", defaultValue = "id", required = false) List<String> sortFields,
            @RequestParam(name = "sortDirection", defaultValue = "asc", required = false) List<String> sortDirections
    ) {
        ReqUserCriteria criteria = ReqUserCriteria.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .mobile(mobile)
                .gender(gender)
                .birthDate(birthDate)
                .address(address)
                .educationDetailsCriteria(educationDetailsCriteria)
                .careerDetailsCriteria(careerDetailsCriteria)
                .activeStatuses(activeStatuses)
                .offset(offset)
                .pageSize(pageSize)
                .sortFields(sortFields)
                .sortDirections(sortDirections)
                .build();
        return new ResponseEntity<>(userService.getAllUsers(criteria), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RespUserDetailed> getUserByIdDetailed(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserByIdDetailed(id), HttpStatus.OK);
    }
}
