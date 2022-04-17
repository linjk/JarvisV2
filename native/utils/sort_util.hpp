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
    /**
     * 插入排序，复杂度：O(n^2)
     * @param arr 数组
     * @param n 数组大小
     */
    static void insertionSort(T arr[], int n) {
        for (int i = 1; i < n; i++) {
            T temp = arr[i];
            // j用于存放temp元素应该插入的位置
            int j;
            // 寻找元素arr[i]合适的插入位置
            for (j = i; j > 0 && arr[j-1] > temp; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }

    /**
     * 归并排序，复杂度：O(N*logN)
     * @param arr 数组
     * @param n 数组大小
     */
    static void mergeSort(T arr[], int n) {
        m_merge_sort(arr, 0, n-1);
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

    static T* generateNearlyOrderArray(int num, int swap_times) {
        // 按序生成数组
        int *arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = i;
        }
        // 随机种子设置
        srand(time(NULL));
        // 随机打乱，但整体近似有序
        for (int i  = 0; i < swap_times; i++) {
            int x = rand() % num;
            int y = rand() % num;
            swap(arr[x], arr[y]);
        }
        return arr;
    }

    static void print_arr(const T a[], int num) {
        for(int i = 0; i < num; i++) {
            cout << a[i] << " ";
        }
        cout << endl;
    }

    static T* copy_arr(const T a[], int num) {
        int* new_arr = new T[num];
        copy(a, a+num, new_arr);
        return new_arr;
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
//        for(int i  = 0; i < num-1; i++) {
//            if (arr[i] > arr[i+1]) {
//                cerr << sort_name << " FAILED" << endl;
//            }
//        }
    }

private:
    // 对arr[start...end]的范围进行排序
    static void m_merge_sort(int arr[], int start, int end) {
        if (start >= end) {
            return;
        }

        int middle_position = (start + end) / 2;
        m_merge_sort(arr, start, middle_position);
        m_merge_sort(arr, middle_position+1, end);
        m_merge(arr, start, middle_position, end);
    }

    // 对arr[start...middle_position]和arr[middle_position+1...end]两部分数组进行归并
    static void m_merge(int arr[], int start, int middle_position, int end) {
        T temp[end-start+1];
        for (int i = start; i <= end; i++) {
            temp[i-start] = arr[i];
        }

        int i = start;
        int j = middle_position + 1;
        for (int k = start; k <= end; k++) {
            if (i > middle_position) {
                arr[k] = temp[j-start];
                j++;
            }
            else if (j > end) {
                arr[k] = temp[i-start];
                i++;
            }
            else if (temp[i-start] < temp[j-start]) {
                arr[k] = temp[i-start];
                i++;
            }
            else {
                arr[k] = temp[j-start];
                j++;
            }
        }
    }
};

TEST(sort_util, sort_util_test) { /* NOLINT */
    cout << "Test: sort_util_test start" << endl;
    int arr_size = 1000;
    cout << "arr_size: " << arr_size << endl;
    int* a = sort_util<int>::generateRandomArray(arr_size, 0, arr_size);
    int* b = sort_util<int>::copy_arr(a, arr_size);
    int* c = sort_util<int>::copy_arr(a, arr_size);

    sort_util<int>::benchmark("选择排序", sort_util<int>::selectionSort, a, arr_size);
    sort_util<int>::benchmark("插入排序", sort_util<int>::insertionSort, b, arr_size);
    sort_util<int>::benchmark("归并排序", sort_util<int>::mergeSort, c, arr_size);
    sort_util<int>::print_arr(a, arr_size);
    sort_util<int>::print_arr(b, arr_size);
    sort_util<int>::print_arr(c, arr_size);

    delete[] a;
    delete[] b;
    delete[] c;
    cout << "Test: sort_util_test end" << endl;
}

#endif //JARVIS_SORT_UTIL_H
