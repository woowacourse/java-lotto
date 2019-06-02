package lotto.domain;

public class LottoCount {

    private Money money;
    private int numberOfManualLotto;

    public LottoCount(Money money, int numberOfManualLotto) {
        this.money = money;
        this.numberOfManualLotto = numberOfManualLotto;
        if (money.isOverTotalNumberOfLotto(numberOfManualLotto)) {
            throw new IllegalArgumentException("구매할 수 있는 전체 로또 개수보다 많은 수동 로또를 생성할 수 없습니다.");
        }
    }
}
