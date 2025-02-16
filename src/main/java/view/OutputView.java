package view;

import static view.Output.FIFTH_MESSAGE;
import static view.Output.FIRST_MESSAGE;
import static view.Output.FOURTH_MESSAGE;
import static view.Output.PROFIT_MESSAGE;
import static view.Output.PURCHASE_MESSAGE;
import static view.Output.RESULT_TITLE_MESSAGE;
import static view.Output.SECOND_MESSAGE;
import static view.Output.SEPARATE_LINE;
import static view.Output.THIRD_MESSAGE;

import domain.Rank;
import domain.dto.AmountDto;
import domain.dto.LottoDto;
import domain.dto.LottosDto;
import domain.dto.ResultDto;

public class OutputView {

    public void printAmount(AmountDto amountDto) {
        System.out.printf(PURCHASE_MESSAGE.getMessage(), amountDto.amount());
    }

    public void printLottos(LottosDto lottosDto) {
        lottosDto.getLottoDtos().forEach(this::printLotto);
    }

    public void printWinningStatistic(ResultDto lottosResult) {
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

