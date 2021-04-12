#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<bits/stdc++.h>
struct node
{
    int data;
    struct node* next;
};
std::stack <struct node*> S;
std::vector<int> v;
struct node*head;
struct node *t,*t1,*t2,*t3,*t4,*cu;
void insertbegin();
void reverse();
void insertend();
void insertn();
void Delete();
void print(struct node*);
int main()
{   printf("GOKULA KRISHNAN E-19EUEC045\n");
    int n;printf("Enter the number of nodes:\n");
    scanf("%d",&n);printf("Enter the element(At end)\n");
    for(int i=0;i<n;i++){insertend();}
    print(head);printf("1.Insert element at begin  2.Insert element at n 3.Delete an element at postion n\n");
    int c=1;
    do
    {
        switch(c)
        {
            case 1:{insertbegin();print(head);break;}
            case 2:{insertn();print(head);break;}
            case 3:{Delete();print(head);break;}
            default:{printf("INVALID\n");}
        }
       printf("Enter the choice\n");
       scanf("%d",&c);
    }while(c!=4);
    reverse();
    print(head);
}
void reverse()
{printf("Reversed linked list\n");
    struct node* t=head;
    while(t!=NULL)
    {
        S.push(t);
        t=t->next;
    }
    struct node* t1=S.top();
    head=t1;
    S.pop();
    while(!S.empty())
    {
        t1->next=S.top();
        S.pop();
        t1=t1->next;
    }t1->next=NULL;
}
void insertbegin()
{
    int a;
    printf("Enter the element(At begin)\n");
    scanf("%d",&a);
    t=(struct node*)malloc(sizeof(struct node*));
    t->data=a;
    t->next=NULL;
    if(head!=NULL){t->next=head;}
    head=t;
}
void insertend()
{
    int x;
    scanf("%d",&x);
    t=(struct node*)malloc(sizeof(struct node*));
    t->data=x;
    if(head==NULL)
    {
        head=t;
        cu=t;
    }
    else
    {
        cu->next=t;
        cu=t;
    }
}
void insertn()
{
    printf("Enter the element and position(At n)\n");int a1,n;
    scanf("%d %d",&a1,&n);
    t3=(struct node*)malloc(sizeof(struct node*));t4=head;
    t3->data=a1;
    t3->next=NULL;
    if(n==1)
    {
        t3->next=head;
        head=t3;
    }
    else
    {
        for(int i=0;i<n-2;i++)
        {
            t4=t4->next;
        }t3->next=t4->next;
        t4->next=t3;
    }
}
void Delete()
{   t1=head;
    printf("Enter the position to be deleted\n");int n1;
    scanf("%d",&n1);
    if(n1==1)
    {
        head=t1->next;
        free(t1);
    }
    else
    {
        for(int i=0;i<n1-2;i++)
        {
            t1=t1->next;
        }
        t2=t1->next;
        t1->next=t2->next;
        free(t2);
    }
}
void print(struct node* h)
{printf("Linked List: ");
    while(h!=NULL)
    {
        printf("%d ",h->data);
        h=h->next;
    }printf("\n");}
