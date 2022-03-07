package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.generator.NumberGenerator;

public class LottoTicket {

    private final List<LottoLine> lines;
    private final Money money;

    public LottoTicket(List<LottoLine> lines, Money money) {
        this.lines = lines;
        this.money = money;
    }

    public static LottoTicket createLottoTicket(NumberGenerator generator, Money money) {
        validatePurchasable(money);
        List<LottoLine> lines = createLines(generator, calculatePurchasableCount(money));
        return new LottoTicket(lines, money);
    }

    private static void validatePurchasable(Money money) {
        if (!money.isGreatThanOrEqualTo(Money.from(LottoLine.PRICE))) {
            throw new IllegalArgumentException("로또 구매에 필요한 금액이 부족합니다.");
        }
    }

    private static int calculatePurchasableCount(Money money) {
        return money.divide(Money.from(LottoLine.PRICE));
    }

    private static List<LottoLine> createLines(NumberGenerator generator, int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> LottoLine.createLine(generator))
            .collect(Collectors.toList());
    }

    public List<LottoLine> getLines() {
        return lines;
    }

    public Money getMoney() {
        return money;
    }
}
