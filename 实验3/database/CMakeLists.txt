cmake_minimum_required(VERSION 3.22)
project(database)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(resources)

add_executable(main main.c sort.h tuple.h)
add_executable(test test.c)
add_executable(test1 test1.c)