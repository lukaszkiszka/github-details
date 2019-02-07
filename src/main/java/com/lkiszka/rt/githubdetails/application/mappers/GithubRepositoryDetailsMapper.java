package com.lkiszka.rt.githubdetails.application.mappers;

import com.lkiszka.rt.githubdetails.controller.dto.RepositoryDetailsResponseDto;
import com.lkiszka.rt.githubdetails.infrastructure.rest.github.GithubRepositoryDetailsDto;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Lukasz Kiszka
 */
@Component
class GithubRepositoryDetailsMapper implements RepositoryDetailsToResponseMapper<GithubRepositoryDetailsDto, RepositoryDetailsResponseDto> {


    @Override
    public Optional<RepositoryDetailsResponseDto> map(GithubRepositoryDetailsDto entity) {

        if (Objects.isNull(entity)) {
            return Optional.empty();
        }

        RepositoryDetailsResponseDto repositoryDetailsResponseDto = new RepositoryDetailsResponseDto();
        repositoryDetailsResponseDto.setFullName(entity.getFullName());
        repositoryDetailsResponseDto.setDescription(entity.getDescription());
        repositoryDetailsResponseDto.setCloneUrl(entity.getCloneUrl());
        repositoryDetailsResponseDto.setCreatedAt(entity.getCreatedAt());
        repositoryDetailsResponseDto.setStars(entity.getStars());
        return Optional.of(repositoryDetailsResponseDto);
    }
}
