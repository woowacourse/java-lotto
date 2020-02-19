package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AllLottoNumbers {
    private static final String EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE = "AllLootNumbers에 Null 혹은 빈 입력이 들어왔습니다.";
    private final List<LottoNumbers> allLottoNumbers;

    public AllLottoNumbers(List<LottoNumbers> allLottoNumbers) {
        validateEmptyOrNull(allLottoNumbers);
        this.allLottoNumbers = allLottoNumbers;
    }

    private void validateEmptyOrNull(List<LottoNumbers> allLottoNumbers) {
        if (Objects.isNull(allLottoNumbers) || allLottoNumbers.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public List<LottoNumbers> getAllLottoNumbers() {
        return Collections.unmodifiableList(allLottoNumbers);
    }
}
