package view;

import domain.ball.LottoBall;
import domain.lotto.LottoTicket;
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
    public static final String LOTTO_PREFIX = "[";
    public static final String LOTTO_POSTFIX = "]";
    public static final String DELIMITER = ", ";
    public static final String WINNING_LOTTO_CONFIRMATION = "\n지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_LOTTO_CONFIRMATION = "보너스 볼을 입력해 주세요.";
    public static final String RESULT = "당첨통계";
    public static final String LINE = "----------";
    public static final String LOTTO_RESULT_MESSAGE = "%s개 일치 (%d원)- %d개";
    public static final String SECOND_PRIZE_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d)원- %d";
    public static final String REVENUE_RESULT_FORMATTER = "총 수익률은 %.2f입니다.";

    public void showAllLottoStatus(final List<LottoTicket> lottoTickets) {
        lottoTickets.stream()
                .forEach(lottoTicket -> showTicketStatus(lottoTicket));
    }

    private void showTicketStatus(final LottoTicket lottoTicket) {
        String lottoStatus = makeSingleLottoTicketStatus(lottoTicket.getLottoBalls());
        OutputUtil.printMessage(lottoStatus);
    }

    private String makeSingleLottoTicketStatus(final List<LottoBall> lottoBalls) {
        List<String> status = lottoBalls.stream()
                .map(lottoBall -> String.valueOf(lottoBall.getValue()))
                .collect(Collectors.toList());
        return LOTTO_PREFIX + String.join(DELIMITER, status) + LOTTO_POSTFIX;
    }

    public void confirmWinningLotto() {
        OutputUtil.printMessage(WINNING_LOTTO_CONFIRMATION);
    }

    public void confirmBonusLotto() {
        OutputUtil.printMessage(BONUS_LOTTO_CONFIRMATION);
    }

    public void showGameResult(LottoGameResultDto lottoGameResultDto) {
        OutputUtil.printMessage(RESULT);
        OutputUtil.printMessage(LINE);
        showMatchesResult(lottoGameResultDto);
    }

    private void showMatchesResult(LottoGameResultDto lottoGameResultDto) {
        Map<LottoRank, Integer> matches = lottoGameResultDto.getMatches();
        Arrays.stream(LottoRank.values())
                .filter(LottoRank::hasMatches)
                .forEach(lottoRank ->
                        OutputUtil.printMessage(String.format(findMessage(lottoRank), lottoRank.getMatches(), lottoRank.getPrize(), getCount(matches, lottoRank))));
    }

    private String findMessage(LottoRank key) {
        if (key.isSecond()) {
            return SECOND_PRIZE_RESULT_MESSAGE;
        }
        return LOTTO_RESULT_MESSAGE;
    }

    private Integer getCount(Map<LottoRank, Integer> matches, LottoRank lottoRank) {
        Integer count = matches.get(lottoRank);
        if (Objects.isNull(count)) {
            return 0;
        }
        return count;
    }

    public void showRevenueResult(BigDecimal earningsRate) {
        OutputUtil.printMessage(String.format(REVENUE_RESULT_FORMATTER, earningsRate.doubleValue()));
    }
}

