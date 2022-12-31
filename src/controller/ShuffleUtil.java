package controller;

import java.util.Random;

import model.Brand;


public class ShuffleUtil {
    private static void swap(Brand[] arr, int i, int j) {
        Brand tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * shuffle方法能将参数数组arr随机重排
     * shuffle的基本思路是什么呢？从后往前，逐个给每个数组位置重新赋值，值是从剩下的元素中随机挑选的。
     * swap(arr, i-1, rnd.nextInt(i));i-1表示当前要赋值的位置，rnd.nextInt（i）表示从剩下的元素中随机挑选。
     */
    public static void shuffle(Brand[] arr, int n) {
        Random rnd = new Random();
        for (int i = n; i > 1; i--) {
            swap(arr, i - 1, rnd.nextInt(i));
        }
    }
}
