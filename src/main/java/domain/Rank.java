package domain;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Arrays;

public enum Rank {
    FIFTH(5, 3, 5000),
    FOURTH(4, 4, 50000),
    THIRD(3, 5, 1500000, false),
    SECOND(2, 5, 30000000, true),
    FIRST(1, 6, 2000000000),
    NONE(0, 0, 0);

    private final int rankNumber;
    private final int criteria;
    private final int reward;
    private boolean hitBonusBall;

    Rank(int rankNumber, int criteria, int reward) {
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
    }

    Rank(int rankNumber, int criteria, int reward, boolean hitBonusBall) {
        this(rankNumber, criteria, reward);
        this.hitBonusBall = hitBonusBall;
    }

    public static Rank judgeResult(Result result) {
        return Arrays.stream(Rank.values())
                .filter(result::isWhatRank)
                .findFirst()
                .orElse(NONE);
    }

    public int getRankNumber() {
        return rankNumber;
=======
import java.util.List;
=======
import java.util.Arrays;
>>>>>>> a879dd3 (feat : 구매한 모든 로또의 결과를 기록하는 클래스 추가)

public enum Rank {
    FIFTH(5, 3, 5000),
    FOURTH(4, 4, 50000),
    THIRD(3, 5, 1500000, false),
    SECOND(2, 5, 30000000, true),
    FIRST(1, 6, 2000000000),
    NONE(0, 0, 0);

    private final int rankNumber;
    private final int criteria;
    private final int reward;
    private boolean hitBonusBall;

    Rank(int rankNumber, int criteria, int reward) {
        this.rankNumber = rankNumber;
        this.criteria = criteria;
        this.reward = reward;
    }

    Rank(int rankNumber, int criteria, int reward, boolean hitBonusBall) {
        this(rankNumber, criteria, reward);
        this.hitBonusBall = hitBonusBall;
    }

    public static Rank judgeResult(Result result) {
        return Arrays.stream(Rank.values())
                .filter(result::isWhatRank)
                .findFirst()
                .orElse(NONE);
    }

<<<<<<< HEAD
    public int getHitCount() {
        return hitCount;
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
=======
    public int getRankNumber() {
        return rankNumber;
>>>>>>> a879dd3 (feat : 구매한 모든 로또의 결과를 기록하는 클래스 추가)
    }

    public int getCriteria() {
        return criteria;
    }

    public int getReward() {
        return reward;
    }

    public boolean getHitBonusBall() {
        return hitBonusBall;
    }
}
