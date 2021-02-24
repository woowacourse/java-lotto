package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.lottoGroup.MixedLottoGroup;

public class OutputView {

  private static final String LOTTO_FORM = "[%s]\n";
  private static final String RESULT_MESSAGE = "당첨 통계";
  private static final String BOUNDARY = "---------";
  private static final String PROFIT_FORM = "총 수익률은 %.1f%%입니다.\n";
  private static final String BOUGHT_LOTTO_FORM = "수동으로 %d장, 자동으로 %d장을 구매했습니다.\n";
  private static final String LOTTO_JOIN_DELIMITER = ", ";
  private static final String LOTTO_RANK_MESSAGE_FORM = "%d개 일치%s(%d원) - %d개";
  private static final String BONUS_MATCH_MESSAGE = ", 보너스 볼 일치";
  private static final String BLANK = "";

  public static void printMessage(final String message) {
    System.out.println(message);
  }

  public static void printBoughtLotto(final MixedLottoGroup lottoGroup) {
    System.out.printf(BOUGHT_LOTTO_FORM, lottoGroup.inputLottoCount(), lottoGroup.randomLottoCount());
    lottoGroup.lottoGroup()
        .forEach(lotto -> System.out.printf(LOTTO_FORM, lottoNumberMessage(lotto)));
  }

  public static void printLottoResult(final LottoResult lottoResult) {
    System.out.println(RESULT_MESSAGE);
    System.out.println(BOUNDARY);

    lottoResult.rankMatch()
        .entrySet()
        .stream()
        .filter(entrySet -> entrySet.getKey() != LottoRank.NONE)
        .forEach(entrySet -> System.out.println(
            lottoRankMessage(entrySet.getKey(), entrySet.getValue()))
        );
    System.out.printf(PROFIT_FORM, lottoResult.winningProfit());
  }

  private static String lottoNumberMessage(Lotto lotto) {
    return lotto.lottoNumbers().stream()
        .map(lottoNumber -> String.valueOf(lottoNumber.get()))
        .collect(Collectors.joining(LOTTO_JOIN_DELIMITER));
  }

  private static String lottoRankMessage(LottoRank lottoRank, int count) {
    if (lottoRank == LottoRank.SECOND) {
      return String.format(LOTTO_RANK_MESSAGE_FORM, lottoRank.matchCount(), BONUS_MATCH_MESSAGE, lottoRank.winningMoney(), count);
    }
    return String.format(LOTTO_RANK_MESSAGE_FORM, lottoRank.matchCount(), BLANK, lottoRank.winningMoney(), count);
  }
}
