package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PurchaseCountDTO;
import lotto.model.dto.PrizeCountDTO;

public class ResultView {
    private static final String FORMAT_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String FORMAT_LOTTO = "[%s]%n";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String FORMAT_PRIZE = "%d개 일치 (%d원)- %d개%n";
    private static final String FORMAT_PRIZE_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개%n";
    private static final String FORMAT_EARNING_RATE = "총 수익률은 %.2f입니다.";


    public void showPurchaseCount(PurchaseCountDTO purchaseCountDTO) {
        System.out.println();
        System.out.printf(FORMAT_COUNT, purchaseCountDTO.getManualCount(), purchaseCountDTO.getAutoCount());
    }

    public void showLottos(List<LottoDTO> lottos) {
        for (LottoDTO lotto : lottos) {
            showLotto(lotto);
        }
        System.out.println();
    }

    private void showLotto(LottoDTO lotto) {
        String joinedNumbers = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
        System.out.printf(FORMAT_LOTTO, joinedNumbers);
    }

    public void showPrizeInformation(List<PrizeCountDTO> prizeInformations) {
        System.out.println();
        for (PrizeCountDTO prizeInformation : prizeInformations) {
            showOnePrizeInformation(getFormat(prizeInformation), prizeInformation);
        }
    }

    private String getFormat(PrizeCountDTO prizeInformation) {
        if (prizeInformation.getPrize().isBonus()) {
            return FORMAT_PRIZE_BONUS;
        }
        return FORMAT_PRIZE;
    }

    private void showOnePrizeInformation(String format, PrizeCountDTO prizeInformation) {
        System.out.printf(format,
                prizeInformation.getPrize().getMatchingCount(),
                prizeInformation.getPrize().getAmount(),
                prizeInformation.getPrizeCount());
    }

    public void showEarningRate(double earningRate) {
        System.out.println();
        System.out.printf(FORMAT_EARNING_RATE, earningRate);
    }
}
