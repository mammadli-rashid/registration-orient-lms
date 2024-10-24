package az.edu.orient.lms.validation.validator;

import az.edu.orient.lms.exception.duplicate.DuplicateEmailException;
import az.edu.orient.lms.exception.duplicate.DuplicateMobileException;
import az.edu.orient.lms.exception.duplicate.DuplicatePinException;
import az.edu.orient.lms.exception.duplicate.DuplicateUsernameException;
import az.edu.orient.lms.model.ReqRegister;
import az.edu.orient.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterRequestUniquenessValidator {
    private final UserRepository userRepository;

    public void completeUniqueCheckRegisterRequest(ReqRegister reqRegister){
        if (!isUsernameUnique(reqRegister.getUsername())){
            throw new DuplicateUsernameException(reqRegister.getUsername());
        }
        if (!isEmailUnique(reqRegister.getEmail())){
            throw new DuplicateEmailException(reqRegister.getEmail());
        }
        if (!isMobileUnique(reqRegister.getMobile())){
            throw new DuplicateMobileException(reqRegister.getMobile());
        }
        if (!isPinUnique(reqRegister.getPin())){
            throw new DuplicatePinException(reqRegister.getPin());
        }
    }

    private boolean isUsernameUnique(String username) {
        return userRepository.checkUsernameIsAvailable(username);
    }

    private boolean isEmailUnique(String email) {
        return userRepository.checkEmailIsAvailable(email);
    }

    private boolean isPinUnique(String pin) {
        return userRepository.findAllNonDeletedPins(pin);
    }

    private boolean isMobileUnique(String mobile) {
        return userRepository.checkMobileIsAvailable(mobile);
    }
}
