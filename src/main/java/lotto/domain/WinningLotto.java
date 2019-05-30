package lotto.domain;

import java.util.*;

public class WinningLotto {
    private static final int SUM_OF_USER_WINNING = 12;
    private final LottoTicket winningLotto;

    public WinningLotto(List<Integer> winningNumbers) {
        if (winningNumbers.size() != AutoLotto.MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException("당첨 번호의 개수는 6개 입니다.");
        }
        this.winningLotto = addManualLottoNumbers(winningNumbers);
    }

    public static LottoTicket addManualLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        Collections.sort(numbers);
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return new LottoTicket(lottoNumbers);
    }

    public int matchNumbersOfLotto(List<LottoNumber> userNumbers) {
        Set<LottoNumber> checkLottoNumbers = new HashSet<>(winningLotto.getLottoNumbers());
        checkLottoNumbers.addAll(userNumbers);
        return SUM_OF_USER_WINNING - checkLottoNumbers.size();
    }
}
