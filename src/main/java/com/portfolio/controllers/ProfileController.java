package com.portfolio.controllers;

import com.portfolio.exceptions.ProfileIdMismatchException;
import com.portfolio.exceptions.ProfileNotFoundException;
import com.portfolio.models.Profile;
import com.portfolio.repositories.ProfileRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Entry point to users routes
 *
 * @author Jorge Díaz
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/users")
public class ProfileController {

    /**
     * Entry point to IO database operations
     */
    @Autowired
    private ProfileRepository profileRepository;

    /**
     * Shows a profile by id
     *
     * @param id user's email
     * @return Profile's model
     * @throws ProfileNotFoundException profile not found
     */
    @GetMapping("/{id}")
    @ExceptionHandler(ProfileNotFoundException.class)
    @ApiOperation(value = "Return book searched", response = Profile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return profile"),
            @ApiResponse(code = 404, message = "Profile Not Found")
    })
    public Profile findOne(@PathVariable String id) throws ProfileNotFoundException {
        return this.profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    /**
     * Updates a profile
     *
     * @param profile New profile data
     * @param id Profile identification
     * @throws ProfileIdMismatchException id is not the same on json body request
     * @throws ProfileNotFoundException profile not found
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Returns book updated")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updates user correctly"),
            @ApiResponse(code = 400, message = "Profile Id mismatch"),
            @ApiResponse(code = 404, message = "Profile Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @RequestPart("json") Profile profile, @RequestPart("image") MultipartFile image, @PathVariable String id
    ) throws ProfileIdMismatchException, ProfileNotFoundException {
        if (profile.getId().compareTo(id) != 0) {
            throw new ProfileIdMismatchException();
        }
        this.profileRepository.findById(id)
                .orElseThrow(ProfileNotFoundException::new);
        profile.setPicture(image);
        this.profileRepository.save(profile);
    }
}
