package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final LottoGenerator lottoGenerator;
    private final Money money;
    private final Lottoes manualLottoes;

    public LottoMachine(LottoGenerator lottoGenerator, Money money, Lottoes manualLottoes) {
        this.lottoGenerator = lottoGenerator;
        this.money = remainMoney(money, manualLottoes);
        this.manualLottoes = manualLottoes;
    }

    private Money remainMoney(Money money, Lottoes lottoes) {
        return money.subtract(lottoes.getPrice());
    }

    public Lottoes issueLotto() {
        return manualLottoes.combine(issueAutoLottoes());
    }

    private Lottoes issueAutoLottoes() {
        return new Lottoes(createLottoes());
    }

    private List<Lotto> createLottoes() {
        return IntStream.range(0, quantity(this.money))
            .mapToObj(i -> lottoGenerator.createLotto())
            .collect(toUnmodifiableList());
    }

    private int quantity(Money money) {
        return money.divide(Lotto.PRICE).intValue();
    }

    public int getManualLottoesSize() {
        return manualLottoes.size();
    }

    public int getAutoLottoesSize() {
        return money.divide(Lotto.PRICE).intValue();
    }
}
