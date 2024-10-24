package az.edu.orient.lms.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegistrationRequestStatus {
    ACCEPTED(1),
    PROCESSED(2),
    CONFIRMED(3);
    private final int value;
}
