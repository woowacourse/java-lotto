package lotto.domain;

public class WinningLotto {
    // TODO 싱글톤 클래스에서 파라미터로 전달은 어떻게 하는지 생각해보자
    public static final WinningLotto WINNING_LOTTO = new WinningLotto();

    private WinningLotto() {
    }

    /*private WinningLotto(LottoNumbers lottoNumbers) {
    }*/

    public static final WinningLotto getInstance() {
        return WINNING_LOTTO;
    }

    public boolean hasEqualNumber() {
        return false;
    }
}
