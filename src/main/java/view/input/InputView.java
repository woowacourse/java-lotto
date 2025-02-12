package view.input;

import java.util.List;

public interface InputView {
    
    int readPurchaseAmount();

    List<Integer> readWinningNumber();

    int readBonusNumber();
}
