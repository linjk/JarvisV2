//
// Created by linjk on 2020/11/24.

#ifndef JARVIS_CAR_OIL_UTIL_HPP
#define JARVIS_CAR_OIL_UTIL_HPP

#include "../commons/jarvis.h"
#include "../entity/car_oil.h"
#include "mysql_connector.hpp"

class car_oil_util {
public:
    explicit car_oil_util() {
        connectMySQL();
    }

    ~car_oil_util() {
        cout << "disconnect mysql." << endl;
        sql_connector.disconnect();
    }

    vector<car_oil> getAll();
private:
    mysql_connector sql_connector;

    void connectMySQL();
};

vector<car_oil> car_oil_util::getAll() {
    vector<car_oil> oil_records;
    try {
        vector<Row> results = sql_connector.query("select * from car_oil limit 2");
        for (int i = 0; i < results.size(); i++) {
            car_oil oilRecord;
            oilRecord.setOilSummary(results.at(i)["oil_summary"]);
            oilRecord.setOilDate(from_string(string(results.at(i)["oil_date"])));
            oil_records.push_back(oilRecord);
        }
    }
    catch (const BadConversion &er){
        cout << "Conversion error: " << er.what() << endl <<
             "\tretrieved data size: " << er.retrieved <<
             ", actual size: " << er.actual_size << endl;
    }
    return oil_records;
}

void car_oil_util::connectMySQL() {
    char *account, *pwd;
    account = getenv("MYSQL_ACCOUNT");
    pwd = getenv("MYSQL_PASSWORD");
    sql_connector = mysql_connector("127.0.0.1", 3308, account, pwd);
    sql_connector.connect();
}

TEST(CarOilRecord, CarOilRecordInit) { /* NOLINT */
    car_oil_util carOilRecord;

    vector<car_oil> r = carOilRecord.getAll();
    for (int i = 0; i < r.size(); ++i) {
        cout << "加油日期: " << to_iso_extended_string(r.at(i).getOilDate()) << ", 加油金额: " << r.at(i).getOilSummary() << endl;
    }
    car_oil c1 = r.at(0);
    car_oil c2 = r.at(1);
    c1 += c2;
    cout << c1.getOilCnt() << ", " << c1.getOilSummary() << endl;

    SUCCEED();
}

#endif //JARVIS_CAR_OIL_UTIL_HPP
