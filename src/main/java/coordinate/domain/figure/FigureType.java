package coordinate.domain.figure;

import coordinate.exception.InvalidFigureException;

import java.util.Arrays;

public enum FigureType {
    LINE(2),
    TRIANGLE(3),
    RECTANGLE(4);

    private int size;

    FigureType(int size) {
        this.size = size;
    }

    private boolean matchFigure(int size) {
        return this.size == size;
    }

    public static FigureType findFigure(int size) throws InvalidFigureException {
        return Arrays.stream(FigureType.values())
                .filter(figureType -> figureType.matchFigure(size))
                .findFirst()
                .orElseThrow(() -> new InvalidFigureException("유효하지 않은 도형입니다."));
    }
}
