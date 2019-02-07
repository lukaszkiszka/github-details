package com.lkiszka.rt.githubdetails.application.services;


import java.util.Optional;

/**
 * @author Lukasz Kiszka
 */
public interface RepositoryDetilsService<R> {
    Optional<R> getRepositoryDetails(String repositoryOwnerName, String repositoryName);
}
