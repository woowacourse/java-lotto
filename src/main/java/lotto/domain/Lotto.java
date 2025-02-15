package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

public class Lotto {

    private static final String DELIMITER = ",";
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto from(List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lotto from(String input) {
        if (input == null || input.isBlank() || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 이와 같은 형태로 작성해주세요.(ex. 1, 2, 3, 4, 5, 6)");
        }

        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException("구분자(,)를 활용해주세요! (ex. 1, 2, 3, 4, 5, 6)");
        }

        return new Lotto(Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new).toList());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public long getMatchCount(Lotto winnerNumbers) {
        return winnerNumbers.getMatchCount(lottoNumbers);
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    private long getMatchCount(List<LottoNumber> winnerNumbers) {
        return lottoNumbers.stream()
                .filter(winnerNumbers::contains)
                .count();
    }

    private void validLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException("로또 번호 6개를 입력해주세요.");
        }

        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalStateException("중복은 불가능합니다.");
        }
    }

    private List<LottoNumber> sortedInAscending(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (LottoNumber lottoNumber : sortedInAscending(lottoNumbers)) {
            joiner.add(lottoNumber.toString());
        }

        return joiner.toString();
    }


}
