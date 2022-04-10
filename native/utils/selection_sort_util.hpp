//
// Created by linjk on 2022/4/10.
//

#ifndef JARVIS_SELECTION_SORT_UTIL_H
#define JARVIS_SELECTION_SORT_UTIL_H

#include "../commons/jarvis.h"

template<typename T>
class selection_sort_util {
public:
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
};

TEST(selection_sort_util, selection_sort_util_test) { /* NOLINT */
    cout << "Test: selection_sort_util_test start" << endl;
    int a[10] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    selection_sort_util<int>::selectionSort(a, 10);
    for(int i : a) {
        cout << i << " ";
    }
    cout << endl;
    float b[5] = {1.2, 3.4, 0.8, 6.9, 11.23};
    selection_sort_util<float>::selectionSort(b, 5);
    for(float i : b) {
        cout << i << " ";
    }
    cout << endl;
    cout << "Test: selection_sort_util_test end" << endl;
}

#endif //JARVIS_SELECTION_SORT_UTIL_H
