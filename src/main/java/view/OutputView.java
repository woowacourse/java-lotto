package view;

import domain.Lotto;
import domain.LottoRank;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String USER_LOTTOS_HEADER = "%d개를 구매했습니다.";
    private static final String ASK_INPUT_WINNING_LOTTO = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_RESULT_HEADER = "당첨 통계" + LINE_SEPARATOR + "---------";
    private static final String WINNING_PROFIT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";

    public void printAskInputMoney() {
        System.out.println(ASK_INPUT_MONEY);
    }

    public void printUserLottos(final List<Lotto> lottos) {
        final String message = lottos.stream()
                .map(lotto -> lotto.getNumbers().toString())
                .collect(Collectors.joining(LINE_SEPARATOR));
        System.out.println(String.format(USER_LOTTOS_HEADER, lottos.size()) + LINE_SEPARATOR + message);
    }

    public void printAskInputWiningLotto() {
        System.out.println(LINE_SEPARATOR + ASK_INPUT_WINNING_LOTTO);
    }

    public void printAskInputBonusNumber() {
        System.out.println(ASK_INPUT_BONUS_NUMBER);
    }

    public void printWinningResult(Map<LottoRank, Integer> winningResult) {
        System.out.println(LINE_SEPARATOR + WINNING_RESULT_HEADER);
        for (Entry<LottoRank, Integer> entry : winningResult.entrySet()) {
            final LottoRank lottoRank = entry.getKey();
            final int count = entry.getValue();
            if (lottoRank == LottoRank.RANK_2) {
                System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개" + LINE_SEPARATOR, lottoRank.getMatchedCount(),
                        lottoRank.getPrice(), count);
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개" + LINE_SEPARATOR
                    , lottoRank.getMatchedCount(), lottoRank.getPrice(), count);
        }
    }

    public void printWinningProfit(double profitRate) {
        String message = String.format(WINNING_PROFIT, profitRate, "이득");
        if (profitRate < 1) {
            message = String.format(WINNING_PROFIT, profitRate, "손해");
        }
        System.out.println(message);
    }
}
