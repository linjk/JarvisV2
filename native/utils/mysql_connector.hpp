//
// Created by linjk on 2020/9/27.
// 数据库读写工具

#ifndef JARVIS_MYSQL_CONNECTOR_HPP
#define JARVIS_MYSQL_CONNECTOR_HPP

#include "../commons/jarvis.h"
#include <mysql++/mysql++.h>

using namespace mysqlpp;

class mysql_connector {
public:
    explicit mysql_connector(string host = "127.0.0.1", int port = 3306, string account = "root", string password = "root", string database = "jarvis")
        : host(std::move(host)), port(port), account(std::move(account)), password(std::move(password)), database(std::move(database)) {}

    bool connect();
    void connectionInfo();
    void disconnect();
    vector<Row> query(const string& sql);

private:
    Connection connection;
    string host;
    int port;
    string account;
    string password;
    string database;
};

bool mysql_connector::connect() {
    if (!host.empty()) {
        try {
            connection =  Connection(true);
            return connection.connect(database.c_str(), host.c_str(), account.c_str(), password.c_str(), port);
        }
        catch (const Exception &er) {
            cerr << "#connect# Error: " << er.what() << endl;
            return false;
        }
    }
    cerr << "host is empty." << endl;
    return false;
}

void mysql_connector::connectionInfo() {
    if (connection.connected()) {
        cout << "connect to mysql: " << host << ":" << port << ", db: " << database << endl;
        cout << "mysql version: " << connection.server_version() << endl;
        cout << "mysql running status: " << connection.server_status() << endl;
    }
    else {
        cerr << "Are you already start MYSQL server or you ar offline ?" << endl;
    }
}

void mysql_connector::disconnect() {
    try {
        if (connection.connected()) {
            connection.disconnect();
        }
    }
    catch (const Exception &er) {
        cerr << "#disconnect# Error: " << er.what() << endl;
    }
}

vector<Row> mysql_connector::query(const string& sql) {
    if (connection.connected()) {
        try {
            vector<Row> v;
            Query query = connection.query(sql);
            StoreQueryResult storeQueryResult = query.store();
            if (storeQueryResult) {
                StoreQueryResult::const_iterator it;
                for (it = storeQueryResult.begin(); it != storeQueryResult.end(); ++it) {
                    v.push_back(*it);
                }
            }
            if (connection.errnum()) {
                cerr << "Error in fetch_row";
            }
            return v;
        }
        catch (BadQuery &er){
            cout << "#query# Error:" << er.what() << endl;
        }
    }
    return vector<Row>();
}

TEST(mysql_connector, mysql_connector_test) { /* NOLINT */
    char *account, *pwd;
    account = getenv("MYSQL_ACCOUNT");
    pwd = getenv("MYSQL_PASSWORD");
    mysql_connector mysqlConnector("127.0.0.1", 3308, account, pwd);

    if (mysqlConnector.connect()) {
        cout << "connect to mysql success." << endl;
        mysqlConnector.connectionInfo();
        vector<Row> rows = mysqlConnector.query("select now() as now;");
        for (int i = 0; i < rows.size(); i++) {
            cout << "------> " << rows.at(i)["now"] << endl;
        }
        mysqlConnector.disconnect();
        SUCCEED();
    }
    else {
        cerr << "connect to mysql failed." << endl;
        FAIL();
    }
}

#endif //JARVIS_MYSQL_CONNECTOR_HPP
