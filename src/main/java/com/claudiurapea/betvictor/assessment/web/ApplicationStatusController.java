package com.claudiurapea.betvictor.assessment.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping("/application")
public class ApplicationStatusController {

    private final String applicationVersion;

    public ApplicationStatusController(@Value("${application.version}") String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok(format("VERSION: %s", applicationVersion));
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("STATUS: UP");
    }

}
