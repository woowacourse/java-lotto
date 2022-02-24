package lotto.service;

import lotto.domain.Money;
import lotto.repository.MoneyRepository;

public class MoneyService {

    private final MoneyRepository moneyRepository;

    public MoneyService(MoneyRepository moneyRepository) {
        this.moneyRepository = moneyRepository;
    }

    public void insert(Money money) {
        moneyRepository.save(money);
    }

    public Money inquire() {
        return moneyRepository.get();
    }
}
