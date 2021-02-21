package view;

import domain.ball.LottoBall;
import domain.lotto.LottoTicket;
import domain.result.LottoRank;
import util.OutputUtil;
import view.dto.LottoGameResultDto;

import java.math.BigDecimal;
import java.util.List;
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
    public static final String STRING_FORMATTER = "%s개 일치 (%d원)- ";
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
        showThreeMatchesResult(lottoGameResultDto);
        showFourMatchesResult(lottoGameResultDto);
        showFiveMatchesResult(lottoGameResultDto);
        showFiveMatchesAndBonusResult(lottoGameResultDto);
        showSixMatchesResult(lottoGameResultDto);
    }

    private void showSixMatchesResult(LottoGameResultDto lottoGameResultDto) {
        Integer count = getCount(lottoGameResultDto, LottoRank.SIX_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "6", 2000000000) + count + "개");
    }

    private void showFiveMatchesAndBonusResult(LottoGameResultDto lottoGameResultDto) {
        Integer count = getCount(lottoGameResultDto, LottoRank.FIVE_AND_BONUS_MATCHES);
        OutputUtil.printMessage("5개 일치, 보너스 볼 일치(30000000원)- " + count + "개");
    }

    private void showFiveMatchesResult(LottoGameResultDto lottoGameResultDto) {
        Integer count = getCount(lottoGameResultDto, LottoRank.FIVE_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "5", 1500000) + count + "개");
    }

    private void showFourMatchesResult(LottoGameResultDto lottoGameResultDto) {
        Integer count = getCount(lottoGameResultDto, LottoRank.FOUR_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "4", 50000) + count + "개");
    }

    private void showThreeMatchesResult(LottoGameResultDto lottoGameResultDto) {
        Integer count = getCount(lottoGameResultDto, LottoRank.THREE_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "3", 5000) + count + "개");
    }

    private Integer getCount(LottoGameResultDto lottoGameResultDto, LottoRank lottoRank) {
        Integer count = lottoGameResultDto.getMatches().get(lottoRank);
        if (Objects.isNull(count)) {
            return 0;
        }
        return count;
    }

    public void showRevenueResult(BigDecimal earningsRate) {
        OutputUtil.printMessage(String.format(REVENUE_RESULT_FORMATTER, earningsRate.doubleValue()));
    }
}

