#include "stdio.h"
#include "stdlib.h"


//1.X^n
//int pow(long  long x, long long n)
//{
//	long long res = 1;
//	while (n > 0)
//	{
//		if (n & 1 == 1)
//		{
//			res *= x;
//		}
//		n = n >> 1;
//		x = x * x;
//	}
//	return res;
//}
//
//int main(void)
//{
//	long long res = pow(13,7);
//	printf("%ld\n",res);
//
//	return 0;
//}

//2.找出没有重复的数
//int main(void)
//{
//	int arr[] = {5,9,7,3,1,5,6,2,9,6,7,2,3};
//	int tmp = arr[0];
//	for (int i = 1; i < sizeof(arr) / sizeof(int); i++)
//	{
//		tmp ^= arr[i];
//	}
//	printf("%d",tmp);
//
//	return 0;
//}

//3.找出不大于N的最大的2的幂指数
int main(void)
{
	int n;
	scanf_s("%d", &n);

	int tmp = n;
	while (tmp > 0)
	{
		tmp = tmp >> 1;
		n = n | tmp;
	}
	//n = n + 1;
	//n = n >> 1;
	printf("%d",(n + 1) >> 1);

	return 0;
}