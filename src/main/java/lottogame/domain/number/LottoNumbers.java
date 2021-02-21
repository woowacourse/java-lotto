package lottogame.domain.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    public static final int DRAWING_COUNT_LIMIT = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        this(new ArrayList<>());
    }

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        validDrawingNumberCount(lottoNumbers.size());
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public void add(final LottoNumber lottoNumber) {
        validNonDuplicateNumbers(lottoNumber);
        validDrawingNumberCount(this.lottoNumbers.size() + 1);
        lottoNumbers.add(lottoNumber);
    }

    private void validNonDuplicateNumbers(final LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    private void validDrawingNumberCount(final int lottoNumbersSize) {
        if (lottoNumbersSize > DRAWING_COUNT_LIMIT) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개이여야 합니다.");
        }
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(new ArrayList<>(this.lottoNumbers));
    }
}
