package view;

import static domain.Rank.*;
import static view.OutputMessage.FIFTH_MESSAGE;
import static view.OutputMessage.FIRST_MESSAGE;
import static view.OutputMessage.FOURTH_MESSAGE;
import static view.OutputMessage.NEW_LINE;
import static view.OutputMessage.PROFIT_MESSAGE;
import static view.OutputMessage.PURCHASE_MESSAGE;
import static view.OutputMessage.RESULT_TITLE_MESSAGE;
import static view.OutputMessage.SECOND_MESSAGE;
import static view.OutputMessage.SEPARATE_LINE;
import static view.OutputMessage.THIRD_MESSAGE;

import domain.Amount;
import domain.Rank;
import domain.dto.GetLottoDto;
import domain.dto.GetLottosDto;
import domain.dto.GetResultDto;

public class OutputView {

    public void printAmount(Amount amount) {
        System.out.printf(PURCHASE_MESSAGE, amount.getAmount());
    }

    public void printLottos(GetLottosDto lottosDto) {
        lottosDto.getLottoDtos().forEach(this::printLotto);
    }

    public void printWinningStatistic(GetResultDto lottosResult) {
        System.out.print(RESULT_TITLE_MESSAGE + NEW_LINE);
        System.out.print(SEPARATE_LINE + NEW_LINE);
        System.out.printf(FIFTH_MESSAGE + NEW_LINE, lottosResult.countRank().get(FIFTH));
        System.out.printf(FOURTH_MESSAGE + NEW_LINE, lottosResult.countRank().get(FOURTH));
        System.out.printf(THIRD_MESSAGE + NEW_LINE, lottosResult.countRank().get(THIRD));
        System.out.printf(SECOND_MESSAGE + NEW_LINE, lottosResult.countRank().get(SECOND));
        System.out.printf(FIRST_MESSAGE + NEW_LINE, lottosResult.countRank().get(FIRST));
        System.out.printf(PROFIT_MESSAGE + NEW_LINE, lottosResult.profit());
    }

    private void printLotto(final GetLottoDto getLottoDto) {
        System.out.println(getLottoDto.numbers());
    }


}

