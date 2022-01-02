package ir.bontec.programmingchallenge.entities.enumeration;

// Add Enum For Check type Of User
public enum UserType {

    ADMIN, NORMAL_USER;

    @Override
    public String toString() {
        return switch (this) {
            case ADMIN -> "ادمین";
            case NORMAL_USER -> "کاربر ساده";
        };
    }
}
