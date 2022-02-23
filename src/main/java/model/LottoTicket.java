package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(GenerateStrategy generateStrategy) {
        List<Integer> generatedNumbers = generateStrategy.generateNumbers();
        lottoNumbers = new ArrayList<>();
        for (Integer generatedNumber : generatedNumbers) {
            lottoNumbers.add(new LottoNumber(generatedNumber));
        }
    }
}
