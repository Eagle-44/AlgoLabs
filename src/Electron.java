import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Electron {
    private int distance;
    private List<Integer> heightOfPillar;

    public Electron() {
        this.distance = 0;
        this.heightOfPillar = new ArrayList<>();
    }

    public static void main(String[] args) {
        Electron electron = new Electron();
        electron.setDistance(4);
        electron.setHeightOfPillar(Arrays.asList(56, 18, 17, 94, 23, 7, 21, 94, 29, 54, 44, 26, 86, 79, 4, 15, 5, 91, 25, 17, 88, 66, 28, 2, 95, 97, 60, 93, 40, 70, 75, 48, 38, 51, 34, 52, 87, 8, 62, 77, 35, 52, 3, 93, 34, 57, 51, 11, 39, 72));
        System.out.println(electron.countWireLength());
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Integer> getHeightOfPillar() {
        return heightOfPillar;
    }

    public void setHeightOfPillar(List<Integer> heightOfPillar) {
        this.heightOfPillar = heightOfPillar;
    }

    public Double countWireLength() {
        double result = 0;

        for (int current = 1; current < heightOfPillar.size(); current++) {
            if (current + 1 >= heightOfPillar.size()) {
                if (heightOfPillar.get(current) > heightOfPillar.get(current - 1)) {
                    heightOfPillar.set(current - 1, 1);
                    break;
                }
                if (heightOfPillar.get(current) < heightOfPillar.get(current - 1)) {
                    heightOfPillar.set(current, 1);
                    break;
                }
                break;
            }

            if (heightOfPillar.get(current - 1) <= heightOfPillar.get(current) && heightOfPillar.get(current + 1) <= heightOfPillar.get(current)) {
                heightOfPillar.set(current - 1, 1);
                heightOfPillar.set(current + 1, 1);
            } else if (heightOfPillar.get(current - 1) > heightOfPillar.get(current)) {
                heightOfPillar.set(current, 1);
            } else if (heightOfPillar.get(current + 1) > heightOfPillar.get(current) && heightOfPillar.get(current - 1) != 1) {
                heightOfPillar.set(current, 1);
            }

        }

//        for (int current = 1; current < heightOfPillar.size(); current++) {
//            int difference = heightOfPillar.get(current) - heightOfPillar.get(current - 1);
//            double calcValue = Math.sqrt(Math.pow(distance, 2) + Math.pow(difference, 2));
//            result += calcValue;
//        }

        for (int current = 1; current < heightOfPillar.size(); current++) {
            int difference = heightOfPillar.get(current) - heightOfPillar.get(current - 1);
            double calcValue = Math.sqrt(Math.pow(distance, 2) + Math.pow(difference, 2)) + 1;
            result += calcValue;
            System.out.println(calcValue);
        }
        result+=heightOfPillar.get(0);

        return result;
    }
}

