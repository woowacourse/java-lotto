package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.lotto.utils.Rank;
import lotto.view.ErrorMessages;

public class LottoLine {

    private final Set<LottoNumber> value;

    public LottoLine(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> unDuplicatedLottoNumber = new HashSet(lottoNumbers);
        if (unDuplicatedLottoNumber.size() != 6) {
            throw new IllegalArgumentException(
                ErrorMessages.ERROR_LOTTO_NUMBER_DUPLICATED.getMessage());
        }
        value = unDuplicatedLottoNumber;
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

