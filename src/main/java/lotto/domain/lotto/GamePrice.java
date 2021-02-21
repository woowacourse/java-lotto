package lotto.domain.lotto;

import lotto.domain.number.PayOut;

public class GamePrice {

    private static final int GAME_PRICE = 1000;

    public int getGameCount(PayOut payOut) {
        return payOut.getValueAsInt() / GAME_PRICE;
    }

    public PayOut subtractPayOutByGameCount(PayOut payOut, int gameCount) {
        validateMoreGameCountThenPayOut(payOut, gameCount);
        return payOut.subtract(new PayOut(GAME_PRICE * gameCount));
    }

    private void validateMoreGameCountThenPayOut(PayOut payOut, int gameCount) {
        if( payOut.getValueAsInt() < gameCount * GAME_PRICE ) {
            throw new IllegalArgumentException("구입 금액보다 게임 횟수가 더 클 순 없습니다.");
        }
    }
}
