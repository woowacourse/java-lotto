package lotto.domain;

import com.google.common.primitives.Ints;

import java.util.List;

public class Lotto {
    public static final int LOTTO_START_INDEX = 0;
    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int countMatchingNumbers(List<LottoNumber> numbers) {
        return (int) numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return lottoNumbers.contains(new LottoNumber(bonusNumber));
    }

    public String getLottoSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(Ints.join(", ", lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .toArray()));
        sb.append("]");

        return sb.toString();
    }

    public int getLottoSize() {
        return lottoNumbers.size();
    }
}
