package domain.lottoGame.shuffleStrategy;

import domain.lottoGame.LottoNumber;

import java.util.List;

public interface ShuffleStrategy {
    List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers);
}
