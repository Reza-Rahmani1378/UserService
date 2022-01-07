package ir.bontec.programmingchallenge.service.dto;

import ir.bontec.programmingchallenge.base.BaseDTO;
import ir.bontec.programmingchallenge.entities.enumeration.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO<Long> {

    private String userId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String encryptedPassword;

    private UserType userType;


}
