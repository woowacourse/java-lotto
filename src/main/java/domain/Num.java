package domain;

import static domain.LottoInformation.LOTTO_NUMBER_END;
import static domain.LottoInformation.LOTTO_NUMBER_START;

public class Num {
    private int num;

    public Num(int num) {
        validate(num);
        this.num = num;
    }

    private void validate(int num) {
        validateNumberScope(num);
    }

    private void validateNumberScope(int num) {
            if (num < LOTTO_NUMBER_START || num > LOTTO_NUMBER_END) {
                throw new IllegalArgumentException("로또 번호는" + LOTTO_NUMBER_START+"에서 부터" + LOTTO_NUMBER_END +"까지의 수 입니다");
            }
    }

    public int getNum() {
        return num;
    }
}
