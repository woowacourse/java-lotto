package lotto.domain;

import lotto.exception.NaturalNumberException;
import lotto.exception.PaymentOutOfBoundsException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CountOfLotto {
    private final int countOfManualLotto;
    private final int countOfRandomLotto;

    public CountOfLotto(Payment payment, String countOfManualLotto) {
        if (Objects.isNull(payment)) {
            throw new NullPointerException();
        }

        int totalLotto = payment.calculateCountOfLotto();
        checkCountOfManualLotto(totalLotto, countOfManualLotto);

        this.countOfManualLotto = Integer.parseInt(countOfManualLotto);
        this.countOfRandomLotto = totalLotto - this.countOfManualLotto;
    }

    private void checkCountOfManualLotto(int totalLotto, String countOfManualLotto) {
        if (StringUtils.isBlank(countOfManualLotto)) {
            throw new NullPointerException();
        }
        if (!StringUtils.isNumeric(countOfManualLotto)) {
            throw new NaturalNumberException("수동생성할 로또 개수는 0이상 정수만 가능합니다");
        }
        if (totalLotto < Integer.parseInt(countOfManualLotto)) {
            throw new PaymentOutOfBoundsException("입력한 로또 개수가 지불한 금액을 초과합니다");
        }
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfRandomLotto() {
        return countOfRandomLotto;
    }
}
