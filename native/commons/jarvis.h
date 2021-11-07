//
// Created by linjk on 2020/12/26.
//

#ifndef JARVIS_JARVIS_H
#define JARVIS_JARVIS_H

#include <iostream>
#include <vector>
#include <utility>

#include <opencv2/opencv.hpp>

#include <boost/assert.hpp>
#include <boost/date_time/gregorian/gregorian.hpp>
#include <boost/smart_ptr.hpp>

#include <gtest/gtest.h>

using namespace std;
using namespace boost;
using namespace boost::gregorian;

void print_system_info();
void printUsage();

#endif //JARVIS_JARVIS_H
