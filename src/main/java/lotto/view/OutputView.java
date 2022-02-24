package lotto.view;

import lotto.domain.ChoiceNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoDto;

public class OutputView {
    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printLotto(LottoDto lottoDto) {
        System.out.println(String.format("%d개를 구매했습니다.", lottoDto.getLottoSize()));
        for (ChoiceNumber choiceNumber : lottoDto.getLotto()) {
            System.out.println(choiceNumber);
        }
    }

    public void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoRank lottoRank : result.getResult().keySet()) {
            String message = String.format("%d개 일치%s(%d원) - %d개", lottoRank.getCorrectNumber(),
                    getBonusMessage(lottoRank), lottoRank.getPrizeAmount(),
                    result.getResult().get(lottoRank));
            System.out.println(message);
        }
    }

    private String getBonusMessage(LottoRank lottoRank) {
        if (lottoRank == LottoRank.RANK_SECOND) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    public void printYield(double yield) {
        System.out.println(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", yield));

    }
}
