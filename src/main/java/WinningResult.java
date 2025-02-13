import java.util.EnumMap;

public class WinningResult {
    private final EnumMap<WinningInfo, Integer> result = new EnumMap<>(WinningInfo.class);

    public WinningResult() {
        for (WinningInfo winningInfo : WinningInfo.values()) {
            result.put(winningInfo, 0);
        }
    }

    public void increaseCount(WinningInfo winningInfo, final int count) {
        result.put(winningInfo, result.get(winningInfo) + count);
    }

    public int getCount(WinningInfo winningInfo) {
        return result.get(winningInfo);
    }
}
