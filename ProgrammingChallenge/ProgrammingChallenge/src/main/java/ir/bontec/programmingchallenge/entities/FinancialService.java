package ir.bontec.programmingchallenge.entities;

import ir.bontec.programmingchallenge.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = FinancialService.TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialService extends BaseEntity<Long> {
    public static final String TABLE_NAME = "service";

}
