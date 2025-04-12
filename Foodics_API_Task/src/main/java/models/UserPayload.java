package models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPayload {
    // Defining properties with annotations for JSON mapping
    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    // Constructor that accepts name and job
    public UserPayload(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for job
    public String getJob() {
        return job;
    }
}
