package com.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.Objects;

/**
 * Profile model is using to save profile's data
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
@Entity
@Table(name = "profile")
@Data
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
    @Column()
    private Blob picture;

    @SneakyThrows
    public String getPicture(){
        String base64Encoded = "";

        if (Objects.nonNull(this.picture)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            Blob blob = this.picture;
            InputStream inputStream = blob.getBinaryStream();

            int n = 0;

            while ((n=inputStream.read(buffer))>=0) {
                byteArrayOutputStream.write(buffer, 0, n);
            }

            inputStream.close();
            byte[] encodeBase64 = Base64.encodeBase64(buffer);
            base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
        }

        return base64Encoded;
    }
}
