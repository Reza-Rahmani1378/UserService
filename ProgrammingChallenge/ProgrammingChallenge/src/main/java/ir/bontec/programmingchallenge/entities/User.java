package ir.bontec.programmingchallenge.entities;

import ir.bontec.programmingchallenge.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity<Long> {

    private static final String TABLE_NAME = "user_table";
}
