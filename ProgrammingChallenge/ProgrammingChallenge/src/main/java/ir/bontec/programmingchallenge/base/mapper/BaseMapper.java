package ir.bontec.programmingchallenge.base.mapper;

import java.util.List;
import java.util.Set;

public interface BaseMapper<E, D> {

    E convertDTOToEntity(D d);

    D convertEntityToDTO(E e);

    List<E> convertListDTOToEntity(List<D> d);

    List<D> convertListEntityToDTO(List<E> e);

    Set<E> convertSetDTOToEntity(Set<D> d);

    Set<D> convertSetEntityToDTO(Set<E> e);


}
