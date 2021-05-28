package com.ederfaria.parking.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eder
 */
@RestController
@RequestMapping("api/v1/version")
@CrossOrigin(origins = "*")
public class VersionController {

    @GetMapping
    public String getVersion() {
        return "1.0.0-Parking";
    }

}
