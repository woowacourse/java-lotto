package view;

import domain.ball.LottoBall;
import domain.lotto.LottoTicket;
import domain.lotto.TicketCount;
import domain.result.LottoRank;
import util.OutputUtil;
import view.dto.LottoGameResultDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoGameScreen {
    private static final String LOTTO_TICKET_COUNT = "%d개를 구매했습니다.";
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUTFIX = "]";
    private static final String DELIMITER = ", ";
    private static final String WINNING_LOTTO_CONFIRMATION = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_LOTTO_CONFIRMATION = "보너스 볼을 입력해 주세요.";
    private static final String RESULT = "당첨통계";
    private static final String LINE = "----------";
    private static final String LOTTO_RESULT_MESSAGE = "%s개 일치 (%d원)- %d개";
    private static final String SECOND_PRIZE_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d)원- %d";
    private static final String REVENUE_RESULT_FORMATTER = "총 수익률은 %.2f입니다.";

    public void showTicketCount(final TicketCount lottoCount) {
        OutputUtil.printMessage(String.format(LOTTO_TICKET_COUNT, lottoCount.getTicketCount()));
    }

    public void showAllLottoStatus(final List<LottoTicket> lottoTickets) {
        lottoTickets.forEach(lottoTicket -> showTicketStatus(lottoTicket));
    }

    public void confirmWinningLotto() {
        OutputUtil.printMessage(WINNING_LOTTO_CONFIRMATION);
    }

    public void confirmBonusLotto() {
        OutputUtil.printMessage(BONUS_LOTTO_CONFIRMATION);
    }

    public void showGameResult(final LottoGameResultDto lottoGameResultDto) {
        OutputUtil.printMessage(RESULT);
        OutputUtil.printMessage(LINE);
        showMatchesResult(lottoGameResultDto);
    }

    public void showRevenueResult(final BigDecimal earningsRate) {
        OutputUtil.printMessage(String.format(REVENUE_RESULT_FORMATTER, earningsRate.doubleValue()));
    }

    private void showTicketStatus(final LottoTicket lottoTicket) {
        String lottoStatus = makeSingleLottoTicketStatus(lottoTicket.getLottoBalls());
        OutputUtil.printMessage(lottoStatus);
    }

    private String makeSingleLottoTicketStatus(final List<LottoBall> lottoBalls) {
        return lottoBalls.stream()
                .map(lottoBall -> String.valueOf(lottoBall.getValue()))
                .collect(Collectors.joining(DELIMITER, LOTTO_PREFIX, LOTTO_SUTFIX));
    }

    private void showMatchesResult(final LottoGameResultDto lottoGameResultDto) {
        Map<LottoRank, Integer> matches = lottoGameResultDto.getMatches();
        Arrays.stream(LottoRank.values())
                .filter(LottoRank::hasMatches)
                .forEach(lottoRank ->
                        OutputUtil.printMessage(String.format(findMessage(lottoRank), lottoRank.getMatches(), lottoRank.getPrize(), getCount(matches, lottoRank))));
    }

    private String findMessage(final LottoRank key) {
        if (key.hasBonus()) {
            return SECOND_PRIZE_RESULT_MESSAGE;
        }
        return LOTTO_RESULT_MESSAGE;
    }

    private Integer getCount(final Map<LottoRank, Integer> matches, final LottoRank lottoRank) {
        Integer count = matches.get(lottoRank);
        if (Objects.isNull(count)) {
            return 0;
        }
        return count;
    }
}
