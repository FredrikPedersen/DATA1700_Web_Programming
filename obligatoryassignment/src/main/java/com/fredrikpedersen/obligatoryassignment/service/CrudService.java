package com.fredrikpedersen.obligatoryassignment.service;

import com.fredrikpedersen.obligatoryassignment.domain.models.DomainEntity;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.DTO;

import java.util.List;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 20:03
 * @param <T> The type of DTO model the service is being implemented for
 * @param <S> The type of Domain model the service is being implemented for
 */

public interface CrudService<T extends DTO, S extends DomainEntity> {

    List<T> findAll();
    T createNew(final T dtoObject);
    void deleteAll();

    //This is a helper method. Consider creating an abstract class which implements this interface, which overrides this method, as to not make it public.
    T saveAndReturnDTO(final S domainObject);
}
