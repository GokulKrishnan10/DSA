#include<iostream>
void swap(int *a,int *b)
{
    int t;
    t=*a;
    *a=*b;
    *b=t;
}
void Bubblesort(int a[],int n)
{printf("Bubble sorting: ");
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n-i-1;j++)
        {
            if(a[j]>a[j+1])
            {
                swap(&a[j],&a[j+1]);
            }
        }
    }
}
void Selectionsort(int a[],int n)
{printf("Selection sorting: ");
    for(int i=0;i<n;i++)
    {
        int m=i;
        for(int j=i+1;j<n;j++)
        {
            if(a[m]>a[j])
            {
                m=j;
            }
        }swap(&a[m],&a[i]);
    }
}
void Insertionsort(int a[],int n)
{printf("Insertion sorting: ");
    for(int i=0;i<n;i++)
    {
        int key=a[i];
        int j=i-1;
        while(j>0 && a[j]>key)
        {
            a[j+1]=a[j];
            --j;
        }a[j+1]=key;
    }
}
int main()
{
    int *ar;int n;
    ar=(int*)malloc(n*sizeof(int));
    printf("Enter the number of elements: ");
    scanf("%d ",&n);
    for(int i=0;i<n;i++)
    {scanf("%d",&ar[i]);}
    Bubblesort(ar,n);
    for(int i=0;i<n;i++)
    {printf("%d ",ar[i]);}printf("\n");
    Insertionsort(ar,n);
    for(int i=0;i<n;i++)
    {printf("%d ",ar[i]);}printf("\n");
    Selectionsort(ar,n);
    for(int i=0;i<n;i++)
    {printf("%d ",ar[i]);}
}