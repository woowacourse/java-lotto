package lotto.application;

public class LottoSession {
    private static long numOfLotto;

    public static void setNumOfLotto(long numOfLotto) {
        LottoSession.numOfLotto = numOfLotto;
    }

    public static long getNumOfLotto() {
        return numOfLotto;
    }
}
