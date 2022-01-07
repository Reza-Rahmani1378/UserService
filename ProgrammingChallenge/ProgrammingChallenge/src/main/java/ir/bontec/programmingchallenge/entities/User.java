package ir.bontec.programmingchallenge.entities;

import ir.bontec.programmingchallenge.base.BaseEntity;
import ir.bontec.programmingchallenge.entities.enumeration.UserType;
import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "user_table";
    private static final String USERTYPE = "user_type";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    @Column(nullable = false)
    private String userId;

    @Column(name = User.FIRST_NAME)
    private String firstName;

    @Column(name = User.LAST_NAME)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(name = User.USERTYPE)
    @Enumerated(EnumType.STRING)
    private UserType userType;


}
