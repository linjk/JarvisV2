//
// Created by linjk on 2020/9/27.
//

#include "commons/jarvis.h"
#include "utils/mysql_connector.hpp"
#include "utils/car_oil_util.hpp"
#include "utils/json_util.hpp"
#include "utils/sort_util.hpp"
//#include "utils/mqtt_util.hpp"

void bayesi_filter();

int main(int argc, char* argv[]) {
    //system_init();
    //int input;
    //usage();
    bayesi_filter();
    // cin >> input;
    // while (input != 0) {
    //     switch (input) {
    //         case 1:
    //             cout << "N/A." << endl;
    //             break;
    //         default:
    //             usage();
    //             break;
    //     }
    //     cout << "Number to continue or any characters to exit" << endl;
    //     usage();
    //     cin >> input;
    // }
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

using namespace cv;
using namespace cv::ml;
void bayesi_filter() {
    //创建正态贝叶斯分类器
    Ptr<NormalBayesClassifier> model = NormalBayesClassifier::create();
    //创建训练样本
    float trainingData[8][3] =
    {
        { 6,    180, 12 }, // 1
        { 5.92, 190, 11 },
        { 5.58, 170, 12 },
        { 5.92, 165, 10 },
        { 5,    100, 6 },
        { 5.5,  150, 8 },
        { 5.42, 130, 7 },
        { 5.75, 150, 9 }   // 8
    };
    Mat trainingDataMat(8, 3, CV_32FC1, trainingData);
    cout << trainingDataMat << "\r\n" << endl;
    
    //1代表M  -1代表F    
    int labels[8] = { 1, 1, 1, 1, -1, -1, -1, -1 };
    Mat labelsMat(8, 1, CV_32SC1, labels);
    cout << labelsMat << endl;
    
    //创建TrainData并进行训练
    Ptr<TrainData> tData = TrainData::create(trainingDataMat, ROW_SAMPLE, labelsMat);
    model->train(tData);
 
    float myData[3] = { 6, 130, 8 };        //测试样本
    Mat myDataMat(1, 3, CV_32FC1, myData);    //
    int res = model->predict(myDataMat);    //利用训练好的分类器进行测试样本预测
 
    cout << endl << "The result is :  " << res << endl;
}