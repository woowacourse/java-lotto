package lotto.viewer;

import java.util.Scanner;
import lotto.domain.Money;
import lotto.domain.Piece;
import lotto.exception.PieceException;

public class PieceInputView {

    public static final String MANUAL_PIECE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    final Scanner scanner;

    public PieceInputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Piece inputManualPieces(Money money) throws PieceException {
        System.out.println(MANUAL_PIECE_MESSAGE);
        int inputAutoPieces = scanner.nextInt();
        scanner.nextLine();
        return new Piece(money, inputAutoPieces);
    }

}
