import java.util.Arrays;
import java.util.Scanner;


public class Assignment2 {

    public static double[][] mergeSort(double[][] arr, int index) {
        if(arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        double[][] left = mergeSort(Arrays.copyOfRange(arr, 0, mid), index);
        double[][] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length), index);

        return merge(left, right, index);
    }

    public static double[][] merge(double[][] left, double[][] right, int index) {
        double[][] mergedArr = new double[left.length + right.length][3];
        int i = 0, j = 0;
        while(i < left.length && j < right.length) {
            if(left[i][index] > right[j][index]) {
                mergedArr[i + j] = right[j];
                j++;
            }
            else {
                mergedArr[i + j] = left[i];
                i++;
            }
        }
        while(i < left.length) {
            mergedArr[i + j] = left[i];
            i++;
        }
        while(j < right.length) {
            mergedArr[i + j] = right[j];
            j++;
        }
        return mergedArr;
    }

    public static double distance(double[] point1, double[]point2) {
        return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
    }

    public static double[] findClosestPair(double[][] arr) {
        if(arr.length == 2) {
            return new double[] {arr[0][2], arr[1][2], distance(arr[0], arr[1])};
        }
        if(arr.length == 3) {
            double dist01 = distance(arr[0], arr[1]);
            double dist12 = distance(arr[1], arr[2]);
            double dist02 = distance(arr[0], arr[2]);
            if(dist01 <= dist12 && dist01 <= dist02) {
                return new double[] {arr[0][2], arr[1][2], dist01};
            }
            else if(dist12 <= dist01 && dist12 <= dist02) {
                return new double[] {arr[1][2], arr[2][2], dist12};
            }
            else {
                return new double[] {arr[0][2], arr[2][2], dist02};
            }
        }

        int mid = arr.length / 2;
        double[] left = findClosestPair(Arrays.copyOfRange(arr, 0, mid));
        double[] right = findClosestPair(Arrays.copyOfRange(arr, mid, arr.length));
        double[] minValues;

        if(left[2] <= right[2]) {
            minValues = left;
        }
        else {
            minValues = right;
        }

        double minDist = minValues[2];
        double midXIndex = arr[mid][0];
        double midYIndex = arr[mid][1];
        int i = mid, j = mid;
        while(i > 0 && (Math.abs(midXIndex - arr[i][0]) < minDist)) {
            i--;
        }
        while(j < arr.length - 1 && (Math.abs(midXIndex - arr[j][0]) < minDist)) {
            j++;
        }
        double[][] validXStrip = Arrays.copyOfRange(arr, i, j + 1);
        validXStrip = mergeSort(validXStrip, 1);

        for(int k = 0; k < validXStrip.length; k++) {
            for(int l = k + 1; l < validXStrip.length; l++) {
                double dist = distance(validXStrip[k], validXStrip[l]);
                if(dist < minDist) {
                    minValues = new double[] {validXStrip[k][2], validXStrip[l][2], dist};
                    minDist = dist;
                }
            }
        }
        return minValues;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfAlumni = sc.nextInt();
        double[][] alumniCoordinates = new double[numOfAlumni][3];
        for (int i = 0; i < numOfAlumni; i++) {
            alumniCoordinates[i][0] = sc.nextDouble();
            alumniCoordinates[i][1] = sc.nextDouble();
            alumniCoordinates[i][2] = (double) i;
        }

        alumniCoordinates = mergeSort(alumniCoordinates, 0);
        double[] minValues = findClosestPair(alumniCoordinates);
        System.out.printf("%.0f %.0f %.6f\n", minValues[0] + 1, minValues[1] + 1, minValues[2]);
    }
}