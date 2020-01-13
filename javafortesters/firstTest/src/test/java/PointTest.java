

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
  @Test
  public void testLength(){
    Point p1 = new Point(100,50);
    Point p2 = new Point(100,50);
    Assert.assertEquals(Point.distance(p1, p2), 0);
  }
}
