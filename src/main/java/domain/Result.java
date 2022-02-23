package domain;

<<<<<<< HEAD
public class Result {

    private static final int HIT_FIVE_RANK = 5;

    private final int hitCount;
    private final boolean isHitBonusBall;

    public Result(int hitCount, boolean isHitBonusBall) {
=======
import java.util.Objects;

public class Result {
    private final int hitCount;
    private final boolean isHitBonusBall;

    public Result(int hitCount, boolean isHitBonusBall){
>>>>>>> ed345ef (feat: 보너스볼 판정 로직 및 Result 객체 구현)
        this.hitCount = hitCount;
        this.isHitBonusBall = isHitBonusBall;
    }

<<<<<<< HEAD
    public boolean isWhatRank(Rank rank) {
        if (rank.getCriteria() == HIT_FIVE_RANK) {
            return this.hitCount == rank.getCriteria() && this.isHitBonusBall == rank.getHitBonusBall();
        }
        return this.hitCount == rank.getCriteria();
=======
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return hitCount == result.hitCount && isHitBonusBall == result.isHitBonusBall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitCount, isHitBonusBall);
>>>>>>> ed345ef (feat: 보너스볼 판정 로직 및 Result 객체 구현)
    }
}
