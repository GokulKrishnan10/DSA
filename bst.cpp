#include<bits/stdc++.h>
using namespace std;
class bst
{
    public:
    int data;
    bst *left;
    bst *right;
    bst(int data)
    {
        this->data=data;
        left=NULL;
        right=NULL;
    }
};
bool search(bst *root,int data)
{
    if(!root){return false;}
    if(root->data==data){
        return true;
    }
    else if(root->data<data)
    {
        return search(root->right,data);
    }
    else if(root->data>=data)
    {
        return search(root->left,data);
    }
    return false;
}
bst* insert(bst *root,int data)
{
    if(!root){
        return new bst(data);
    }
    else if(root->data<data)
    {
        root->right=insert(root->right,data);
    }
    else
    {
        root->left=insert(root->left,data);
    }return root;
}
void preorder(bst *root)
{
    if(!root){return;}
    cout<<root->data<<" ";
    preorder(root->left);
    preorder(root->right);
}
void inorder(bst* root)
{
    if(!root){return;}
    inorder(root->left);
    cout<<root->data<<" ";
    inorder(root->right);
}
void postorder(bst* root)
{
    if(!root){return;}
    postorder(root->left);
    postorder(root->right);
    cout<<root->data<<" ";
}
void levelorder(bst* root)
{
    queue<bst*> q;
    if(!root){return;}
    q.push(root);
    while(!q.empty()){
        bst* c=q.front();
        cout<<c->data<<" ";
        if(c->left!=NULL){
            q.push(c->left);
        }
        if(c->right!=NULL){
            q.push(c->right);
        }
        q.pop();//REMOVING ELEMENT FROM THE QUEUE
    }

}
int min(bst *root)
{
    if(!root){return -1;}
    while(root->left!=NULL)
    {root=root->left;
    }return root->data;
}
int max(bst *root)
{
    if(!root){return -1;}
    while(root->right!=NULL)
    {root=root->right;
    }return root->data;
}
int main()
{
    int n;int d;bst* root=NULL;
    cout<<"Enter number of nodes ";
    cin>>n;
    for(int i=0;i<n;i++)
    {
        cin>>d;
        root=insert(root,d);
    }int s;
    cout<<"Enter the element to be searched ";cin>>s;
    cout<<"Found(1) or Not Found(0): "<<search(root,s)<<endl;
    cout<<"Preorder traversal: ";preorder(root);cout<<endl;
    cout<<"Inorder traversal: ";inorder(root);cout<<endl;
    cout<<"Postorder traversal: ";postorder(root);cout<<endl;
    cout<<"Levelorder traversal: ";levelorder(root);cout<<endl;
    cout<<"Minimum element in BST: "<<min(root)<<endl;
    cout<<"Maximum element in BST: "<<max(root)<<endl;
}