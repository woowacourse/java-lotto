package lotto.domain;

import lotto.exception.PieceException;

public class Piece {

    public static final String EXCESS_AVAILABLE_MESSAGE = "설정 가능한 매수를 초과하였습니다.";

    private final int piece;

    public Piece(Money money, int targetPiece) {
        checkAvailablePiece(money, targetPiece);
        this.piece = targetPiece;
    }

    private void checkAvailablePiece(Money money, int targetPiece) {
        if (money.getLottoPieces() < targetPiece) {
            throw new PieceException(EXCESS_AVAILABLE_MESSAGE);
        }
    }

    public Piece getAnotherPiece(Money money) {
        int anotherPieceNumber = money.getLottoPieces() - this.piece;
        return new Piece(money, anotherPieceNumber);
    }

    public int getPieceNumber() {
        return this.piece;
    }
}
