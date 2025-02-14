package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto from(final LottoNumbers lottoNumbers) {
        return new Lotto(lottoNumbers);
    }



    public long getMatchCount(LottoNumbers winnerNumbers) {
        return winnerNumbers.getMatchCount(lottoNumbers);
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (LottoNumber lottoNumber : lottoNumbers.getItem()) {
            joiner.add(lottoNumber.toString());
        }
        return joiner.toString();
    }
}
