package lotto;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoCreator {
    private List<List<LottoNumber>> numbers = new ArrayList<>();

    /*public ManualLottoCreator(List<String> inputs) {
        LottoNumbers lottoNumbers = new LottoNumbers();

        for (String input : inputs) {
            List<LottoNumber> inputNumbers = lottoNumbers.getLottoNumbers(parseInts(input));
            numbers.add(inputNumbers);
        }
    }*/

    /*private List<Integer> parseInts(String input) {
        List<Integer> numbers = new ArrayList<>();

        for (String s : input.split(",")) {
            numbers.add(parseInt(s));
        }
        return numbers;
    }*/

    /*private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ;
        }
    }*/

    /*@Override
    public List<Lotto> createLottos(int lottoQuantity) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            lottos.add(new Lotto());
        }
        return lottos;
    }*/
}
