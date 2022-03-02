package lotto.model;


import java.util.ArrayList;
import java.util.List;

public class LottosBuilder {
    private static final String ERROR_COUNT_OVER = "[ERROR] 구매할 수 있는 수량을 초과했습니다";
    public static final String ERROR_TYPE = "[ERROR] 로또 구매 수량은 숫자로만 입력해주세요";

    private final List<Lotto> lottos;
    private int autoCount;
    private int manualCount;

    private LottosBuilder(int count, int manualCount) {
        checkCount(count, manualCount);
        this.lottos = new ArrayList<>();
        this.autoCount = count - manualCount;
        this.manualCount = manualCount;
    }

    public static LottosBuilder of(Money money, String manualCountInput) {
        try {
            return new LottosBuilder(
                    money.countAvailableLotto(), Integer.parseInt(manualCountInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    private void checkCount(int count, int manualCount) {
        if (manualCount > count) {
            throw new IllegalArgumentException(ERROR_COUNT_OVER);
        }
    }

    public void addManualLotto(List<String> inputs) {
        if (isManualAvailable()) {
            this.lottos.add(new Lotto(inputs));
            manualCount--;
        }
    }

    public void addAutoLottos() {
        for (int i = 0; i < autoCount; i++) {
            this.lottos.add(new Lotto());
        }
    }

    public Lottos toLottos() {
        return new Lottos(this.lottos);
    }

    public boolean isManualAvailable() {
        return manualCount > 0;
    }
}
