package ir.bontec.programmingchallenge.controller;

import ir.bontec.programmingchallenge.exceptions.UserServiceException;
import ir.bontec.programmingchallenge.service.UserService;
import ir.bontec.programmingchallenge.ui.model.request.PasswordResetRequestModel;
import ir.bontec.programmingchallenge.ui.model.request.UserDetailsRequestModel;
import ir.bontec.programmingchallenge.ui.model.response.OperationStatusModel;
import ir.bontec.programmingchallenge.ui.model.response.UserRest;
import ir.bontec.programmingchallenge.ui.model.response.errormessages.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* if We Want get Response in format Xml We use this
    (path = "/{id}" , produces = MediaType.APPLICATION_XML_VALUE)
     */
    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest getUser(@PathVariable String userId) {

        return userService.getUserByUserId(userId);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces =)
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        if (userDetails.getFirstName().isEmpty() || userDetails.getLastName().isEmpty() || userDetails.getUsername().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        return userService.createUser(userDetails);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteUserById(@PathVariable String id) {
        return userService.deleteUserByUserId(id);

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json", MediaType.APPLICATION_XML_VALUE})
    public CollectionModel<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<UserRest> users = userService.getUsers(page, limit);
        for (final UserRest user : users) {
            String userId = user.getUserId();
            Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
            user.add(selfLink);
        }

        Link link = linkTo(UserController.class).withSelfRel();
        return CollectionModel.of(users, link);
    }


    /*
     * http://localhost:1290/programming-chalenge/users/password-reset-request
     * */
    @PostMapping(path = "/password-reset-request", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
        return userService.requestPasswordReset(passwordResetRequestModel.getUsername());
    }

}
