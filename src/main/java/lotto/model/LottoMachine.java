package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private final LottoGenerator lottoGenerator;
    private Money money;
    private Lottoes manualLottoes;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
        this.money = Money.ZERO;
        this.manualLottoes = Lottoes.empty();
    }

    public void inputMoney(Money money) {
        this.money = this.money.plus(money);
    }

    public void registerManualLotto(Lottoes lottoes) {
        money = money.subtract(lottoes.getPrice());
        manualLottoes = manualLottoes.combine(lottoes);
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
