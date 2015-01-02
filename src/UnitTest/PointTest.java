package UnitTest;

import Util.Point;
import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void testToString() throws Exception {
        Point pt = new Point (5, 6);
        assertEquals("(5, 6)", pt.toString());
    }

    public void testEqualsTo() throws Exception {
        Point pt1 = new Point (5, 6);
        Point pt2 = new Point (1, 2);
        assertEquals(false, pt1.equals(pt2));

        pt2.setLocation(5, 6);
        assertEquals(true, pt1.equals(pt2));
    }
}