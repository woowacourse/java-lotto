package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.Rank;
import lotto.view.ErrorMessages;

public class LottoLine {
    protected static final int LOTTO_LINE_SIZE = 6;
    private final List<LottoNumber> value;

    public LottoLine(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> unDuplicatedLottoNumber = new HashSet(lottoNumbers);

        if (unDuplicatedLottoNumber.size() != LOTTO_LINE_SIZE) {
            throw new IllegalArgumentException(
                    ErrorMessages.ERROR_LOTTO_NUMBER_DUPLICATED.getMessage());
        }

        value = Collections.unmodifiableList(
                new ArrayList<>(unDuplicatedLottoNumber));
    }

    public Rank matchLottoNumbers(WinningNumbers winningNumbers) {
        return winningNumbers.matchRank(value);
    }

    public List<LottoNumber> getValue() {
        return value;
    }

    public boolean containNumber(LottoNumber number) {
        return value.contains(number);
    }
}

