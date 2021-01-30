package com.portfolio.services.dtos;

import twitter4j.ResponseList;
import twitter4j.Status;

import java.util.ArrayList;

public class TweetServiceDTO {

    private ResponseList<Status> statusResponseList;

    public TweetServiceDTO(ResponseList<Status> statusResponseList) {
        this.statusResponseList = statusResponseList;
    }

    public ArrayList<TweetDTO> convertToArrayListDTO() {
        ArrayList<TweetDTO> tweetList =  new ArrayList<>();

        statusResponseList.forEach(status ->
                tweetList.add(
                    new TweetDTO(
                            status.getUser().getName(),
                            status.getUser().getProfileImageURL(),
                            status.getText()
                    )
                )
        );

        return tweetList;
    }
}
