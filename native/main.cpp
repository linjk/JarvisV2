//
// Created by linjk on 2020/9/27.
//

#include "commons/jarvis.h"
//#include "utils/mysql_connector.hpp"
//#include "utils/car_oil_util.hpp"
#include "utils/json_util.hpp"
//#include "utils/mqtt_util.hpp"

int main(int argc, char* argv[]) {
    system_init();
    int input;
    usage();
    cin >> input;
    while (input != 0) {
        switch (input) {
            case 1:
                cout << "N/A." << endl;
                break;
            default:
                usage();
                break;
        }
        cout << "Number to continue or any characters to exit" << endl;
        usage();
        cin >> input;
    }
    usage();
    return 0;
}

void system_init() {
    cout << "System init ..." << endl;
    cout << " -> opencv version : " << CV_VERSION << endl;
    cout << "checking system ..." << endl;
    testing::InitGoogleTest();
    int test_result = RUN_ALL_TESTS();
    if (test_result != 0) {
        cerr << "System check FAILED" << endl;
        exit(-1);
    }
    cout << "Welcome to Jarvis !" << endl;
}

void usage() {
    cout << "-----------------------------------" << endl;
    cout << "Usage:" << endl;
    cout << "1. Run gTest." << endl;
    cout << "2. Exit system." << endl;
    cout << "-----------------------------------" << endl;
}
