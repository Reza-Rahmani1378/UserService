package ir.bontec.programmingchallenge.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseDTO<PK extends Serializable> implements Serializable {

    private PK id;

}
