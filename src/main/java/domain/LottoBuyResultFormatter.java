package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoBuyResultFormatter {

    private static final String BUY_LOTTO_AMOUNT_FORMAT = "%d개를 구매했습니다.\n";
    private static final String BUY_LOTTO_NUMBERS_FORMAT = "[%s]";

    public String formatNumbers(List<LottoNumber> lottoNumbers) {
        String formattedLottoNumbers = lottoNumbers.stream()
                .map((number) -> number + "")
                .collect(Collectors.joining(", "));
        return String.format(BUY_LOTTO_NUMBERS_FORMAT, formattedLottoNumbers);
    }

    public String formattingBuyLottoResult(List<Lotto> lottos) {
        StringBuilder stringBuilder = new StringBuilder(String.format(BUY_LOTTO_AMOUNT_FORMAT, lottos.size()));
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto.buyNumber())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
