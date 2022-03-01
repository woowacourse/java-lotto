package domain.LottoGenerator;

import domain.Lotto.Lotto;

import java.util.List;

public interface LottoGenerator {
    Lotto generateLotto(List<Integer> lottoNumbers);
}
