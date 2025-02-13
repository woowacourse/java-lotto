package view;

import static view.Output.*;

import domain.Amount;
import domain.Rank;
import domain.dto.GetLottoDto;
import domain.dto.GetLottosDto;
import domain.dto.GetResultDto;

public class OutputView {

    // todo : amount dto 변환
    public void printAmount(Amount amount) {
        System.out.printf(PURCHASE_MESSAGE.getMessage(), amount.getAmount());
    }

    public void printLottos(GetLottosDto lottosDto) {
        lottosDto.getLottoDtos().forEach(this::printLotto);
    }

    public void printWinningStatistic(GetResultDto lottosResult) {
        System.out.print(RESULT_TITLE_MESSAGE.getMessage());
        System.out.print(SEPARATE_LINE.getMessage());
        System.out.printf(FIFTH_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.FIFTH));
        System.out.printf(FOURTH_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.FOURTH));
        System.out.printf(THIRD_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.THIRD));
        System.out.printf(SECOND_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.SECOND));
        System.out.printf(FIRST_MESSAGE.getMessage(), lottosResult.countRank().get(Rank.FIRST));
        System.out.printf(PROFIT_MESSAGE.getMessage(), lottosResult.profit());
    }

    private void printLotto(final GetLottoDto getLottoDto) {
        System.out.println(getLottoDto.numbers());
    }



}

