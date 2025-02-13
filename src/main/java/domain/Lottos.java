package domain;


import static domain.Lotto.LOTTO_PRICE;
import static domain.Lotto.MAX_LOTTO_NUMBER;
import static domain.Lotto.MAX_LOTTO_SIZE;
import static domain.Lotto.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(int money) {
        int quantity = money / LOTTO_PRICE;

        List<Lotto> generatedLottos = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            List<Integer> lottoNumbers = generateRandomNumbers();
            generatedLottos.add(new Lotto(lottoNumbers));
        }

        return new Lottos(generatedLottos);
    }

    private static List<Integer> generateRandomNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        Random random = new Random();

        while (lottoNumbers.size() < MAX_LOTTO_SIZE) {
            int number = random.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1);

            addNumberIfUnique(lottoNumbers, number);
        }

        lottoNumbers.sort(Comparator.naturalOrder());

        return lottoNumbers;
    }

    private static void addNumberIfUnique(List<Integer> lottoNumbers, int number) {
        if (!lottoNumbers.contains(number)) {
            lottoNumbers.add(number);
        }
    }

    public int getQuantity() {
        return lottos.size();
    }

    public List<String> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }

    public List<MatchDto> getMatchDtos(List<Integer> winningNumbers, int bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.getMatchDto(winningNumbers, bonusNumber))
                .toList();
    }
}
