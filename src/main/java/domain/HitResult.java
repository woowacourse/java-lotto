package domain;

import java.util.EnumMap;

public class HitResult {
    private EnumMap<Rank, Integer> hitResult = new EnumMap<Rank, Integer>(Rank.class);

    public HitResult() {
        for (Rank rank : Rank.values()) {
            hitResult.put(rank, 0);
        }
    }

    public void increase(Rank rank) {
        hitResult.put(rank, hitResult.get(rank) + 1);
    }

    public Integer get(Rank value) {
        return hitResult.get(value);
    }
}