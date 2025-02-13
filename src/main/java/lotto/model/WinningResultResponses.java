package lotto.model;

import java.util.List;

public class WinningResultResponses {

    private final List<WinningResultResponse> responses;

    public WinningResultResponses(List<WinningResultResponse> responses) {
        this.responses = responses;
    }

    public List<WinningResultResponse> getResponses() {
        return responses;
    }

}
