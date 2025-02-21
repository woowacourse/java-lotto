package view;

import domain.Lotto;
import domain.LottoBuyResultFormatter;
import domain.WinningCalculateFormatter;
import domain.dto.WinningCalculateDto;
import exception.LottoException;
import java.util.List;

public class OutputView {
    private final LottoBuyResultFormatter lottoBuyResultFormatter;
    private final static String BUY_LOTTO_MONEY = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private final WinningCalculateFormatter winningCalculateFormatter;

    public OutputView(LottoBuyResultFormatter lottoBuyResultFormatter,
                      WinningCalculateFormatter winningCalculateFormatter) {
        this.winningCalculateFormatter = winningCalculateFormatter;
        this.lottoBuyResultFormatter = lottoBuyResultFormatter;
    }

    public void printBuyLottoMoney() {
        printLine(BUY_LOTTO_MONEY);
    }

    private void printLine(String message) {
        System.out.println(message);
    }

    public void printError(LottoException lottoException) {
        printLine(lottoException.getMessage());
    }

    public void printBuyLotto(List<Lotto> lottos) {
        printLine(lottoBuyResultFormatter.formattingBuyLottoResult(lottos));
    }

    public void printInputWinningNumber() {
        printLine(INPUT_WINNING_NUMBER);
    }

    public void printInputBonusNumber() {
        printLine(INPUT_BONUS_NUMBER);
    }

    public void printWinningResult(WinningCalculateDto winningCalculateDto) {
        printLine(winningCalculateFormatter.winningResultFormatting(winningCalculateDto.winningCalculateResult(),
                winningCalculateDto.earnMoney()));
    }
}
