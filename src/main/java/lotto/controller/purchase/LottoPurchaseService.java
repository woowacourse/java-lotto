package lotto.controller.purchase;

import lotto.domain.buyer.Budget;
import lotto.domain.buyer.LottoBuyer;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoType;
import lotto.utils.LottoNoParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LottoPurchaseService {
    private LottoBuyer lottoBuyer;

    void makeBuyer(int budget) {
        lottoBuyer = new LottoBuyer(new Budget(budget));
    }

    void makeManualLotto(String[] rawLottoInputs) {
        List<Lotto> lottos = Arrays.stream(rawLottoInputs)
                .map(LottoNoParser::parseToLottoNos)
                .map(Lotto::of).collect(Collectors.toList());
        lottoBuyer.buyManualLotto(lottos);
    }

    void makeAutoLotto() {
        lottoBuyer.buyAutoLotto();
    }

    List<String> showLottoInfos() {
        List<String> lottoInfos = new ArrayList<>();
        lottoInfos.add("수동으로 " + lottoBuyer.getCountOfLottoMatch(LottoType.MANUAL) + "개, "
                + "자동으로 " + lottoBuyer.getCountOfLottoMatch(LottoType.AUTOMATIC) + "개를 구매하셨습니다.");
        lottoInfos.addAll(lottoBuyer.showLottos());

        return lottoInfos;
    }

    int calculateMaxManualCount(int budget) {
        return budget / Lotto.LOTTO_PRICE;
    }
}