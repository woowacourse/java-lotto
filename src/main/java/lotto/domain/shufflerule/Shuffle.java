package lotto.domain.shufflerule;

import lotto.domain.lottofactory.LottoNumber;

import java.util.List;

public interface Shuffle {
    List<LottoNumber> getShuffledLottoNumbers(List<LottoNumber> lottoNumbers);
}
