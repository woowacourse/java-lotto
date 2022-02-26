package vo;

public class NumberOfLottos {
    private static final String ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER = "횟수는 1 보다 작을 수 없습니다 😂";
    private static final int TRIAL_CRITERIA = 0;
    private final int numberOfLottos;

    public NumberOfLottos(int trialNumber) {
        validatePositive(trialNumber);

        this.numberOfLottos = trialNumber;
    }

    private void validatePositive(int trialNumber) {
        if (trialNumber <= TRIAL_CRITERIA) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER);
        }
    }

    public int getNumberOfLottos() {
        return numberOfLottos;
    }

    @Override
    public String toString() {
        return "NumberOfLottos{" +
                "numberOfLottos=" + numberOfLottos +
                '}';
    }
}
