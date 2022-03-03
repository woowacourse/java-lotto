package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoCart {
    private static final String ERROR_COUNT_OVER = "[ERROR] 구매할 수 있는 수량을 초과했습니다";
    public static final String ERROR_TYPE = "[ERROR] 로또 구매 수량은 숫자로만 입력해주세요";

    private final List<Lotto> lottos;
    private Money money;
    private int manualCount;

    private LottoCart(Money money, int manualCount) {
        checkCount(money, manualCount);
        money.payLotto(manualCount);
        this.lottos = new ArrayList<>();
        this.money = money;
        this.manualCount = manualCount;
    }

    private void checkCount(Money money, int manualCount) {
        if (!money.isLottoAvailable(manualCount)) {
            throw new IllegalArgumentException(ERROR_COUNT_OVER);
        }
    }

    public static LottoCart of(Money money, String manualCountInput) {
        try {
            return new LottoCart(
                    money, Integer.parseInt(manualCountInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public void addManualLotto(List<String> inputs) {
        if (isManualAvailable()) {
            this.lottos.add(Lotto.from(inputs));
            manualCount--;
        }
    }

    public void addAutoLottos() {
        while (money.isLottoAvailable()) {
            this.lottos.add(Lotto.ofRandom());
            money.payLotto();
        }
    }

    public Lottos getLottos() {
        return new Lottos(this.lottos);
    }

    public boolean isManualAvailable() {
        return manualCount > 0;
    }
}
