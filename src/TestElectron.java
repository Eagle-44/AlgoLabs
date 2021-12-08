import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestElectron {
    public Electron electron;

    @Before
    public void init() {
        electron = new Electron();
    }

    @Test
    public void firstTest() {
        Double a = 2633.1702734390583d;
        electron.setDistance(4);
        electron.setHeightOfPillar(Arrays.asList(56, 18, 17, 94, 23, 7, 21, 94, 29, 54, 44, 26, 86, 79, 4, 15, 5, 91, 25, 17, 88, 66, 28, 2, 95, 97, 60, 93, 40, 70, 75, 48, 38, 51, 34, 52, 87, 8, 62, 77, 35, 52, 3, 93, 34, 57, 51, 11, 39, 72));
        Assert.assertEquals(a, electron.countWireLength());
    }

    @Test
    public void secondTest() {
        Double a = 5.656854249492381d;
        electron.setDistance(2);
        electron.setHeightOfPillar(Arrays.asList(3, 3, 3));
        Assert.assertEquals(a, electron.countWireLength());
    }

    @Test
    public void thirdTest() {
        Double a = 396.32310051270036d;
        electron.setDistance(4);
        electron.setHeightOfPillar(Arrays.asList(100, 2, 100, 2, 100));
        Assert.assertEquals(a, electron.countWireLength());
    }

    @Test
    public void fourthTest() {
        Double a = 300.0d;
        electron.setDistance(100);
        electron.setHeightOfPillar(Arrays.asList(1, 1, 1, 1));
        Assert.assertEquals(a, electron.countWireLength());
    }
}
