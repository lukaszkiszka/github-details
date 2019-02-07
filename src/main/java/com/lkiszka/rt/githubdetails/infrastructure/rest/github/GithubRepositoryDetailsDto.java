package com.lkiszka.rt.githubdetails.infrastructure.rest.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lukasz Kiszka
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryDetailsDto {
    @JsonProperty("full_name")
    private String fullName;
    private String description;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("stargazers_count")
    private Integer stars;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
