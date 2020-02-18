package domain;

import java.util.Set;

abstract class AbstractNumbersContainer {
    private static final int TICKET_SIZE = 6;

    protected void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
        }
    }
}
