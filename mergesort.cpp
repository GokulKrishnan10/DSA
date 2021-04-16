#include<bits/stdc++.h>
using namespace std;
void merge(int L[],int R[],int A[],int nl,int nr,int n)
{
    int i=0,j=0,k=0;
    while(i<nl && j<nr)
    {
        if(L[i]<=R[j])
        {
            A[k++]=L[i++];
        }
        else{A[k++]=R[j++];}
    }
    while(i<nl)
    {
        A[k++]=L[i++];
    }
    while(j<nr)
    {
        A[k++]=R[j++];
    }

}
void print(int A[],int n)
{
    cout<<"Sorted array is:";
    for(int i=0;i<n;i++){cout<<A[i]<<" ";}
}
void mergesort(int A[],int n)
{
    if(n<2){return;}
    int m=n/2;
    int *l=new int[m];
    int *r= new int[n-m];
    for(int i=0;i<m;i++){l[i]=A[i];}int ii=0;
    for(int j=m;j<n;j++){r[ii++]=A[j];}
    mergesort(l,m);
    mergesort(r,n-m);
    merge(l,r,A,m,n-m,n);
}
int main()
{

    int a,n;
    cout<<"Enter the number of elements Gokul"<<endl;
    cin>>n;
    int *v=new int[n];
    for(int i=0;i<n;i++){cin>>v[i];}
    mergesort(v,n);
    print(v,n);
}
