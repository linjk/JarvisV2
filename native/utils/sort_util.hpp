//
// Created by linjk on 2022/4/10.
//

#ifndef JARVIS_SORT_UTIL_H
#define JARVIS_SORT_UTIL_H

#include "../commons/jarvis.h"
#include <cassert>

template<typename T>
class sort_util {
public:
    /**
     * 选择排序，复杂度：O(n^2)
     * @param arr 数组
     * @param n 数组大小
     */
    static void selectionSort(T arr[], int n) {
        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间里的最小值
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr[i], arr[minIndex]);
        }
    }

    static T* generateRandomArray(int num, int range_left, int range_right) {
        assert(range_left <= range_right);

        int *arr = new int[num];
        // 随机种子设置
        srand(time(NULL));
        for (int i  = 0; i < num; i++) {
            arr[i] = rand() % (range_right - range_left + 1) + range_left;
        }
        return arr;
    }

    static void print_arr(const T a[], int num) {
        for(int i = 0; i < num; i++) {
            cout << a[i] << " ";
        }
        cout << endl;
    }

    /**
     * 排序算法性能测试函数
     * @param sort_name 排序名称
     * @param sort 用于排序的函数指针
     * @param arr 需要排序的数组
     * @param num 需要排序的数组的数据个数
     */
    static void benchmark(string sort_name, void(* sort)(T[], int), T arr[], int num) {
        clock_t start = clock();
        sort(arr, num);
        clock_t end = clock();
        cout << "SortName: " << sort_name << ", cost " << double(end-start)/CLOCKS_PER_SEC << "s" << endl;
        // make sure you test is PASS
        for(int i  = 0; i < num-1; i++) {
            if (arr[i] > arr[i+1]) {
                cerr << sort_name << " FAILED" << endl;
            }
        }
    }
};

TEST(sort_util, sort_util_test) { /* NOLINT */
    cout << "Test: sort_util_test start" << endl;
    int arr_size = 1000;
    cout << "arr_size: " << arr_size << endl;
    int* a = sort_util<int>::generateRandomArray(arr_size, 0, arr_size);
    sort_util<int>::benchmark("选择排序", sort_util<int>::selectionSort, a, arr_size);
    delete[] a;
    cout << "Test: sort_util_test end" << endl;
}

#endif //JARVIS_SORT_UTIL_H
