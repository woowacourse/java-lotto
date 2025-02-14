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

    public static List<LottoNumber> toLottoNumberList(String input) {
        if (input == null || input.isBlank() || input.endsWith(", ")) {
            throw new IllegalArgumentException("잘못된 입력입니다. 이와 같은 형태로 작성해주세요.(ex. 1, 2, 3, 4, 5, 6)");
        }

        return Arrays.stream(input.split(", "))
                .map(LottoNumber::new)
                .toList();
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
