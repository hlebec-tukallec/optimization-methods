public interface Method {
    void findMinimum();

    default Point countNewPoint(Point p, Point grad, double lambda) {
        return new Point(p.x - lambda * grad.x, p.y - lambda * grad.y);
    }

    default double getMod(Point p) {
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }
}
