package ir.bontec.programmingchallenge.ui.model.response.errormessages;

public enum ErrorMessages {


    MISSING_REQUIRED_FIELD(FieldErrorMessages.MISSING_REQUIRED_FIELD),
    RECORD_ALREADY_EXIST(FieldErrorMessages.RECORD_ALREADY_EXIST),
    INTERNAL_SERVER_ERROR(FieldErrorMessages.INTERNAL_SERVER_ERROR),
    NO_RECORD_FOUND(FieldErrorMessages.NO_RECORD_FOUND),
    AUTHENTICATION_FAILED(FieldErrorMessages.AUTHENTICATION_FAILED),
    COULD_NOT_UPDATE_RECORD(FieldErrorMessages.COULD_NOT_UPDATE_RECORD),
    COULD_NOT_DELETE_RECORD(FieldErrorMessages.COULD_NOT_DELETE_RECORD),
    USERNAME_NOT_FOUND(FieldErrorMessages.USERNAME_NOT_FOUND);


    private String errorMessage;


    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
