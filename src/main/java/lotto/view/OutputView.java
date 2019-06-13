package lotto.view;

import lotto.domain.WinningResult;
import lotto.domain.lotto.LottoType;
import lotto.dto.LottoDto;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.getProperty("line.separator");

    public static void printErrorMsg(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printManualInputMsg() {
        System.out.println(NEW_LINE + "수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printContainingLottos(List<LottoDto> lottoDtos) {
        List<String> lottos = lottoDtos.stream().map(LottoDto::getLottoNo).collect(Collectors.toList());
        System.out.println(NEW_LINE + "수동으로 " + lottoDtos.stream()
                .filter(lottoDto -> lottoDto.getLottoType().equals(LottoType.MANUAL.getType())).count()
                + "개, 자동으로 " + lottoDtos.stream()
                .filter(lottoDto -> lottoDto.getLottoType().equals(LottoType.AUTOMATIC.getType())).count()
                + "개를 구매하셨습니다.");
        for (String lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printResult(WinningResult winningResult) {
        int[] rankCount = winningResult.getRankCounter();
        System.out.println(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
        System.out.println("3개 일치 (5000원) - " + rankCount[1] + "개");
        System.out.println("4개 일치 (50000원) - " + rankCount[2] + "개");
        System.out.println("5개 일치 (1500000원) - " + rankCount[3] + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + rankCount[4] + "개");
        System.out.println("6개 일치 (2000000000원) - " + rankCount[5] + "개");
        System.out.println("총 수익률은 "
                + winningResult.getWinningMoney().multiply(BigInteger.valueOf(100))
                .divide(BigInteger.valueOf(winningResult.getSpendMoney()))
                + "% 입니다.");
    }
}