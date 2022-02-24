package domain;

import java.util.List;

public interface LottoGenerator {
    Lotto generateLotto(List<Integer> lottoNumbers);
}
