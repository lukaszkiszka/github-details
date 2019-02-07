package com.lkiszka.rt.githubdetails.controller;

import com.lkiszka.rt.githubdetails.GithubDetailsApplication;
import com.lkiszka.rt.githubdetails.application.services.RepositoryDetilsService;
import com.lkiszka.rt.githubdetails.controller.dto.RepositoryDetailsResponseDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lukasz Kiszka
 */
@RestController
@RequestMapping("/githubdetails")
@Slf4j
class GithubDetailsController {

    @Autowired
    private RepositoryDetilsService<RepositoryDetailsResponseDto> githubDetailsService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "API version", required = true, dataType = "string",
                    paramType = "path", defaultValue = GithubDetailsApplication.API_VERSION)
    })
    @GetMapping(value = "/{version:[Vv]1}/repositories/{owner}/{repository-name}")
    @ResponseBody
    public ResponseEntity<RepositoryDetailsResponseDto> getRepositoryDetails(@PathVariable("owner") String repositoryOwnerName,
                                                                             @PathVariable("repository-name") String repositoryName) {

        log.debug("getRepositoryDetails with params owner='{}', repositoryName='{}'", repositoryOwnerName, repositoryName);
        return ResponseEntity.of(githubDetailsService.getRepositoryDetails(repositoryOwnerName, repositoryName));
    }


}
