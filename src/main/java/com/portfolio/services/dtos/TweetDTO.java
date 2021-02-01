package com.portfolio.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TweetDTO {
    private String name;

    private String profileImageUrl;

    private String tweet;
}
