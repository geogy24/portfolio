package com.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * Profile model is using to save profile's data
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
@Entity
@Table(name = "profile")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Profile {

    /**
     * Profile's identification
     */
    @Id
    @Column(name = "user_id")
    private String id;

    /**
     * Experience summary
     */
    @Column(name = "experience_summary")
    private String experienceSummary;

    /**
     * Last names
     */
    @Column(name = "last_names")
    private String lastNames;

    /**
     * Names
     */
    @Column(name = "names")
    private String names;

    /**
     * Twitter user id
     */
    @Column(name = "twitter_user_id")
    private String twitterUserId;

    /**
     * Picture
     */
    @Lob
    @Column()
    private byte[] picture;

    @SneakyThrows
    public void setPicture(MultipartFile image) {
        this.picture = image.getBytes();
    }
}
