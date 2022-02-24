package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;

public class ResultView {
    private static final String MESSAGE_PURCHASE_COUNT = "개를 구매했습니다.";
    private static final String LOTTO_FORMAT = "[%s]%n";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String PRIZE_FORMAT = "%d개 일치 (%d원)- %d개%n";
    private static final String PRIZE_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개%n";

    public static void showPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + MESSAGE_PURCHASE_COUNT);
    }

    public static void showLottos(List<LottoDTO> lottos) {
        for (LottoDTO lotto : lottos) {
            String joinedNumbers = lotto.getNumbers().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
            System.out.printf(LOTTO_FORMAT, joinedNumbers);
        }
    }

    public static void showPrizeInformation(List<PrizeInformationDTO> prizeInformations) {
        for (PrizeInformationDTO prizeInformation : prizeInformations) {
            showOnePrizeInformation(getFormat(prizeInformation), prizeInformation);
        }
    }

    private static String getFormat(PrizeInformationDTO prizeInformation) {
        if (prizeInformation.getBonus()) {
            return PRIZE_BONUS_FORMAT;
        }
        return PRIZE_FORMAT;
    }

    private static void showOnePrizeInformation(String format, PrizeInformationDTO prizeInformation) {
        System.out.printf(format,
                prizeInformation.getMatchingCount(),
                prizeInformation.getAmount(),
                prizeInformation.getPrizeCount());
    }
}
