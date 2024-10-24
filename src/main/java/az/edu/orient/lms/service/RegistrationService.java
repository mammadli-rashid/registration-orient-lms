package az.edu.orient.lms.service;

import az.edu.orient.lms.constant.RegistrationRequestStatus;
import az.edu.orient.lms.entity.Person;
import az.edu.orient.lms.entity.RegistrationRequest;
import az.edu.orient.lms.entity.User;
import az.edu.orient.lms.exception.incorrectinput.IncorrectUsernameOrPasswordException;
import az.edu.orient.lms.exception.notfound.RegistrationRequestTokenNotFoundException;
import az.edu.orient.lms.mapper.RegistrationRequestMapper;
import az.edu.orient.lms.model.ReqLogin;
import az.edu.orient.lms.model.ReqRegister;
import az.edu.orient.lms.repository.PersonRepository;
import az.edu.orient.lms.repository.RegistrationRequestRepository;
import az.edu.orient.lms.repository.UserRepository;
import az.edu.orient.lms.validation.validator.RegisterRequestUniquenessValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRequestRepository registrationRequestRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final RegisterRequestUniquenessValidator uniquenessValidator;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public void createRegistrationRequest(ReqRegister reqRegister) {
        uniquenessValidator.completeUniqueCheckRegisterRequest(reqRegister);
        registrationRequestRepository.save(RegistrationRequestMapper.toRegistrationRequest(reqRegister));
    }

    @Scheduled(fixedRate = 10000)
    public void processRegistrationRequests() throws MessagingException {
        List<RegistrationRequest> registrationRequests = registrationRequestRepository.findAllByRequestStatus
                (RegistrationRequestStatus.ACCEPTED.getValue());
        if (!registrationRequests.isEmpty()) {
            for (RegistrationRequest registrationRequest : registrationRequests) {
                emailService.sendConfirmationEmail(registrationRequest);
                registrationRequest.setRequestStatus(RegistrationRequestStatus.PROCESSED.getValue());
                registrationRequestRepository.save(registrationRequest);
            }
        }
    }

    @Transactional
    public void confirmRegistrationRequest(String token, ReqLogin reqLogin) throws MessagingException {
        RegistrationRequest registrationRequest = registrationRequestRepository.findByRequestToken(token)
                .orElseThrow(() -> new RegistrationRequestTokenNotFoundException(token));
        if (!reqLogin.getUsername().equals(registrationRequest.getUsername()) ||
                !passwordEncoder.matches(reqLogin.getPassword(), registrationRequest.getPassword())) {
            throw new IncorrectUsernameOrPasswordException();
        }
        Person person = personRepository.save(RegistrationRequestMapper.toPerson(registrationRequest));
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .person(person)
                .build();
        userRepository.save(user);
        registrationRequest.setRequestStatus(RegistrationRequestStatus.CONFIRMED.getValue());
        registrationRequestRepository.save(registrationRequest);
        emailService.sendWelcomeEmail(registrationRequest);
    }
}
