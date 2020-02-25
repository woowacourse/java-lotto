package lotto.domain;

import lotto.util.LottoUtils;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LottoManual {
    private List<Lotto> lottoManual;

    public LottoManual(String lottoManual) {
        this.lottoManual = create(StringUtils.splitLotto(lottoManual));
    }

    public static List<Lotto> create(String[] manualLotteries) {
        List<Lotto> manualLotto = new ArrayList<>();
        for (String numbers : manualLotteries) {
            List<LottoNo> lotto = LottoUtils.toLottoNoList(StringUtils.splitNumber(numbers));
            manualLotto.add(new Lotto(lotto));
        }
        return manualLotto;
    }
}
