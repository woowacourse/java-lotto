package view;

import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

public class OutputView {

    public static final String RESPONSE_MESSAGE_PURCHASED_LOTTO = "개를 구매했습니다.";

    public static void showPurchasedLottos(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + RESPONSE_MESSAGE_PURCHASED_LOTTO);

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLottoNumbers().stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList()));
        }
    }
}
