package seedu.tutorswift;

public class Grade {
    private String assessment;
    private int score;

    public Grade(String assessment, int score) {
        this.assessment = assessment;
        this.score = score;
    }

    public String getAssessment() {
        return assessment;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return assessment + ": " + score;
    }
}
