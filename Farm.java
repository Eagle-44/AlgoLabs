package com.company;


public class Farm {
    private int angryCows = 3;
    private int[] corral = new int[]{1, 2, 4, 8, 9};
    private int rightIndex = corral[corral.length - 1];
    private int leftIndex = 1;
    private int res = 0;

    public boolean checkData(int[] corral, int value, int angryCows) {
        int counter = 1;
        int tempValue = corral[0];
        for (int i = 0; i < corral.length; i++) {
            if (corral[i] - tempValue >= value) {
                counter += 1;
                tempValue = corral[i];
                if (counter >= angryCows) {
                    return true;
                }
            }
        }
        return false;
    }

    public void minFarmsCounter() {
        while (leftIndex < rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            if (checkData(corral, middleIndex, angryCows)) {
                res = middleIndex;
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex;
            }
        }
        System.out.println(res);
    }
}







