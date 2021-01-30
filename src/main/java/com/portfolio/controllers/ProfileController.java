package com.portfolio.controllers;

import com.portfolio.exceptions.ProfileNotFoundException;
import com.portfolio.models.Profile;
import com.portfolio.repositories.ProfileRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @throws ProfileNotFoundException
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
}
