package ir.bontec.programmingchallenge.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name = NormalUser.TABLE_NAME)
@NoArgsConstructor
public class NormalUser extends User {
    public static final String TABLE_NAME = "normal_user";
    private static final String IS_CONFIRMED = "is_confirmed";


    // Balance for use The Service
    private Double balance;

    // is confirmed to use the Service
    @Column(name = NormalUser.IS_CONFIRMED, columnDefinition = "TINYINT(1) default 0")
    private Boolean isConfirmed;


}


