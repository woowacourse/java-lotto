package domain;

public class LottoCount {
    private static int lottoCount = Money.getLottoCount();
    private static int autoLottoCount = lottoCount - ManualCount.getManualCount();

    public static int getLottoCount() {
        return lottoCount;
    }

    public static int getAutoLottoCount() {
        return autoLottoCount;
    }
}
