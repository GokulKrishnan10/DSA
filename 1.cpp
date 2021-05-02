#include<bits/stdc++.h>
using namespace std;
int longestValidParentheses(string s) {
        int n=s.size();int c=0;
        if(n==0)
        {return 0;}
        stack<char> S;
        for(int i=0;i<n;i++)
        {
            if(char(s[i])=='(')
            {cout<<1<<endl;c++;S.push(char(s[i]));}
            else if(char(s[i])==')')
            {   
                if(S.top()=='(')
                {cout<<S.top()<<" "<<s[i]<<endl;c++;/*cout<<3<<endl*/;S.pop();}
                else
                {c--;cout<<2<<endl;}
            }
        }if(!S.empty()){c=c-S.size();return c;}
        cout<<S.empty()<<endl;
        return c;
    }
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
    //cout<<"Maximum area of container will be "<<maxArea(v);
    cout<<longestValidParentheses("())");
}