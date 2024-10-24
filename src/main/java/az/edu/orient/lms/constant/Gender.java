package az.edu.orient.lms.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE('M'),
    FEMALE('F'),
    UNDEFINED('U');
    private final char symbol;
}
