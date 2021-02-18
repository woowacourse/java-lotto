package lotto.domain.lotto.lottogenerator;

import java.util.List;
import lotto.domain.lotto.LottoNumber;

@FunctionalInterface
public interface LottoGenerator {

    List<LottoNumber> generateLottoNumbers();
}
