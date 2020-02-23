package lotto.domain;

import lotto.domain.exception.InvalidInputSizeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lotto {
    public static final int LOTTO_NUMBER_AMOUNT = 6;
    private static final String INVALID_INPUT_SIZE_EXCEPTION_MESSAGE = "Lotto number amount must be 6.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> randomNumbers) {
        Objects.requireNonNull(randomNumbers);
        validateSize(randomNumbers);
        List<LottoNumber> tempLottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_AMOUNT; i++) {
            tempLottoNumbers.add(LottoNumberGroup.getInstance(randomNumbers.get(i)));
        }
        this.lottoNumbers = tempLottoNumbers;
    }

    private void validateSize(List<Integer> inputNumbers) {
        if (inputNumbers.size() != LOTTO_NUMBER_AMOUNT) {
            throw new InvalidInputSizeException(INVALID_INPUT_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
