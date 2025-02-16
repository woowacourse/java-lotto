package lotto.service;

import java.util.List;
import lotto.domain.Lotto;

public interface RandomService {

    List<Lotto> generateRandomNumbersList(int count, int maxSize, int maxNumber);
}
