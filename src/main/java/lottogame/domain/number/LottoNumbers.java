package lottogame.domain.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {

    public static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        this(new ArrayList<>());
    }

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        validateDuplicate(this.lottoNumbers);
    }

    private void validateDuplicate(final List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("잘못된 당첨번호 입력입니다.");
        }
    }

    public void add(final LottoNumber lottoNumber) {
        if (contains(lottoNumber)) {
            throw new IllegalArgumentException("중복된 당첨번호가 존재합니다.");
        }
        lottoNumbers.add(lottoNumber);
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(new ArrayList<>(this.lottoNumbers));
    }
}
