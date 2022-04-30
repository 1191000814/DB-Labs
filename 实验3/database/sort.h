#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>
#include "tuple.h"

static const int allTuples = 1000000; // ��Ԫ����
static const int loadTuples = 62500; // �ڴ���װ�µ�Ԫ����
static const int ways = 16; // ���ݷֳɼ����Ӽ���,�Լ��������Ԫ����ڴ��С
static const int waitToSort = 2500; // �ڶ���ɨ��ʱÿ���Ӽ��ϵ����뻺����(�ȴ�������)��Ԫ������
static const int waitToOutput = 20000; // �ڶ���ɨ��ʱ���������(�ȴ������)��Ԫ������
static const char inputPath[50] = "D:\\Code\\C\\database\\resources\\input.txt"; // ����·��
static const char outputPath[50] = "D:\\Code\\C\\database\\resources\\output.txt"; // �ڶ���ɨ��������·��
static const char directoryPath[50] = "D:\\Code\\C\\database\\resources\\"; // ��ԴĿ¼��·��

// ��ʼ������
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

// ��n���ļ���·�� n��0��ʼ
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

// ���һ�������е���Сֵ������
int getMin(Tuple* pTuple, const bool* isEnd, int n){
    int min = -1;
    for(int i = 0; i < n; i ++){
        if(! isEnd[i]){
            min = i;
            break;
        }
    }
    // �Ѿ���ȡ��ȫ��Ԫ��, ��������
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

// һ������
void firstSort() {
    int tupleNums[ways]; // ÿ���Ӽ��д洢��Ԫ����
    for (int i = 0; i < ways; i++) {
        tupleNums[i] = i < allTuples % ways ? allTuples / ways + 1 : allTuples / ways;
        printf("%d ", tupleNums[i]);
    }
    printf("\n");

    // �������ж�ȡ����
    FILE *fr;
    if ((fr = fopen(inputPath, "r")) == NULL) {
        printf("Fail to open file: input!\n");
        exit(0);
    }
    for (int i = 0; i < ways; i++) {
        Tuple* pTuple = readTuples(fr, allTuples / ways); // ���볤��Ϊÿ���Ӽ��ĳ���
        // ʹ�ÿ�������⺯��
        qsort(pTuple, allTuples / ways, sizeof(Tuple), compare);
        // �������д����
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

// ��������
void secondSort() {
    Tuple** pTuple2 = createTuples2(ways); // ���뻺����
    FILE* fr[ways];
    for(int i = 0; i < ways; i ++){ // ��ways���ļ�������
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
    // ��ʼ��������
    int waitNum[ways]; // ÿ���Ӽ���һ�����ڵȵ�Ԫ��
    bool isEnd[ways]; // ÿ���Ӽ��Ƿ��Ѿ�����
    Tuple sorting[ways]; // ���������Ԫ��
    Tuple output[waitToOutput]; // �ȴ��������Ԫ��(���������)
    int outputNum = 0; // ��ǰ�����������Ԫ������
    for(int i = 0; i < ways; i ++){
        isEnd[i] = false;
        sorting[i] = pTuple2[i][0]; // �ѵ�һ��Ԫ�����������
        waitNum[i] = 1;
    }
    int writeCount = 0; // �������������Ĵ���
    FILE* fw;
    if ((fw = fopen(outputPath, "w")) == NULL) {
        printf("Fail to open file: output!\n");
        exit(0);
    }
    while(true){
        globalMin = getMin(sorting, isEnd, ways);
        if(globalMin == -1){
            // �����������
            // �����������û�ж����Ԫ��, ֻҪ�����[�����������С]����[��Ԫ������]
            printf("all the tuples are sorted finally, End!\n");
            // �ͷ���Դ
            for(int i = 0; i < ways; i ++){
                fclose(fr[i]);
                if(pTuple2[i] != NULL)
                    freeTuples(pTuple2[i], globalOutputNum[globalMin]);
            }
            fclose(fw);
            free(pTuple2);
            break;
        }
        output[outputNum ++] = sorting[globalMin]; // ��Сֵ�������������
        sorting[globalMin] = pTuple2[globalMin][waitNum[globalMin] ++]; // ��min���ȴ���++
        // ����������������
        if(outputNum == globalOutputNum[globalMin]){
            writeTuples( fw, output, globalOutputNum[globalMin]);
            outputNum = 0; // ����
            writeCount ++;
            printf("output data to disk for %d times\n", writeCount);
        }
        // ���ĳһ���Ѿ������
        if(waitNum[globalMin] == waitToSort){
            free(pTuple2[globalMin]);
            pTuple2[globalMin] = readTuples(fr[globalMin], waitToSort);
            waitNum[globalMin] = 0; // ���ڵȴ���Ԫ������
            // ���뻺��������Ҫ���¸�ֵ
            // ֱ�Ӹ�ֵ, ����Ҫ��ǰ������ʹ[���뻺�����Ĵ�С]����[ÿ���Ӽ��Ĵ�С]
            if(globalOutputNum[globalMin] < waitToOutput){
//                printf("min: %d\n", globalOutputNum[globalMin]);
                isEnd[globalMin] = true;
            }
        }
    }
}
