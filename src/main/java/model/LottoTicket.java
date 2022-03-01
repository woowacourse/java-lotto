package model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(GenerateStrategy generateStrategy) {
        Set<Integer> generatedNumbers = generateStrategy.generateNumbers();
        lottoNumbers = new HashSet<>();
        for (Integer generatedNumber : generatedNumbers) {
            lottoNumbers.add(new LottoNumber(generatedNumber));
        }
    }

    public Set<Integer> getLottoNumberValues() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toSet());
    }
}
