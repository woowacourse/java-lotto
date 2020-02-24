package domain;

public class LottoCount {
    private static int lottoCount = Money.getLottoCount();

    public static int getLottoCount() {
        return lottoCount;
    }

    public static int getAutoLottoCount() {
        return lottoCount - ManualCount.getManualCount();
    }
}
