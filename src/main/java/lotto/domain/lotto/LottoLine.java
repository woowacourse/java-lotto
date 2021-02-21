package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.Rank;
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

    public Rank matchLottoNumbers(List<LottoNumber> lottoNumbers, WinningNumbers winningNumbers) {
        List<LottoNumber> winningLottoNumbers = winningNumbers.getLastWinningLottoNumbers().getValues();
        int matchCount = (int) lottoNumbers.stream().filter(winningLottoNumbers::contains).count();
        boolean hasBonusNumber = winningLottoNumbers.contains(winningNumbers.getLastWinBonusBall());
        return Rank.matchRank(matchCount, hasBonusNumber);
    }

    public List<LottoNumber> getValues() {
        return Collections.unmodifiableList(
                new ArrayList<>(value));
    }

    public boolean containNumber(LottoNumber number) {
        return value.contains(number);
    }
}

