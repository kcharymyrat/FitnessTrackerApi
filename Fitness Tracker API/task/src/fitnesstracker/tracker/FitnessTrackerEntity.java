package fitnesstracker.tracker;

import jakarta.persistence.*;

@Entity
@Table(name = "fitness_tracker")
public class FitnessTrackerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String activity;
    private int duration;
    private int calories;
    private String application;

    public FitnessTrackerEntity() {
    }

    public FitnessTrackerEntity(long id, String username, String activity, int duration, int calories, String application) {
        this.id = id;
        this.username = username;
        this.activity = activity;
        this.duration = duration;
        this.calories = calories;
        this.application = application;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public long getId() {
        return id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
