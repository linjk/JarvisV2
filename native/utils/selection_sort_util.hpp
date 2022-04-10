//
// Created by linjk on 2022/4/10.
//

#ifndef JARVIS_SELECTION_SORT_UTIL_H
#define JARVIS_SELECTION_SORT_UTIL_H

#include "../commons/jarvis.h"

class selection_sort_util {
public:
    static void selectionSort(int arr[], int n) {
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
    selection_sort_util::selectionSort(a, 10);
    for(int i : a) {
        cout << i << " ";
    }
    cout << endl;
    cout << "Test: selection_sort_util_test end" << endl;
}

#endif //JARVIS_SELECTION_SORT_UTIL_H
