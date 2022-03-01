package lotto.config;

import lotto.repository.LottoRepository;
import lotto.repository.MoneyRepository;

public class RepositoryConfig {
    public static MoneyRepository getMoneyRepository() {
        return MoneyRepository.getInstance();
    }

    public static LottoRepository getLottoRepository() {
        return LottoRepository.getInstance();
    }
}