package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.Rank;

public class LottoLine {
    private static final String ERROR_LOTTO_NUMBER_DUPLICATED = "[Error] 로또번호 라인은 중복되지 않은 6개의 숫자여야 합니다.";
    protected static final int LOTTO_LINE_SIZE = 6;

    private final List<LottoNumber> value;

    public LottoLine(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> unDuplicatedLottoNumber = new HashSet(lottoNumbers);

        if (unDuplicatedLottoNumber.size() != LOTTO_LINE_SIZE) {
            throw new IllegalArgumentException(
                    ERROR_LOTTO_NUMBER_DUPLICATED);
        }

        value = new ArrayList<>(unDuplicatedLottoNumber);
    }

    public Rank matchLottoNumbers(WinningNumbers winningNumbers) {
        return winningNumbers.matchRank(value);
    }

    public List<LottoNumber> getValue() {
        return Collections.unmodifiableList(value);
    }

    public boolean containNumber(LottoNumber number) {
        return value.contains(number);
    }
}

