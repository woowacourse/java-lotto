import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinningResult {
    private final Map<WinningStatus, Integer> winningResults = new LinkedHashMap<>();

    public WinningResult() {
        for (WinningStatus winningStatus : WinningStatus.values()) {
            winningResults.put(winningStatus, 0);
        }
    }

    public void update(WinningStatus winningStatus) {
        winningResults.replace(winningStatus, winningResults.get(winningStatus) + 1);
    }

    public Map<WinningStatus, Integer> getWinningResults() {
        return Collections.unmodifiableMap(winningResults);
    }
}
