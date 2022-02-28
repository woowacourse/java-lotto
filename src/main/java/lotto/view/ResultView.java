package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.LottosDTO;
import lotto.model.dto.PrizeInformationDTO;

public class ResultView {
    private static final String FORMAT_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String FORMAT_LOTTO = "[%s]%n";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String FORMAT_PRIZE = "%d개 일치 (%d원)- %d개%n";
    private static final String FORMAT_PRIZE_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개%n";
    private static final String FORMAT_EARNING_RATE = "총 수익률은 %.2f입니다.";


    public static void showPurchaseCount(LottosDTO lottosDTO) {
        System.out.println();
        System.out.printf(FORMAT_COUNT, lottosDTO.getAutoCount(), lottosDTO.getManualCount());
    }

    public static void showLottos(List<LottoDTO> lottos) {
        for (LottoDTO lotto : lottos) {
            showLotto(lotto);
        }
        System.out.println();
    }

    private static void showLotto(LottoDTO lotto) {
        String joinedNumbers = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
        System.out.printf(FORMAT_LOTTO, joinedNumbers);
    }

    public static void showPrizeInformation(List<PrizeInformationDTO> prizeInformations) {
        System.out.println();
        for (PrizeInformationDTO prizeInformation : prizeInformations) {
            showOnePrizeInformation(getFormat(prizeInformation), prizeInformation);
        }
    }

    private static String getFormat(PrizeInformationDTO prizeInformation) {
        if (prizeInformation.getBonus()) {
            return FORMAT_PRIZE_BONUS;
        }
        return FORMAT_PRIZE;
    }

    private static void showOnePrizeInformation(String format, PrizeInformationDTO prizeInformation) {
        System.out.printf(format,
                prizeInformation.getMatchingCount(),
                prizeInformation.getAmount(),
                prizeInformation.getPrizeCount());
    }

    public static void showEarningRate(double earningRate) {
        System.out.println();
        System.out.printf(FORMAT_EARNING_RATE, earningRate);
    }
}
