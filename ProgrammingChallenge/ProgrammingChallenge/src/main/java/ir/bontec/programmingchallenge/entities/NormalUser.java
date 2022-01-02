package ir.bontec.programmingchallenge.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name = NormalUser.TABLE_NAME)
@NoArgsConstructor
@Builder
public class NormalUser extends User {
    public static final String TABLE_NAME = "normal_user";


    private Double balance;

}


