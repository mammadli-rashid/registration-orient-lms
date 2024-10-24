package az.edu.orient.lms.controller;

import az.edu.orient.lms.model.ReqLogin;
import az.edu.orient.lms.model.ReqRegister;
import az.edu.orient.lms.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/apply")
    public ResponseEntity<String> apply(@Valid @RequestBody ReqRegister reqRegister) {
        registrationService.createRegistrationRequest(reqRegister);
        return new ResponseEntity<>("Registration request has sent successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@NotBlank @RequestParam String token,
                                          @RequestBody @NotBlank ReqLogin reqLogin) throws MessagingException {
        registrationService.confirmRegistrationRequest(token, reqLogin);
        return new ResponseEntity<>("Registration completed successfully!", HttpStatus.OK);
    }
}
