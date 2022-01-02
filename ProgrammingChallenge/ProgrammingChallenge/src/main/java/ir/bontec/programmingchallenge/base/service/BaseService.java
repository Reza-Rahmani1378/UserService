package ir.bontec.programmingchallenge.base.service;

import ir.bontec.programmingchallenge.base.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity<PK>, PK extends Serializable> {

    E saveNotSecure(E e);

    List<E> saveAllNotSecure(Collection<E> collection);

    Optional<E> findByIdNotSecure(PK id);

    List<E> findAllNotSecure();


    void deleteByIdNotSecure(PK id);
}
