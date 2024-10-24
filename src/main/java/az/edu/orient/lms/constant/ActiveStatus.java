package az.edu.orient.lms.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActiveStatus {
    ACTIVE(1),
    BLOCKED(2),
    DELETED(0);
    private final int value;
}
