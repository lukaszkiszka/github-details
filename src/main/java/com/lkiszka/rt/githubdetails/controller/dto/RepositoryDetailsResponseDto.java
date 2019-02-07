package com.lkiszka.rt.githubdetails.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lukasz Kiszka
 */

@Data
public class RepositoryDetailsResponseDto {
    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private LocalDateTime createdAt;
}
