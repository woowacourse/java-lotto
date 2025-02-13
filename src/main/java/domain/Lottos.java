package domain;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private static List<Integer> generateRandomNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        Random random = new Random();

        while (lottoNumbers.size() < 6) {
            int number = random.nextInt(1, 46);

            if (lottoNumbers.contains(number)) {
                continue;
            }
            lottoNumbers.add(number);
        }

        lottoNumbers.sort(Comparator.naturalOrder());

        return lottoNumbers;
    }

    public static Lottos of(int money) {
        int quantity = money / 1000;

        List<Lotto> generatedLottos = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            List<Integer> lottoNumbers = generateRandomNumbers();
            generatedLottos.add(new Lotto(lottoNumbers));
        }

        return new Lottos(generatedLottos);
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
