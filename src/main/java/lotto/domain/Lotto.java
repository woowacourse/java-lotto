package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;
    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복되는 숫자가 있습니다");
        }

        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 올바르지 않습니다.");
        }

        this.lottoNumbers = lottoNumbers;
    }

    public int match(Lotto lotto) {
        return (int) lotto.lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumbers.contains(lottoNumber))
                .count();
    }
}
