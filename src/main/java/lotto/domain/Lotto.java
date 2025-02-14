package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto create(final List<LottoNumber> lottoNumbers) {
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

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> sortedLottoNumbers = sorted(lottoNumbers);

        if (sortedLottoNumbers.size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException("로또 넘버는 6개입니다.");
        }

        if (new HashSet<>(sortedLottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalStateException("중복은 불가능합니다.");
        }
    }

    private List<LottoNumber> sorted(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (LottoNumber lottoNumber : lottoNumbers) {
            joiner.add(lottoNumber.toString());
        }
        return joiner.toString();
    }
}
