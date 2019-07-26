package ru.atom.geometry;

public class Bar implements Collider {
    public Point topLeftCorner;
    public Point topRightCorner;
    public Point bottomLeftCorner;
    public Point bottomRightCorner;

    public Bar(int x1, int y1, int x2, int y2) {
        this.topLeftCorner = new Point(Math.min(x1, x2), Math.max(y1, y2));
        this.topRightCorner = new Point(Math.max(x1, x2), Math.max(y1, y2));
        this.bottomLeftCorner = new Point(Math.min(x1, x2), Math.min(y1, y2));
        this.bottomRightCorner = new Point(Math.max(x1, x2), Math.min(y1, y2));
    }

    public boolean contain(Point point) {
        boolean pointIsBetweenCornersOnX = point.x >= this.bottomLeftCorner.x && point.x <= this.bottomRightCorner.x;
        boolean pointIsBetweenCornersOnY = point.y >= this.bottomLeftCorner.y && point.y <= this.topLeftCorner.y;

        return pointIsBetweenCornersOnX && pointIsBetweenCornersOnY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Bar
        Bar bar = (Bar) o;

        return (bar.topLeftCorner.equals(this.topLeftCorner) && bar.bottomRightCorner.equals(this.bottomRightCorner));
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            return this.contain((Point) other);
        }

        if (other instanceof Bar) {
            Bar bar = (Bar) other;

            if (this.equals(bar)) {
                return true;
            }

            if (
                    this.topLeftCorner.x > bar.topRightCorner.x
                            || this.topRightCorner.x < bar.topLeftCorner.x
                            || this.topRightCorner.y < bar.bottomRightCorner.y
                            || this.bottomRightCorner.y > bar.topRightCorner.x
            ) {
                return false;
            }

            return true;
        }

        return false;
    }
}
