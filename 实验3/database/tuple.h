#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <math.h>

static int globalOutputNum[16] = {20000, 20000,20000,20000,20000,20000,20000,20000,20000,20000,
                                  20000,20000,20000,20000,20000,20000,}; // 输出缓冲区将要输出的元素数量, 默认是最大数量
static int globalMin; // 当前最小元素所在的子集

// 一个元组
typedef struct tuple{
    int a;
    char* b;
} Tuple;

// 初始化一个元组数组
Tuple* initializeTuples(int n){
    Tuple* pTuple = (Tuple*) malloc(n * sizeof(Tuple));
    if(pTuple == NULL){
        printf("no more memory");
        exit(1);
    }
    char abc[63] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    for(int i = 0; i < n; i ++){
        pTuple[i].a = rand(); // 初始化整数
        pTuple[i].b = (char*) malloc (12);
        if(pTuple[i].b == NULL){
            printf("no more memory");
            exit(1);
        }
        int l = strlen(abc);
        for(int j = 0; j < 11; j ++){ // 初始化字符串
            pTuple[i].b[j] = abc[rand() % (l - 1)];
        }
        pTuple[i].b[11] = '\0';
    }
    return pTuple;
}

// 释放一个元组结构体数组的空间
void freeTuples(Tuple* pTuple, int n){
    for(int i = 0; i < n; i ++){
        free(pTuple[i].b);
    }
    free(pTuple);
}

// 打印一个元组数组
void printTuples(Tuple* pTuple, int n){
    printf("{");
    for(int i = 0; i < n; i++)
        printf("[a: %d, b: %s] ", pTuple[i].a, pTuple[i].b);
    printf("}\n");
}

// 将一个元组数组写入文件
void writeTuples(FILE* fp, Tuple* pTuple, int n){
    for(int i = 0; i < n; i++){
        fprintf(fp, "%d ", pTuple[i].a);
        fprintf(fp, "%s ", pTuple[i].b);
    }
}

// 将字符串转化为整数
int parserInt(const char* a, int n){
    int result = 0;
	for(int i = 0; i < n; i++){
        result += (int) ((int) a[i] - 48) * pow(10, n - i - 1);
    }
    return result;
}

// 从文件中读一个元组: 暂未使用
Tuple readATuple(FILE* fp){
    char buf[12];
    int j = 0;
    Tuple tuple;
    while((buf[j ++] = fgetc(fp)) != ' '){}
    tuple.a = parserInt(buf, j - 1); // 求出属性a

    tuple.b = (char*) malloc (12); // 给b malloc空间
    if(tuple.b == NULL){
        printf("no more memory");
        exit(1);
    }
    j = 0;
    while((buf[j ++] = fgetc(fp)) != ' '){
        tuple.b[j - 1] = buf[j - 1]; // 求出属性b
    }
    tuple.b[11] = (char) 0; // 字符串结尾
    return tuple;
}

// 从文件中读出元组数组
Tuple* readTuples(FILE* fp, int n){
    Tuple* pTuple = (Tuple*) malloc(n * sizeof(Tuple));
    if(pTuple == NULL){
        printf("no more memory");
        exit(1);
    }
    for(int i = 0; i < n; i ++){ // 一共要读n个元组
        // 先读整数
        char buf[12];
        int j = 0;
        char c;
        while((c = fgetc(fp)) != ' '){
            if(c == EOF){
                printf("this file has been read totally!\n");
                globalOutputNum[globalMin] = i; // 改变输出缓冲区要输出的数量
                return pTuple;
            }
            buf[j ++] = c;
        }
        pTuple[i].a = parserInt(buf, j - 1); // 求出属性a

        pTuple[i].b = (char*) malloc (12); // 给b malloc空间
        if(pTuple[i].b == NULL){
            printf("no more memory");
            exit(1);
        }
        j = 0;
        while((buf[j ++] = fgetc(fp)) != ' '){
            pTuple[i].b[j - 1] = buf[j - 1]; // 求出属性b
        }
        pTuple[i].b[11] = (char) 0; // 字符串结尾
    }
    return pTuple;
}

// 初始化一个二维元组数组
// 这个数组有m个指针, 每个指针分配n个空间
Tuple** createTuples2(int m){
    Tuple** pTuple2 = (Tuple**) malloc (sizeof(Tuple*) * m);
    if(pTuple2 == NULL){
        printf("no more memory");
        exit(1);
    }
    for(int i = 0; i < m; i ++){
        pTuple2[i] = NULL; // 给每个数组里每个指针分配NULL
    }
    return pTuple2;
}