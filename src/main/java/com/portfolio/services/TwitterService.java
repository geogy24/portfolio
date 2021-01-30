package com.portfolio.services;

import com.portfolio.services.dtos.TweetDTO;
import com.portfolio.services.dtos.TweetServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;

@Service("TwitterService")
public class TwitterService {

    private Environment environment;
    private Twitter twitter;

    private static final byte SHOW_LAST_X_TWEETS = 5;

    @Autowired
    public TwitterService(Environment environment) {
        this.environment = environment;
        ConfigurationBuilder configurationBuilder = this.setTwitterServiceConfiguration();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        this.twitter = twitterFactory.getInstance();
    }

    private ConfigurationBuilder setTwitterServiceConfiguration() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(this.environment.getProperty("app.twitter.consumer-key"))
                .setOAuthConsumerSecret(this.environment.getProperty("app.twitter.consumer-secret"))
                .setOAuthAccessToken(this.environment.getProperty("app.twitter.access-token"))
                .setOAuthAccessTokenSecret(this.environment.getProperty("app.twitter.access-token-secret"));
        return  configurationBuilder;
    }

    public ArrayList<TweetDTO> getLastFiveTweetsFromUserTimeline(String username) throws TwitterException {
        ArrayList<TweetDTO> tweetDTOS = new ArrayList<>();
        twitter4j.ResponseList<twitter4j.Status> statusResponseList = this.twitter.getUserTimeline(username, this.setLimitTweets());

        if (!statusResponseList.isEmpty()) {
            tweetDTOS = (new TweetServiceDTO(statusResponseList)).convertToArrayListDTO();
        }

        return tweetDTOS;
    }

    private Paging setLimitTweets() {
        Paging paging = new Paging();
        paging.setCount(SHOW_LAST_X_TWEETS);
        return paging;
    }
}
