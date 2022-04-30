#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>
#include "tuple.h"

static const int allTuples = 1000000; // 总元组数
static const int loadTuples = 62500; // 内存能装下的元组数
static const int ways = 16; // 数据分成几个子集合,以及待排序的元组的内存大小
static const int waitToSort = 2500; // 第二趟扫描时每个子集合的输入缓冲区(等待被排序)的元组数量
static const int waitToOutput = 20000; // 第二趟扫描时输出缓冲区(等待被输出)的元组数量
static const char inputPath[50] = "D:\\Code\\C\\database\\resources\\input.txt"; // 输入路径
static const char outputPath[50] = "D:\\Code\\C\\database\\resources\\output.txt"; // 第二趟扫描后输出的路径
static const char directoryPath[50] = "D:\\Code\\C\\database\\resources\\"; // 资源目录的路径

// 初始化数据
void generate() {
    assert(allTuples / ways <= loadTuples);
    assert(waitToOutput + ways * (waitToSort + 1) <= loadTuples);
    FILE *fw;
    if ((fw = fopen("D:\\Code\\C\\database\\resources\\input.txt", "w")) == NULL) {
        printf("Fail to open file: input!\n");
        exit(0);
    }
    Tuple *pTuple = initializeTuples(allTuples);
    writeTuples(fw, pTuple, allTuples);
    fclose(fw);
    freeTuples(pTuple, allTuples);
    printf("generate data successfully!\n");
}

// 第n个文件的路径 n从0开始
char* getFilePath(int n){
    char* sortedPath = (char *) malloc(50 * sizeof(char));
    if(sortedPath == NULL){
        printf("no more memory\n");
        exit(1);
    }
    sortedPath[0] = '\0';
    strcat(sortedPath, directoryPath);
    char tap[2];
    tap[0] = (char) (n + 65);
    tap[1] = '\0';
    strcat(sortedPath, tap);
    return sortedPath;
}

int compare(const void* a, const void* b){
    return (*(Tuple*)a).a > (*(Tuple*) b).a ? 1 : -1;
}

// 求出一个数组中的最小值的索引
int getMin(Tuple* pTuple, const bool* isEnd, int n){
    int min = -1;
    for(int i = 0; i < n; i ++){
        if(! isEnd[i]){
            min = i;
            break;
        }
    }
    // 已经读取完全部元组, 结束程序
    if(min == -1){
        return min;
    }
    for(int i = 0; i < n; i ++){
        if(pTuple[i].a < pTuple[min].a && ! isEnd[i]){
            min = i;
        }
    }
    return min;
}

// 一趟排序
void firstSort() {
    int tupleNums[ways]; // 每个子集中存储的元组数
    for (int i = 0; i < ways; i++) {
        tupleNums[i] = i < allTuples % ways ? allTuples / ways + 1 : allTuples / ways;
        printf("%d ", tupleNums[i]);
    }
    printf("\n");

    // 从输入中读取数据
    FILE *fr;
    if ((fr = fopen(inputPath, "r")) == NULL) {
        printf("Fail to open file: input!\n");
        exit(0);
    }
    for (int i = 0; i < ways; i++) {
        Tuple* pTuple = readTuples(fr, allTuples / ways); // 读入长度为每个子集的长度
        // 使用快速排序库函数
        qsort(pTuple, allTuples / ways, sizeof(Tuple), compare);
        // 向输出中写数据
        char* sortedPath = getFilePath(i);
        printf("%s\n", sortedPath);
        FILE* fw;
        if ((fw = fopen(sortedPath, "w")) == NULL) {
            printf("Fail to open file: %c!\n", i + 64);
            exit(0);
        }
        writeTuples(fw, pTuple, allTuples / ways);
        printf("the %d th has been sorted firstly\n", i + 1);
        free(sortedPath);
        freeTuples(pTuple, allTuples / ways);
        fclose(fw);
    }
    fclose(fr);
}

// 二趟排序
void secondSort() {
    Tuple** pTuple2 = createTuples2(ways); // 输入缓冲区
    FILE* fr[ways];
    for(int i = 0; i < ways; i ++){ // 向ways个文件读数据
        char inputPathX[50] = "";
        strcpy(inputPathX, directoryPath);
        char tap[2];
        tap[0] = (char) (i + 65);
        tap[1] = '\0';
        strcat(inputPathX, tap);
        if ((fr[i] = fopen(inputPathX, "r")) == NULL) {
            printf("Fail to open file: input!\n");
            exit(0);
        }
        pTuple2[i] = readTuples(fr[i], waitToSort);
    }
    // 初始化缓冲区
    int waitNum[ways]; // 每个子集下一个正在等的元素
    bool isEnd[ways]; // 每个子集是否已经读完
    Tuple sorting[ways]; // 正在排序的元素
    Tuple output[waitToOutput]; // 等待被输出的元组(输出缓冲区)
    int outputNum = 0; // 当前输出缓冲区的元素数量
    for(int i = 0; i < ways; i ++){
        isEnd[i] = false;
        sorting[i] = pTuple2[i][0]; // 把第一个元组放入排序中
        waitNum[i] = 1;
    }
    int writeCount = 0; // 输出缓冲区输出的次数
    FILE* fw;
    if ((fw = fopen(outputPath, "w")) == NULL) {
        printf("Fail to open file: output!\n");
        exit(0);
    }
    while(true){
        globalMin = getMin(sorting, isEnd, ways);
        if(globalMin == -1){
            // 所有排序结束
            // 输出缓冲区里没有多余的元素, 只要计算好[输出缓冲区大小]整除[总元组数量]
            printf("all the tuples are sorted finally, End!\n");
            // 释放资源
            for(int i = 0; i < ways; i ++){
                fclose(fr[i]);
                if(pTuple2[i] != NULL)
                    freeTuples(pTuple2[i], globalOutputNum[globalMin]);
            }
            fclose(fw);
            free(pTuple2);
            break;
        }
        output[outputNum ++] = sorting[globalMin]; // 最小值放入输出缓冲区
        sorting[globalMin] = pTuple2[globalMin][waitNum[globalMin] ++]; // 第min个等待区++
        // 如果输出缓冲区已满
        if(outputNum == globalOutputNum[globalMin]){
            writeTuples( fw, output, globalOutputNum[globalMin]);
            outputNum = 0; // 置零
            writeCount ++;
            printf("output data to disk for %d times\n", writeCount);
        }
        // 如果某一行已经输出完
        if(waitNum[globalMin] == waitToSort){
            free(pTuple2[globalMin]);
            pTuple2[globalMin] = readTuples(fr[globalMin], waitToSort);
            waitNum[globalMin] = 0; // 正在等待的元素置零
            // 输入缓冲区读完要重新赋值
            // 直接赋值, 但是要在前面计算好使[输入缓冲区的大小]整除[每个子集的大小]
            if(globalOutputNum[globalMin] < waitToOutput){
//                printf("min: %d\n", globalOutputNum[globalMin]);
                isEnd[globalMin] = true;
            }
        }
    }
}
