package com.lkiszka.rt.githubdetails.infrastructure.rest;

/**
 * @author Lukasz Kiszka
 */
public interface RepositoryDetails<R> {

    R getDetails(String owner, String repositoryName);
}
