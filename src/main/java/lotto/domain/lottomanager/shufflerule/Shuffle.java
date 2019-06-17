package lotto.domain.lottomanager.shufflerule;

import lotto.domain.lottomanager.LottoNumber;

import java.util.List;

public interface Shuffle {
    void shuffleLottoNumbers(List<LottoNumber> lottoNumbers);
}
