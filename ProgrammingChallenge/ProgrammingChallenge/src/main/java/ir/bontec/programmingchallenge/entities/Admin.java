package ir.bontec.programmingchallenge.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name = Admin.TABLE_NAME)
@NoArgsConstructor
public class Admin extends User {

    public static final String TABLE_NAME = "admin_user";

}


