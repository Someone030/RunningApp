/**
 * Represents an achievement with a run streak and goals.
 */
public class Achievements {
    private String runStreak;
    private String goals;

    /**
     * Constructs a new Account instance with the specified details.
     *
     * @param runStreak the current streak of days ran by user
     * @param goals     the specific goal completed by a user
     */

    public Achievements(final String runStreak, final String goals) {
        this.runStreak = runStreak;
        this.goals = goals;
    }

    public String getRunStreak() {return runStreak;}

    public String getGoals() {return goals;}
}