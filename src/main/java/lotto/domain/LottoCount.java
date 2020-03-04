package lotto.domain;

import java.util.Objects;
import lotto.util.Count;

public class LottoCount {
    private Count autoLottoCount;
    private Count manualLottoCount;

    public LottoCount(final LottoMoney money) {
        this.manualLottoCount = new Count(0);
        this.autoLottoCount = calculateAutoLottoCount(money);
    }

    public LottoCount(final LottoMoney money, final long manualLottoCount) {
        this.manualLottoCount = new Count(manualLottoCount);
        this.autoLottoCount = calculateAutoLottoCount(money);
    }

    private Count calculateAutoLottoCount(LottoMoney lottoMoney) {
        Objects.requireNonNull(manualLottoCount);
        try {
            return lottoMoney.calculateLottoCount().subtract(this.manualLottoCount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "수동으로 구매할 로또의 갯수가 총 구매할 로또 갯수보다 클 수 없습니다."
            );
        }
    }

    public long getManualLottoCountByLong() {
        return this.manualLottoCount.getCount();
    }

    public long getAutoLottoCountByLong() {
        return this.autoLottoCount.getCount();
    }

    public Count getAutoLottoCount() {
        return this.autoLottoCount;
    }
}
