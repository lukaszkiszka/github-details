package com.lkiszka.rt.githubdetails.application.services;

import com.lkiszka.rt.githubdetails.application.mappers.RepositoryDetailsToResponseMapper;
import com.lkiszka.rt.githubdetails.controller.dto.RepositoryDetailsResponseDto;
import com.lkiszka.rt.githubdetails.infrastructure.rest.github.GithubRepositoryApiFasade;
import com.lkiszka.rt.githubdetails.infrastructure.rest.github.GithubRepositoryDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Lukasz Kiszka
 */
@Service
class GithubDetailsService implements RepositoryDetilsService<RepositoryDetailsResponseDto> {

    @Autowired
    private RepositoryDetailsToResponseMapper<GithubRepositoryDetailsDto, RepositoryDetailsResponseDto> mapper;

    @Autowired
    private GithubRepositoryApiFasade githubRepositoryApiFasade;

    @Override
    public Optional<RepositoryDetailsResponseDto> getRepositoryDetails(String repositoryOwnerName, String repositoryName) {
        GithubRepositoryDetailsDto githubResponse = githubRepositoryApiFasade.getDetails(repositoryOwnerName, repositoryName);
        return mapper.map(githubResponse);
    }
}
