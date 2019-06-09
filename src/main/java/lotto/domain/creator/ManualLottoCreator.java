package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.exception.InvalidLottoNumbersException;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoCreator implements LottoCreator {
    private List<List<LottoNumber>> numbers = new ArrayList<>();

    public ManualLottoCreator(List<String> inputs) {
        for (String input : inputs) {
            List<LottoNumber> inputNumbers = LottoNumber.getLottoNumbers(parseInts(input));
            numbers.add(inputNumbers);
        }
    }

    private List<Integer> parseInts(String input) {
        List<Integer> numbers = new ArrayList<>();

        for (String s : input.split(",")) {
            numbers.add(parseInt(s));
        }
        return numbers;
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumbersException("로또 번호들을 숫자로 입력해주세요.");
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
