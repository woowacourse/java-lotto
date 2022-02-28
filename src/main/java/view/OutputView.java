package view;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import model.lottotickets.LottoTicketDto;
import model.winning.Rank;

public class OutputView {
    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String RIGHT_COVER = "[";
    private static final String LEFT_COVER = "]";
    private static final String JOINING_DELIMITER = ", ";
    private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계\n";
    private static final String DIVISION_LINE = "---------\n";

    public void printLottos(final List<LottoTicketDto> lottos) {
        System.out.println(lottos.size() + PURCHASE_COUNT_MESSAGE);
        for (LottoTicketDto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(final LottoTicketDto lotto) {
        System.out.print(RIGHT_COVER);
        printNumbers(lotto.getLottoNumbers());
        System.out.println(LEFT_COVER);
    }

    private void printNumbers(final List<Integer> numbers) {
        StringJoiner stringJoiner = new StringJoiner(JOINING_DELIMITER);
        numbers.forEach(number -> stringJoiner.add(String.valueOf(number)));
        System.out.print(stringJoiner);
    }
}
