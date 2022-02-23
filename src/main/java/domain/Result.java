package domain;

import java.util.Objects;

public class Result {
    private final int hitCount;
    private final boolean isHitBonusBall;

    public Result(int hitCount, boolean isHitBonusBall){
        this.hitCount = hitCount;
        this.isHitBonusBall = isHitBonusBall;
    }

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
    }
}
