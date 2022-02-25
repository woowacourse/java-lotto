package model.lottotickets;

import java.util.List;
import java.util.stream.Collectors;

import model.LottoNumberGenerator.GenerateStrategy;
import model.lottotickets.vo.LottoNumber;
import model.winning.Rank;

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
                .map(LottoNumber::get)
                .collect(Collectors.toList());
    }

    public Rank selectRank(final List<Integer> winningNumbers, final int bonusNumber) {
        return Rank.valueOf(countMatch(winningNumbers), hasBonus(bonusNumber));
    }


    private int countMatch(final List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.contain(winningNumbers))
                .count();
    }

    private boolean hasBonus(final int bonusNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.contain(bonusNumber));
    }
}
