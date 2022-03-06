package old.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import old.domain.generator.NumberGenerator;

public class LottoMachine {

    public static final int ALL = 0;

    private Money money;

    public LottoMachine(Money money) {
        this.money = money;
    }

    public List<LottoTicket> createLottos(NumberGenerator generator, int count) {
        if (count == ALL) {
            return createLottos(generator, money.getAmount() / LottoTicket.PRICE);
        }
        validatePurchasable(count);
        decreaseMoney(count);
        return IntStream.range(0, count)
            .mapToObj(i -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
    }

    private void decreaseMoney(int count) {
        money = money.minus(Money.from(count * LottoTicket.PRICE));
    }

    private void validatePurchasable(int count) {
        if (!money.isGreatThanOrEqualTo(Money.from(count * LottoTicket.PRICE))) {
            throw new IllegalArgumentException("로또 구매에 필요한 금액이 부족합니다.");
        }
    }

}
