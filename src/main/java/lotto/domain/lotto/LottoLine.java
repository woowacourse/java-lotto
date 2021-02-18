package lotto.domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.lotto.utils.Rank;

public class LottoLine {

    private final Set<LottoNumber> value;

    public LottoLine(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != 6) {
            throw new IllegalArgumentException(LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR.getMessage());
        }
        value = set;
    }

    public Rank matchLottoNumbers(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber,
        List<LottoNumber> answerLottoNumbers) {
        int matchCount = (int) lottoNumbers.stream().filter(answerLottoNumbers::contains).count();
        boolean hasBonusNumber = answerLottoNumbers.contains(bonusNumber);
        return Rank.check(matchCount, hasBonusNumber);
    }

    public List<LottoNumber> getValues() {
        return Collections.unmodifiableList(
            new ArrayList<>(value));
    }
}

