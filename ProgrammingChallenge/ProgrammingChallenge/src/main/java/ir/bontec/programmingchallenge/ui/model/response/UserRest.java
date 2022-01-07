package ir.bontec.programmingchallenge.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class UserRest extends RepresentationModel<UserRest> {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
}
