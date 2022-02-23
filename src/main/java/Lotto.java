import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private List<Integer> lottoNumbers;

    public Lotto() {
        this.lottoNumbers = generateNumber();
    }

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumbers;
    }


    public List<Integer> generateNumber() {

        List<Integer> numbers = IntStream.range(1, 46)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lottoNumbers = numbers.subList(0, 6);

        return numbers.subList(0, 6);
    }

    public int checkWinning(List<Integer> winningNumbers, Integer bonusNumber) {

        int winningCount = (int) lottoNumbers.stream()
                .filter(number -> winningNumbers.contains(number))
                .count();
        int bonusCount = (int) lottoNumbers.stream()
                .filter(number -> bonusNumber.equals(number))
                .count();

        if (winningCount < 3) {
            winningCount = 0;
        }

        if (winningCount != 5) {
            bonusCount = 0;
        }

        return Rewards.getRanking(Rewards.findReward(winningCount, bonusCount));
    }
}

//        winningNumbers, bonusNumber, ranking, 갯수
//        FIRST(6,0,1),
//                SECOND(5, 1, 2),
//                THRID(5, 0, 3),
//                FOURTH(4, 0, 4),
//                FIFTH(3, 0, 5),
//                NO_REWARD(0, 0, 0)
//

/*
    public int checkWinning3(List<Integer> winningNumbers, Integer bonusNumber) {
        int winningMatches = 0;
        int bonusMatches = 0;
        for (Integer winningNumber : winningNumbers) {
            if (lottoNumbers.contains(winningNumber)) {
                winningMatches++;
            }

            if (lottoNumbers.contains(bonusNumber)) {
                bonusMatches++;
            }

        }

        if (winningMatches < 3) {
            winningMatches = 0;
        }

        if (winningMatches != 5) {
            bonusMatches = 0;
        }

        // Map<List<Integer>, Rewards> rankings =  new HashMap<>();
        Map<Pair, Integer> rankings = new HashMap<>();

//        rankings.put(Arrays.asList(6, 0), Rewards.FIRST_REWARD);
//        rankings.put(Arrays.asList(5, 1), Rewards.SECOND_REWARD);
//        rankings.put(Arrays.asList(5, 0), Rewards.THIRD_REWARD);
//        rankings.put(Arrays.asList(4, 0), Rewards.FORTH_REWARD);
//        rankings.put(Arrays.asList(3, 0), Rewards.FIFTH_REWARD);
//        rankings.put(Arrays.asList(0, 0), Rewards.NO_REWARD);

        rankings.put(new Pair(6, 0), 1);
        rankings.put(new Pair(5, 1), 2);
        rankings.put(new Pair(5, 0), 3);
        rankings.put(new Pair(4, 0), 4);
        rankings.put(new Pair(3, 0), 5);
        rankings.put(new Pair(0, 0), -1);

        Pair matches = new Pair(winningMatches, bonusMatches);

        int ranking = rankings.get(matches);

        System.out.println(ranking);
        return ranking;

    }

}


/*
    public int checkWinning(List<Integer> winningNumbers, Integer bonusNumber) {
        List<Integer> lottoNumbers = new ArrayList<>();

        int sameNumberCount = (int)winningNumbers.stream()
                .filter(number -> lottoNumbers.contains(number))
                .count();
        boolean bonusNumberMatched = lottoNumbers.contains(bonusNumber);

        return Lotto_Ranking_Info.convertToPrize(sameNumberCount, bonusNumberMatched);
    }
    */

/*
public enum Lotto_Ranking_Info {
    FIRST_REWARD(6, 0, 2000000000),
    SECOND_REWARD(5, 1, 30000000),
    THIRD_REWARD(5, 0, 1500000),
    FORTH_REWARD(4, 0, 50000),
    FIFTH_REWARD(3, 0, 5000),
    NO_REWARD(-1, 0, 0);

    private final int matchedNumberCount;
    private final boolean bonusNumberMatched;
    private final int rank;

    Lotto_Ranking_Info (int matchedNumberCount, boolean bonusNumberMatched, int prize) {
        this.matchedNumberCount = matchedNumberCount;
        this.bonusNumberMatched = bonusNumberMatched;
        this.rank = prize;
    }

    public int convertToPrize(int matchedNumberCount, boolean bonusNumberMatched) {

    }

}
*/

