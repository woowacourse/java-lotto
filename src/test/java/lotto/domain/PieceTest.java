package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.exception.PieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("수동 구매 매수가 구매 가능 매수 초과시, 예외 처리 테스트")
    void excessAvailableLottoPieces() {
        int sampleMoney = 2000;
        int manualPieceNumber = 3;
        Money money = new Money(sampleMoney);
        assertThatThrownBy(() -> new Piece(money, manualPieceNumber))
            .isInstanceOf(PieceException.class)
            .hasMessageContaining(Piece.EXCESS_AVAILABLE_MESSAGE);
    }

    @Test
    @DisplayName("수동 구매 매수 입력시 자동 구매 가능 매수 계산")
    void calculateAutoLottoPieces() {
        int sampleMoney = 4000;
        int manualPieceNumber = 3;
        int autoPieceNumber = 1;
        Money money = new Money(sampleMoney);
        Piece manualPiece = new Piece(money, manualPieceNumber);
        Piece autoPiece = manualPiece.getAnotherPiece(money);
        assertThat(autoPiece.getPieceNumber()).isEqualTo(autoPieceNumber);
    }
}
