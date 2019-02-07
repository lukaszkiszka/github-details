package com.lkiszka.rt.githubdetails.application.mappers;

import com.lkiszka.rt.githubdetails.controller.dto.RepositoryDetailsResponseDto;
import com.lkiszka.rt.githubdetails.infrastructure.rest.github.GithubRepositoryDetailsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Lukasz Kiszka
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GithubRepositoryDetailsMapper.class})

public class GithubRepositoryDetailsMapperTest {

    @Autowired
    private RepositoryDetailsToResponseMapper<GithubRepositoryDetailsDto, RepositoryDetailsResponseDto> mapper;

    @Test
    public void shouldCorrectlyMapGithubDetailsDtoToResponseDto() {
        // given
        String str = "2016-02-06 22:05:49";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime createdAtDate = LocalDateTime.parse(str, formatter);
        String fullName = "testOwner/testRepository";
        String description = "Test repository description";
        String cloneUrl = "https://github.com/testOwner/testRepository.git";
        int stars = 10;


        GithubRepositoryDetailsDto githubRepositoryDetailsEntity = new GithubRepositoryDetailsDto();
        githubRepositoryDetailsEntity.setFullName(fullName);
        githubRepositoryDetailsEntity.setDescription(description);
        githubRepositoryDetailsEntity.setCloneUrl(cloneUrl);
        githubRepositoryDetailsEntity.setStars(stars);
        githubRepositoryDetailsEntity.setCreatedAt(createdAtDate);

        // when
        Optional<RepositoryDetailsResponseDto> optionalResponseDto = mapper.map(githubRepositoryDetailsEntity);

        // then
        assertTrue(optionalResponseDto.isPresent());
        RepositoryDetailsResponseDto responseDto = optionalResponseDto.get();
        assertEquals(fullName, responseDto.getFullName());
        assertEquals(description, responseDto.getDescription());
        assertEquals(cloneUrl, responseDto.getCloneUrl());
        assertEquals(stars, responseDto.getStars().intValue());
        assertEquals(createdAtDate, responseDto.getCreatedAt());
    }

    @Test
    public void shouldReturnOptionalEmptyWhenGithubDetailsDtoIsNull() {
        // given
        GithubRepositoryDetailsDto githubRepositoryDetailsEntity = null;

        // when
        Optional<RepositoryDetailsResponseDto> optionalResponseDto = mapper.map(githubRepositoryDetailsEntity);

        // then
        assertFalse(optionalResponseDto.isPresent());
    }


}
