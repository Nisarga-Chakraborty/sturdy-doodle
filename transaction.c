#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    FILE *transaction;
    char accno[11], p[11], name[40],a[3],i[4];
    int o,t=0;

    transaction = fopen("TRANSACTION.txt", "w");
    if (transaction == NULL)
    {
        printf("Error in opening the file!\n");
        return 1;
    }
    printf("THE FOLLOWING BANKS ARE PRINTED....IF YOU SEE YOUR BANK AMONG THEM , THEN TYPE IN THE NUMBER ADJACENT TO IT\n OTHERWISE CLICK ON THE NUMBER BESIDE-----OTHERS------\n");
    puts("-----------------------------------------------");//using this function so as to take the cursor to the next line automatically
    printf(" (1) State Bank of India \n (2) Punjab National Bank \n (3) HDFC Bank \n (4) Indian Overseas Bank\n (5) Bank of Baroda \n (6) Kotak Mahindra Bank \n (7) Canara Bank \n (8) Union Bank of India \n (9) Bank of India \n (10) Others \n");
    puts("-----------------------------------------------");//using this function so as to take the cursor to the next line automatically
	scanf("%d",&o);
    
    switch(o)
    {
    	case 1 : printf("WELCOME TO SBI..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 2 : printf("WELCOME TO PNB..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 3 : printf("WELCOME TO HDFC Bank ..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 4 : printf("WELCOME TO IOB..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 5 : printf("WELCOME TO Bank of Baroda..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 6 : printf("WELCOME TO Kotak Mahindra Bank..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 7 : printf("WELCOME TO Canara Bank..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 8 : printf("WELCOME TO Union Bank of India..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 9 : printf("WELCOME TO Bank of India..Thank you for choosing us...\nYour account is being processed :\n");
    	break;
    	case 10 : printf(" OTHERS \n ");
    	break;
    	default : printf("WRONG CHOICE \n");
    	printf("please enter a valid choice");
    	exit(1);
    	break;
	}
	printf("Do you want to make an account?\n(yes/no)\n");
	scanf("%s",i);
	if(strcasecmp(i,"yes")==0)
	{
		printf("Select acount type\n -----------------------------------------");
	    printf("\n 1. Savings Account\n 2. Current Account\n 3. Public Provident Fund\n");
	    scanf("%d",&t);
	    switch(t)
	    {
		   case 1: printf("\n ok....choice is saved\n");
		   printf("--> SAVINGS ACCOUNT\n");
		   break;
		   case 2: printf("\n ok.....your choice is saved");
		   printf("--> CURRENT ACCOUNT\n");
		   break;
		   case 3: printf("\n ok.....your choice is saved");
		   printf("--> PUBLIC PROVIDENT FUND\n");
		   break;
		   default: printf("WRONG CHOICE");
		   exit(1);
		   break;
	         
	    }
	    read:
        printf("ENTER THE ACCOUNT NUMBER:\n");
        scanf("%s", accno);
        if(strlen(accno)!=10)
        {
    	  printf("PLEASE ENTER A VALID ACCOUNT NUMBER\n");
    	  goto read;
	    }
    
        password:
        printf("ENTER THE PASSWORD:(ENTER ATLEAST A SPECIAL CHARACTER)\n");
        scanf("%s", p);
        if(strlen(p)<8)
        {
    	    printf("PLEASE ENTER A PASSWORD ATLEAST 8 CHARACTERS LONG! \n");
    	    goto password;
	    }

    printf("MENTION THE NAME OF THE ACCOUNT:\n ");
    scanf("%s", name);

    fprintf(transaction, "%s %s %s\n", accno, p, name);
    printf("------------------------\n\n");
    amount :
    printf("It is for your kind information that the amount you enter here while creating an amount will be directly transferred to the BANK and will not be available to you :) \n");
    printf("NOW YOU HAVE TO ENTER EXACTLY 60 Rupees AS A FEE , TO CREATE THE ACCOUNT : \n");
    printf("enter the amount in the account:\n");
    scanf("%s",&a);
    if(strcmp(a,"60")==0)
    {
    	printf("ACCOUNT CREATED\n");
    	fprintf(transaction," Original Amount:%s\n",a);
    	fclose(transaction);
        printf("ACCOUNT MADE SUCCESSFULLY\n");
    	exit(1);
    	
	}
    
    else
    {
    	printf("PLEASE GIVE THE DESIRED AMOUNT\n\n");
    	goto amount;
	}
	    
	}
	else if(strcasecmp(i,"no")==0)
	{
		printf(" OK BYE ");
		exit(1);
	}
	
    
    
    
    
    

    return 0;
}

