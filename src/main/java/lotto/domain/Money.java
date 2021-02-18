package lotto.domain;

import lotto.exception.LottoPiecesException;

public class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int getLottoPieces(int lottoPrice) {
        int possiblePieces = money / lottoPrice;

        if (possiblePieces <= 0) {
            throw new LottoPiecesException("로또를 사기에 금액이 모자랍니다.");
        }

        return possiblePieces;
    }
}
