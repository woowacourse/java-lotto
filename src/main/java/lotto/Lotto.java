package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String SEPERATOR = ",";
    private static final int LOTTO_NUMBER_LENGHT = 6;
    Set<LottoNumber> numbers;

    public Lotto(String input) {
        List<String> tokens = Arrays.asList(input.split(SEPERATOR));
        checkDuplication(tokens);
        checkLottoLength(tokens);
        this.numbers = LottoNumber.generateLottoNumbers(tokens);
    }

    public Lotto(Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    private void checkDuplication(List<String> tokens) {
        Set<String> tokensWithoutDuplicates = new HashSet<>(tokens);
        if(tokens.size() != tokensWithoutDuplicates.size()) {
            throw new IllegalNumberCombinationException();
        }
    }

    private void checkLottoLength(List<String> tokens) {
        if (tokens.size() != LOTTO_NUMBER_LENGHT) {
            throw new IllegalNumberCombinationException();
        }
    }


}
