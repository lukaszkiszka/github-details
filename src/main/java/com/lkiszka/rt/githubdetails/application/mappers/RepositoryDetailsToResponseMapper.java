package com.lkiszka.rt.githubdetails.application.mappers;

import java.util.Optional;

/**
 * @author Lukasz Kiszka
 */
public interface RepositoryDetailsToResponseMapper<T, R> {
    Optional<R> map(T entity);
}
