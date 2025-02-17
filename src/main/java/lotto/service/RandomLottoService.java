package lotto.service;

import java.util.List;
import lotto.domain.Lotto;

public interface RandomLottoService {

    List<Lotto> generateLottoList(int count, int maxSize, int maxNumber);
}
