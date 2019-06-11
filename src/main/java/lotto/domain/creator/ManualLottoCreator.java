package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidLottoNumbersException;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoCreator implements LottoCreator {
    private List<List<LottoNumber>> numbers = new ArrayList<>();

    public ManualLottoCreator(List<String> inputs) {
        for (String input : inputs) {
            List<LottoNumber> inputNumbers
                    = LottoNumber.getLottoNumbers(CustomStringUtils.parseInts(input));
            numbers.add(inputNumbers);
        }
    }

    @Override
    public List<Lotto> createLottos(int lottoQuantity) {
        checkNumberOfInputLotto(lottoQuantity);

        List<Lotto> lottos = new ArrayList<>();

        for (List<LottoNumber> numbers : numbers) {
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    private void checkNumberOfInputLotto(int lottoQuantity) {
        if (numbers.size() != lottoQuantity) {
            throw new InvalidLottoNumbersException("입력하신 수동 로또 수와 입력하신 번호 셋트의 수가 일치하지 않습니다.");
        }
    }
}
