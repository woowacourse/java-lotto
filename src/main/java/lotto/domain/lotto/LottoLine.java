package lotto.domain.lotto;

import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_NUMBER_COUNT;
import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.rank.Rank;

public class LottoLine {

    private final List<LottoNumber> lottoNumbers;

    public LottoLine(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR.getMessage());
        }
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public Rank checkLottoLine(LottoLine answerLottoLine, LottoNumber bonusNumber) {
        int matchCount = (int) getMatchCount(answerLottoLine);
        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Rank.getMatchedRank(matchCount, hasBonusNumber);
    }

    private long getMatchCount(LottoLine lottoLine) {
        List<LottoNumber> lottoNumbers = lottoLine.getLottoNumbers();
        return lottoNumbers.stream().filter(this.lottoNumbers::contains).count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

}