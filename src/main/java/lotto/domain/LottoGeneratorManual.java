package lotto.domain;

import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoGeneratorManual implements LottoGenerator {
    private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

    private static List<Lotto> createManualLotto(String[] manualLottoNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (String numbers : manualLottoNumbers) {
            Set<LottoNo> lotto = toLottoNoSet(StringUtils.splitByComma(numbers));
            lottos.add(new Lotto(lotto));
        }
        return lottos;
    }

    private static Set<LottoNo> toLottoNoSet(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toSet());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
        }
    }

    @Override
    public List<Lotto> generator(Customer customer) {
        String[] manualLottoNumbers = StringUtils.splitByLineSeparator(
                customer.getManualLottoNumber());
        return createManualLotto(manualLottoNumbers);
    }
}
