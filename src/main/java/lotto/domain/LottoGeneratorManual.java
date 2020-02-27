package lotto.domain;

import lotto.util.LottoUtils;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoGeneratorManual implements LottoGenerator {

    public static List<Lotto> createUserLotto(String[] manualLottoNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (String numbers : manualLottoNumbers) {
            Set<LottoNo> lotto = LottoUtils.toLottoNoSet(StringUtils.splitByComma(numbers));
            lottos.add(new Lotto(lotto));
        }
        return lottos;
    }

    @Override
    public List<Lotto> generator(Customer customer) {
        String[] manualLottoNumbers = StringUtils.splitByLineSeparator(
                customer.getManualLottoNumber());
        return createUserLotto(manualLottoNumbers);
    }

}
