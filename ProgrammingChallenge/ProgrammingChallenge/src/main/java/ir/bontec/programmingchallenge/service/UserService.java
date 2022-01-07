package ir.bontec.programmingchallenge.service;

import ir.bontec.programmingchallenge.base.service.BaseService;
import ir.bontec.programmingchallenge.entities.User;
import ir.bontec.programmingchallenge.service.dto.UserDTO;
import ir.bontec.programmingchallenge.ui.model.request.UserDetailsRequestModel;
import ir.bontec.programmingchallenge.ui.model.response.OperationStatusModel;
import ir.bontec.programmingchallenge.ui.model.response.UserRest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends BaseService<User, Long>, UserDetailsService {

    UserDTO getUser(String username);

    UserRest getUserByUserId(String id);

    UserRest createUser(UserDetailsRequestModel userDetails);

    UserRest updateUser(String id, UserDetailsRequestModel userDetails);

    OperationStatusModel deleteUserByUserId(String id);

    List<UserRest> getUsers(int page, int limit);

    OperationStatusModel requestPasswordReset(String username);
}
