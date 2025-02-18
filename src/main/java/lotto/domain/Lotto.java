package lotto.domain;

import java.util.StringJoiner;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }


    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (LottoNumber lottoNumber : lottoNumbers.getLottoNumbers()) {
            joiner.add(lottoNumber.toString());
        }
        return joiner.toString();
    }
}
