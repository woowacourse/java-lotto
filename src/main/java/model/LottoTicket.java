package model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(final GenerateStrategy generateStrategy) {
        List<Integer> generatedNumbers = generateStrategy.generateNumbers();
        lottoNumbers = generatedNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<Integer> lottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }

    public WinningRank findRank(final List<Integer> winningNumbers, final int bonusNumber) {
        return WinningRank.valueOf(countMatch(winningNumbers), hasBonus(bonusNumber));
    }


    private int countMatch(final List<Integer> winningNumbers) {
        List<Integer> lottoNumbers = lottoNumbers();
        return (int) winningNumbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    private boolean hasBonus(final int bonusNumber) {
        List<Integer> lottoNumbers = lottoNumbers();
        return lottoNumbers.contains(bonusNumber);
    }
}
