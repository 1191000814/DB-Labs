#include <stdio.h>
#include <stdlib.h>
#include "tuple.h"

int main(int argc, char const *argv[]) {
    FILE *fpw1;
    Tuple tuple;
    if ((fpw1 = fopen("D:\\Code\\C\\database\\resources\\input.txt", "w")) == NULL) {
        printf("Fail to open file1!");
        exit(0);
    }
    Tuple *pTuple = initializeTuples(10);
    // printTuples(pTuple, 10);
    writeTuples(fpw1, pTuple, 10);
    fclose(fpw1);

    FILE *fpr1;
    if ((fpr1 = fopen("D:\\Code\\C\\database\\resources\\input.txt", "r")) == NULL) {
        printf("Fail to open file2!");
        exit(0);
    }
    Tuple *pTuple1 = readTuples(fpr1, 10);
    printTuples(pTuple1, 10);
    fclose(fpr1);
    // 最后要释放内存
    free(pTuple);
    free(pTuple1);
    return 0;
}
