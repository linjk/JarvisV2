# version info
# 1. boost            1.59.0
# 2. googletest       1.10.0

cmake_minimum_required(VERSION 3.16)

project(Jarvis)

set(CMAKE_CXX_STANDARD 11)

add_compile_definitions(MYSQLPP_MYSQL_HEADERS_BURIED)

# brew install mysql++
#   mysql-client is keg-only, which means it was not symlinked into /usr/local,
#   because it conflicts with mysql (which contains client libraries).
#   If you need to have mysql-client first in your PATH run:
#   echo 'export PATH="/usr/local/opt/mysql-client/bin:$PATH"' >> ~/.zshrc
#   For compilers to find mysql-client you may need to set:
#   export LDFLAGS="-L/usr/local/opt/mysql-client/lib"
#   export CPPFLAGS="-I/usr/local/opt/mysql-client/include"
#   For pkg-config to find mysql-client you may need to set:
#   export PKG_CONFIG_PATH="/usr/local/opt/mysql-client/lib/pkgconfig"
include_directories(
        "/usr/local/opt/mysql++/include"
        "/usr/local/opt/mysql-client/include"
        "/usr/local/include")

link_directories(
        "/usr/local/opt/mysql++/lib"
        "/usr/local/opt/mysql-client/lib"
        "/usr/local/lib/")

link_libraries(
        "mysqlclient"
        "gtest"
        "boost_date_time"
        "boost_system")

add_executable(
        Jarvis main.cpp
        utils/mysql_connector.hpp
        utils/car_oil_util.hpp
        entity/car_oil.h
        commons/jarvis.h
        utils/json_util.hpp utils/mqtt_util.hpp)

target_link_libraries(Jarvis mysqlclient mysqlpp)