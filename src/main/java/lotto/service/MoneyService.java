package lotto.service;

import lotto.config.RepositoryConfig;
import lotto.domain.Money;
import lotto.repository.MoneyRepository;

public class MoneyService {

    private final MoneyRepository moneyRepository;

    private MoneyService(MoneyRepository moneyRepository) {
        this.moneyRepository = moneyRepository;
    }

    private static class MoneyServiceHelper {
        private static final MoneyService INSTANCE = new MoneyService(RepositoryConfig.getMoneyRepository());
    }

    public static MoneyService getInstance() {
        return MoneyServiceHelper.INSTANCE;
    }

    public void insert(Money money) {
        moneyRepository.save(money);
    }

    public Money inquire() {
        return moneyRepository.get();
    }
}
