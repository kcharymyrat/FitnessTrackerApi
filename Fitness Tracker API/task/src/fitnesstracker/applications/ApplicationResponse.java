package fitnesstracker.applications;

public record ApplicationResponse(
        String id,
        String name,
        String description,
        String apikey
){}
