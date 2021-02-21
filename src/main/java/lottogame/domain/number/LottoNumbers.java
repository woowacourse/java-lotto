package lottogame.domain.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    public static final int DRAWING_COUNT = 6;
    private List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoNumbers() {
    }

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        validDrawingNumberCount();
    }

    public void add(final LottoNumber lottoNumber) {
        validDrawingNumbers(lottoNumber);
        lottoNumbers.add(lottoNumber);
        validDrawingNumberCount();
    }

    private void validDrawingNumbers(final LottoNumber lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    private void validDrawingNumberCount() {
        if (lottoNumbers.size() > DRAWING_COUNT) {
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
