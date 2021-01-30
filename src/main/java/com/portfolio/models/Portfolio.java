package com.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Book model is using to save books data
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
@Entity
@Table(name = "portfolio")
@Data
@NoArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Portfolio {

    /**
     * Portfolio's auto generated identification
     */
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "idportfolio")
    private long id;

    /**
     * Description
     */
    @Column(nullable = true)
    private String description;

    /**
     * Image URL
     */
    @Column(name = "image_url", nullable = true)
    private String imageURL;

    /**
     * Twitter Username
     */
    @Column(name = "twitter_user_name", nullable = true)
    private String twitterUsername;

    /**
     * Title
     */
    @Column(name = "title", nullable = true)
    private String title;
}
