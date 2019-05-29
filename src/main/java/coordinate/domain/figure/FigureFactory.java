package coordinate.domain.figure;

import coordinate.domain.Point;
import coordinate.exception.InvalidFigureException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FigureFactory {
    private static Map<FigureType, FigureCreator> creators = new HashMap<>();

    static {
        creators.put(FigureType.LINE, Line::new);
        creators.put(FigureType.TRIANGLE, new TriangleCreator(){
            @Override
            public Figure create(List<Point> points) {
                return new Triangle(points);
            }
        });
        creators.put(FigureType.RECTANGLE, Rectangle::new);
    }

    public static Figure getFigure(List<Point> points) throws InvalidFigureException {
        FigureCreator figureCreator = creators.get(FigureType.findFigure(points.size()));
        if (figureCreator == null) {
            throw new InvalidFigureException("유효하지 않은 도형입니다.");
        }
        return figureCreator.create(points);
    }
}

class LineCreator implements FigureCreator {
    @Override
    public Figure create(List<Point> points) {
        return new Line(points);
    }
}

class TriangleCreator implements FigureCreator {
    @Override
    public Figure create(List<Point> points) {
        return new Triangle(points);
    }
}

class RectangleCreator implements FigureCreator {
    @Override
    public Figure create(List<Point> points) {
        return new Rectangle(points);
    }
}