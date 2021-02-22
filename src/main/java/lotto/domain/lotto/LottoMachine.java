package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    private static final String REGEX = ",";
    private static final String REGEX_WITH_SPACE = ", ";

    public List<Lotto> createAndPutLotto(List<Lotto> lottoTickets, String lottoNumbers) {
        List<Integer> numbers = Arrays.stream(lottoNumbers.split(REGEX))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        lottoTickets.add(new Lotto(numbers));

        return Collections.unmodifiableList(lottoTickets);
    }

    public List<Integer> createLottoNumbers(String lottoNumbers) {
        String values = lottoNumbers.replaceAll(REGEX_WITH_SPACE, REGEX);
        return Arrays.stream(values.split(REGEX))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public WinningLotto createWinningLotto(List<Integer> lottoNumbers, String bonusNumber) {
        return new WinningLotto(lottoNumbers, Integer.parseInt(bonusNumber));
    }
}
