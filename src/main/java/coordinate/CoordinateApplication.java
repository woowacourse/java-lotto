package coordinate;

import coordinate.domain.figure.FigureFactory;
import coordinate.domain.Point;
import coordinate.exception.InvalidFigureException;
import coordinate.exception.InvalidPointException;
import coordinate.utils.PointParser;
import coordinate.view.InputView;

import java.util.List;

public class CoordinateApplication {
    public static void main(String[] args) {
        try {
            String value = InputView.getPoint();
            List<Point> points = PointParser.parse(value);
            FigureFactory.getFigure(points);
        } catch (InvalidPointException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } catch (InvalidFigureException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
