#include<bits/stdc++.h>
using namespace std;
int maxArea(vector<int>& height) {
        int l=0;vector<int> v;
        int r=height.size()-1;
        while(l<r)
        {
            int len,b,a;
            b=(r+1)-(l+1);
            len=height[l]<height[r]?height[l]:height[r];
            a=len*b;
            v.push_back(a);height[l]>height[r]?r--:l++;
        }
        int n=v.size();int m=v[0];
        for(int i=0;i<n;i++)
        { 
            if(v[i]>m){m=v[i];}
        }return m;
}
int main()
{
    vector<int> v;
    int n;
    cin>>n;int d;
    for(int i=0;i<n;i++)
    {
        cin>>d;v.push_back(d);
    }cout<<"Maximum area of container will be "<<maxArea(v);
}