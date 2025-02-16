package view;

import static view.OutputMessage.FIFTH_MESSAGE;
import static view.OutputMessage.FIRST_MESSAGE;
import static view.OutputMessage.FOURTH_MESSAGE;
import static view.OutputMessage.PROFIT_MESSAGE;
import static view.OutputMessage.PURCHASE_MESSAGE;
import static view.OutputMessage.RESULT_TITLE_MESSAGE;
import static view.OutputMessage.SECOND_MESSAGE;
import static view.OutputMessage.SEPARATE_LINE;
import static view.OutputMessage.THIRD_MESSAGE;

import domain.Rank;
import domain.dto.AmountDto;
import domain.dto.LottoDto;
import domain.dto.LottosDto;
import domain.dto.ResultDto;

public class OutputView {

    public void printAmount(final AmountDto amountDto) {
        System.out.printf(PURCHASE_MESSAGE.getMessage(), amountDto.amount());
    }

    public void printLottos(final LottosDto lottosDto) {
        lottosDto.getLottoDtos().forEach(this::printLotto);
    }

    public void printWinningStatistic(final ResultDto lottosResult) {
        System.out.print(RESULT_TITLE_MESSAGE.getMessage());
        System.out.print(SEPARATE_LINE.getMessage());
        System.out.printf(FIFTH_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.FIFTH));
        System.out.printf(FOURTH_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.FOURTH));
        System.out.printf(THIRD_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.THIRD));
        System.out.printf(SECOND_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.SECOND));
        System.out.printf(FIRST_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.FIRST));
        System.out.printf(PROFIT_MESSAGE.getMessage(), lottosResult.profit());
    }

    private void printLotto(final LottoDto getLottoDto) {
        System.out.println(getLottoDto.numbers());
    }


}

