package ir.bontec.programmingchallenge.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity<PK extends Serializable> implements Serializable {

    // I use the generic id for My entities that extend this class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;

}
