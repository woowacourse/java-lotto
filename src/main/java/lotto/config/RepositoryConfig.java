package lotto.config;

import lotto.repository.LottoRepository;
import lotto.repository.MoneyRepository;

public class RepositoryConfig {
    public static MoneyRepository getMoneyRepository() {
        return new MoneyRepository();
    }

    public static LottoRepository getLottoRepository() {
        return new LottoRepository();
    }
}
