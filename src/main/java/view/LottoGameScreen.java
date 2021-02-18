package view;

import domain.ball.LottoBall;
import domain.result.LottoRank;
import util.OutputUtil;
import view.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoGameScreen {
    public static final String BUY_STATUS = "%d개를 구매했습니다.";
    public static final String LOTTO_PREFIX = "[";
    public static final String LOTTO_POSTFIX = "]";
    public static final String DELIMITER = ", ";
    public static final String WINNING_LOTTO_CONFIRMATION = "\n지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_LOTTO_CONFIRMATION = "보너스 볼을 입력해 주세요.";
    public static final String RESULT = "당첨통계";
    public static final String LINE = "----------";
    public static final String STRING_FORMATTER = "%s개 일치 (%d원)- ";
    public static final String REVENUE_RESULT_FORMATTER = "총 수익률은 %.2f입니다.";


    public void showLottoCount(final LottoCountResponseDto lottoCountResponseDto) {
        OutputUtil.printMessage(String.format(BUY_STATUS, lottoCountResponseDto.getLottoCount()));
    }

    public void showAllLottoStatus(final List<LottoResponseDto> lottoResponseDtos) {
        List<LottoNumbersDto> lottoNumbersDtos = lottoResponseDtos.stream()
                .map(lottoResponseDto -> lottoResponseDto.getLottoNumbersDto())
                .collect(Collectors.toList());

        lottoNumbersDtos.stream()
                .forEach(lottoNumberDto -> showLottoStatus(lottoNumberDto));
    }

    private void showLottoStatus(final LottoNumbersDto lottoNumbersDto) {
        List<LottoBall> lottoBalls = lottoNumbersDto.getLottoNumbers();
        String lottoStatus = makeSingleLottoStatus(lottoBalls);
        OutputUtil.printMessage(lottoStatus);
    }

    private String makeSingleLottoStatus(final List<LottoBall> lottoBalls) {
        List<String> status = lottoBalls.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.toList());
        return LOTTO_PREFIX + String.join(DELIMITER, status) + LOTTO_POSTFIX;
    }

    public void confirmWinningLotto() {
        OutputUtil.printMessage(WINNING_LOTTO_CONFIRMATION);
    }

    public void confirmBonusLotto() {
        OutputUtil.printMessage(BONUS_LOTTO_CONFIRMATION);
    }

    public void showDrawResult(DrawResultDto drawResultDto) {
        OutputUtil.printMessage(RESULT);
        OutputUtil.printMessage(LINE);
        showThreeMatchesResult(drawResultDto);
        showFourMatchesResult(drawResultDto);
        showFiveMatchesResult(drawResultDto);
        showFiveMatchesAndBonusResult(drawResultDto);
        showSixMatchesResult(drawResultDto);
    }

    private void showSixMatchesResult(DrawResultDto drawResultDto) {
        Integer count = getCount(drawResultDto, LottoRank.SIX_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "6", 2000000000) + count + "개");
    }

    private void showFiveMatchesAndBonusResult(DrawResultDto drawResultDto) {
        Integer count = getCount(drawResultDto, LottoRank.FIVE_AND_BONUS_MATCHES);
        OutputUtil.printMessage("5개 일치, 보너스 볼 일치(30000000원)- " + count + "개");
    }

    private void showFiveMatchesResult(DrawResultDto drawResultDto) {
        Integer count = getCount(drawResultDto, LottoRank.FIVE_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "5", 1500000) + count + "개");
    }

    private void showFourMatchesResult(DrawResultDto drawResultDto) {
        Integer count = getCount(drawResultDto, LottoRank.FOUR_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "4", 50000) + count + "개");
    }

    private void showThreeMatchesResult(DrawResultDto drawResultDto) {
        Integer count = getCount(drawResultDto, LottoRank.THREE_MATCHES);
        OutputUtil.printMessage(String.format(STRING_FORMATTER, "3", 5000) + count + "개");
    }

    private Integer getCount(DrawResultDto drawResultDto, LottoRank lottoRank) {
        Integer count = drawResultDto.getMatches().get(lottoRank);
        if (Objects.isNull(count)) {
            return 0;
        }
        return count;
    }

    public void showRevenueResult(RevenueDto revenueDto) {
        BigDecimal revenue = revenueDto.getRevenueDto();
        OutputUtil.printMessage(String.format(REVENUE_RESULT_FORMATTER, revenue.doubleValue()));
    }
}

