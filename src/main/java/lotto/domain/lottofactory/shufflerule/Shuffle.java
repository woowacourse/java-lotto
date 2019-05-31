package lotto.domain.lottofactory.shufflerule;

import lotto.domain.lottofactory.LottoNumber;

import java.util.List;

public interface Shuffle {
    void shuffleLottoNumbers(List<LottoNumber> lottoNumbers);
}
