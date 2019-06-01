package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoManualGenerator implements LottoNumberGenerator {
    public static final int LOTTO_NUMBER_COUNT = 6;
    private String input;

    public LottoManualGenerator(String input) {
        this.input = input;
    }

    private List<Integer> splitInput() {
        List<String> splittedInput = Arrays.asList(input.split(","));
        List<Integer> parsedInput = new ArrayList<>();
        for (String s : splittedInput) {
            parsedInput.add(Integer.parseInt(s));
        }
        checkLottoLength(parsedInput);
        return parsedInput;
    }
    
    private void checkLottoLength(List<Integer> parsedInput) {
        if (parsedInput.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
    }
    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = LottoContainer.createLottoNumbers();
        List<Integer> parsedInput = splitInput();
        List<LottoNumber> retNumbers = new ArrayList<>();
        for (Integer index : parsedInput) {
            retNumbers.add(lottoNumbers.get(index - 1));
        }
        return retNumbers;
    }
}
