#include<bits/stdc++.h>
using namespace std;
void swap(int *a,int *b)
{
    int t;
    t=*a;
    *a=*b;
    *b=t;
}
int partition(int ar[],int s,int e)
{
    int pivot=ar[e];
    int in=s;
    for(int i=s;i<e;i++)
    {
        if(ar[i]<=pivot)
        {
            swap(&ar[i],&ar[in]);in++;
        }
    }
    swap(&ar[in],&ar[e]);
    return in;
}
int Random(int *ar,int s,int e)
{
    int in=s+rand()%(e-s);
    swap(&ar[in],&ar[e]);
    return partition(ar,s,e);
}
void Quicksort(int *ar,int s,int e)
{
    if(s<e)
    {
        int in=Random(ar,s,e);
        Quicksort(ar,s,in-1);
        Quicksort(ar,in+1,e);
    }
}
int main()
{
    int n;
    cout<<"Enter n: ";
    cin>>n;
    int *ar=new int[n];
    cout<<"Enter elements: ";
    for(int i=0;i<n;i++)
    {
        cin>>ar[i];
    }Quicksort(ar,0,n-1);
    cout<<"Sorted array is: ";
    for(int i=0;i<n;i++)
    {
        cout<<ar[i]<<" ";
    }
}