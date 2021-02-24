package lotto.domain.lotto;

import java.util.Objects;

public class ManualLotto {

    private static final String ERROR_COLLECT_NUMBER = "올바른 숫자를 입력하여 주세요";
    private static final String ERROR_MIN_LIMIT = "0 이상의 수를 입력하여 주세요";

    private int numLotto;

    public ManualLotto(String numManualLotto) {
        validNumber(numManualLotto);
        validPositive(numManualLotto);
        this.numLotto = Integer.parseInt(numManualLotto);
    }

    public ManualLotto() {
        this.numLotto = 0;
    }

    private void validPositive(String numManualLotto) {
        if (Integer.parseInt(numManualLotto) < 0) {
            throw new IllegalArgumentException(ERROR_MIN_LIMIT);
        }
    }

    private void validNumber(String numManualLotto) {
        try {
            Integer.parseInt(numManualLotto);
        } catch (Exception error) {
            throw new IllegalArgumentException(ERROR_COLLECT_NUMBER);
        }
    }

    public int getNumLotto() {
        return numLotto;
    }

}
