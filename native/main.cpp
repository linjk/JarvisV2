//
// Created by linjk on 2020/9/27.
//

#include "commons/jarvis.h"
//#include "utils/mysql_connector.hpp"
//#include "utils/car_oil_util.hpp"
#include "utils/json_util.hpp"
//#include "utils/mqtt_util.hpp"

int main(int argc, char* argv[]) {
    if (2 == argc) {
        int selection;
        istringstream params(argv[1]);
        if (!(params >> selection)) {
            cerr << "Invalid input selection 「" << argv[1] << "」" << endl;
            printUsage();
        }

        switch (selection) {
            case 1:
                testing::InitGoogleTest();
                return RUN_ALL_TESTS();
            case 2:
                cout << "N/A." << endl;
                break;
            default:
                printUsage();
                break;
        }
    }
    printUsage();
    return 0;
}

void printUsage() {
    cout << "-----------------------------------" << endl;
    cout << "version info: " << endl;
    cout << "- OpenCV: " << CV_VERSION << endl;
    cout << "-----------------------------------" << endl;
    cout << "Usage:" << endl;
    cout << "1. Run gTest." << endl;
    cout << "-----------------------------------" << endl;
}
