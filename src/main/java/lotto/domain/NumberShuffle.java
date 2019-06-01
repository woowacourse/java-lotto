package lotto.domain;

import java.util.List;

@FunctionalInterface
public interface NumberShuffle {
    List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers);
}
