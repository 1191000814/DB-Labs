/**
 * @author Xiao Zeqiang
 * @brief HIT-Database-Lab3: Multi-way merger
 * @date 2022.04.28
 */
#include <time.h>
#include"sort.h"

int main(){
    clock_t start = clock();
    generate();
    firstSort();
    secondSort();
    printf("程序共运行了%fs", ((double) (clock()-start)) / CLK_TCK);
    return 0;
}