package fitnesstracker.developers;

import fitnesstracker.applications.ApplicationResponse;

import java.util.List;

public record DeveloperInfoResponse(Long id, String email, List<ApplicationResponse> applications) { }
