package lotto.controller;

import java.util.Map;
import lotto.model.exception.DuplicatedNumberException;
import lotto.model.exception.InvalidLottoSizeException;
import lotto.model.exception.InvalidNumberRangeException;
import lotto.model.exception.InvalidRankException;
import lotto.model.exception.NotEnoughMoneyException;
import lotto.view.OutputView;

public class ExceptionHandler {

    private static final Map<Class<? extends Exception>, String> EXCEPTION_MESSAGE_MAP =
        Map.of(DuplicatedNumberException.class, "중복된 로또 번호는 입력할 수 없습니다.",
            InvalidLottoSizeException.class, "로또 번호 갯수는 6개여야 합니다.",
            InvalidRankException.class, "일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.",
            InvalidNumberRangeException.class, "로또 번호는 1 ~ 45 사이여야 합니다.",
            NotEnoughMoneyException.class, "금액이 부족합니다.");

    public static void handle(Exception e) {
        OutputView.printMessage(errorMessage(e));
    }

    private static String errorMessage(Exception e) {
        if (EXCEPTION_MESSAGE_MAP.containsKey(e.getClass())) {
            return EXCEPTION_MESSAGE_MAP.get(e.getClass());
        }
        return e.getMessage();
    }

}
