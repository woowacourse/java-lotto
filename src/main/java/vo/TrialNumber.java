package vo;

import java.util.Objects;

public class TrialNumber {
    private static final String ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER = "횟수는 1 보다 작을 수 없습니다 😂";
    private static final int TRIAL_CRITERIA = 0;
    private final int trialNumber;

    public TrialNumber(int trialNumber) {
        validatePositive(trialNumber);

        this.trialNumber = trialNumber;
    }

    private void validatePositive(int trialNumber) {
        if (trialNumber <= TRIAL_CRITERIA) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER);
        }
    }

    public int getTrialNumber() {
        return trialNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        TrialNumber that = (TrialNumber) object;
        return trialNumber == that.trialNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trialNumber);
    }

    @Override
    public String toString() {
        return "TrialNumber{" +
                "trialNumber=" + trialNumber +
                '}';
    }
}
