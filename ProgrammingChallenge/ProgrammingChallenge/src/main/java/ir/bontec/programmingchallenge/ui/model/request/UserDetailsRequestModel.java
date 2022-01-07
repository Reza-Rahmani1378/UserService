package ir.bontec.programmingchallenge.ui.model.request;

import ir.bontec.programmingchallenge.entities.enumeration.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsRequestModel {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private UserType userType;

}
