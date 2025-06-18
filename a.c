#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX 5000
#define SIZE 20
#define NAME 40

FILE *file, *logFile,*transaction;
char username[SIZE], password[SIZE], c[5], buffer[255], q[5], e[5], w[4], append[256], a[4], show[MAX], ta[5], p[5], acc[SIZE], pass[SIZE], accname[NAME],u[4],temp[MAX];
char storedUsername[SIZE], storedPassword[SIZE];
float original_amount, new_balance,v,d;

int authenticate(char *username, char *password)
{
    file = fopen("BANK.txt", "r");

    if (file == NULL)
    {
        printf("Could not find the file.\n");
        return 0;
    }

    while (fscanf(file, "%s %s", storedUsername, storedPassword) != EOF)
    {
        if (strcmp(username, storedUsername) == 0 && strcmp(password, storedPassword) == 0)
        {
            fclose(file);
            return 1; // Login Successful
        }
    }
    fclose(file);
    return 0; // Login Unsuccessful
}

int login(char *accountno, char *password, char *name)
{
    logFile = fopen("TRANSACTION.txt", "r");

    if (logFile == NULL)
    {
        printf("Could not find the file.\n");
        return 0;
    }

    while (fscanf(logFile, "%s %s %s", acc, pass, accname) != EOF)
    {
        if ((strcmp(accountno, acc) == 0) && (strcmp(pass, password) == 0) && (strcmp(name, accname) == 0))
        {
            return 1; // Login Successful
			fclose(logFile);
        }
    }
    fclose(logFile);
    return 0; // Login Unsuccessful
}
int withdraw(float withdraw_amount) 
{
    FILE *tempFile;//taking it so as to store the amount to be updated in another file
    char line[MAX];
    float current_balance = 0.0;
    int balance_found = 0;
    
    // Open original file for reading
    transaction = fopen("TRANSACTION.txt", "r");
    if (transaction == NULL) {
        return -999; // Error code for file not found
    }

    // Create temporary file for writing
    tempFile = fopen("temp.txt", "w");
    if (tempFile == NULL) {
        fclose(transaction);
        return -999;
    }

    // Process each line
    while (fgets(line, sizeof(line), transaction)) 
	{
        // Check if this line contains the balance
        if (strstr(line, "Original Amount:")) {
        	
            sscanf(line, "Original Amount: %f", &current_balance);
            
            // Check if sufficient funds exist
            if (withdraw_amount > current_balance) 
			{
                fclose(transaction);
                fclose(tempFile);
                remove("temp.txt");
                return 0; // Insufficient funds
            }
            
            // Update the balance
            current_balance -= withdraw_amount;//shorthand notation
            fprintf(tempFile, "Original Amount: %.2f\n", current_balance);
            balance_found = 1;
        } 
        else {
            // Copy other lines unchanged
            fputs(line, tempFile);
        }
    }

    fclose(transaction);
    fclose(tempFile);

    if (!balance_found) {
        remove("temp.txt");
        return -999; // No balance record found
    }

    // Replace original file with updated file
    remove("TRANSACTION.txt");
    rename("temp.txt", "TRANSACTION.txt");

    return 1; // Success
}
int deposit(float deposit_amount)
{
    FILE *tempFile;
    char line[MAX];
    int found = 0;
    
    transaction = fopen("TRANSACTION.txt", "r");
    if (transaction == NULL)
    {
        return -999;
    }

    tempFile = fopen("temp.txt", "w");
    if (tempFile == NULL)
    {
        fclose(transaction);
        return -999;
    }

    while (fgets(line, sizeof(line), transaction))
    {
        if (strstr(line, "Original Amount:"))
        {
            sscanf(line, "Original Amount: %f", &original_amount);
            new_balance = original_amount + deposit_amount; //updates amount
            fprintf(tempFile, "Original Amount: %.2f\n", new_balance);
            found = 1;
        }
        else
        {
            fputs(line, tempFile);
        }
    }

    fclose(transaction);
    fclose(tempFile);

    if (!found)
    {
        remove("temp.txt");
        return -999;
    }

    remove("TRANSACTION.txt");
    rename("temp.txt", "TRANSACTION.txt");

    return 1;
}

void log_transaction(const char *username, const char *type, double amount)
{
    time_t now;
    struct tm *local;

    logFile = fopen("TRANSACTION.txt", "a+");
    if (logFile == NULL)
    {
        printf("Error opening transaction history file.\n");
        return;
    }

    now = time(NULL);
    local = localtime(&now);

    fprintf(logFile, "[%02d-%02d-%04d %02d:%02d:%02d] User: %s | %s | Amount: %.2f\n",
            local->tm_mday, local->tm_mon + 1, local->tm_year + 1900,
            local->tm_hour, local->tm_min, local->tm_sec,
            username, type, amount);

    fclose(logFile);
}

int main()
{
    double amount;
    char transactionType[15];

    printf("Enter Username: ");
    scanf("%s", username);

    printf("Enter Password: ");
    scanf("%s", password);

    while (getchar() != '\n');

    if (authenticate(username, password))
    {
        printf("Login Successful\n");
    }
    else
    {
        printf("Invalid Username or Password.\n");
        printf("Access to file is denied.\n");
        exit(1);
    }

    printf("\nDO YOU WANT TO PERFORM A TRANSACTION? (yes/no): ");
    scanf("%s", ta);
    while (getchar() != '\n');

    if (strcmp("yes", ta) == 0)
    {
        printf("Enter Transaction Type (Deposit/Withdraw/Loan/Repayment): ");
        scanf("%s", transactionType);
        while (getchar() != '\n');

        printf("Enter Amount: ");
        scanf("%lf", &amount);

        log_transaction(username, transactionType, amount);

        printf("Transaction successfully recorded!\n");
        
        if(strcasecmp(transactionType,"Withdraw")==0)
        {
            v=withdraw(amount);
            if(v==0)
            {
                printf("Insufficient funds! Withdrawal denied.\n");
            }
            else if(v==-999)
            {
                printf("Error opening file for update!\n");
            }
            else 
                printf("Transaction successful! New balance updated.\n");        
        }
        else if(strcasecmp(transactionType,"Deposit")==0)
        {
            d=deposit(amount);
            if(d==-999)
            {
                printf("Error updating account!! \n");
            }
            else
                printf("Transaction successful! New balance updated.\n");
        }
        else if(strcasecmp(transactionType,"Loan")==0)
        {
            printf("THIS IS A FRIENDLY REMINDER THAT YOU HAVE A LOAN OF: %.2f",amount);
        }
        else
            printf("OK %.2f",amount);
    }
    
    printf("\nDO YOU WANT TO VIEW TRANSACTION HISTORY? (yes/no): ");
    scanf("%s", w);
    while (getchar() != '\n');

    if (strcmp("yes", w) == 0)
    {
        printf("enter the account number:\n");
        scanf("%s",acc);
        printf("enter the password:\n");
        scanf("%s",pass);
        printf("enter the account name:\n");
        scanf("%s",accname);
        if(login(acc,pass,accname)==1)
        {
            file = fopen("TRANSACTION.txt", "r");
            if (file == NULL)
            {
                printf("Could not open transaction history file.\n");
                return 1;
            }

            printf("\nTransaction History:\n");
            while (fgets(buffer, sizeof(buffer), file) != NULL)
            {
                printf("HISTORY:\n%s\n--->>\n", buffer);
            }
            fclose(file);
            
            printf("DO YOU WANT TO ERASE THE MEMORY?: (yes/no)");
            scanf("%s",u);
            while (getchar() != '\n');
            if(strcasecmp(u,"yes")==0)
            {
                if(remove("TRANSACTION.txt")==0)
                {
                    printf("DELETED\n");
                }
                else
                    printf("NOT DELETED");
            }
        }
        else
        {
            printf("SORRY WRONG IDENTIFICATION");
        }  
    }

    printf("DO YOU WANT TO READ THE CONTENTS OF THE FILE HIGHLIGHTING THE REASONS OF TRANSACTIONS? yes/no: ");
    scanf("%s", c);
    while (getchar() != '\n');

    if (strcmp("yes", c) == 0)
    {
        file = fopen("BANK.txt", "r");

        if (file == NULL)
        {
            printf("\nCould not open the file for reading.\n");
            return 1;
        }

        printf("\nHere comes:\n-------------\n\n");
        while (fgets(buffer, sizeof(buffer), file) != NULL)
        {
            printf("%s", buffer);
        }
        fclose(file);
    }
    else
    {
        printf("\nOK, bye.\n");
    }

    printf("\nDO YOU WANT TO DELETE THE CONTENTS OF THE FILE?...yes/no:\n");
    scanf("%s", q);
    while (getchar() != '\n');

    if (strcmp(q, "yes") == 0)
    {
        printf("Are you sure?... Deleting the contents will completely erase all memory.\n");
        scanf("%s", e);
        while (getchar() != '\n');

        if (strcmp(e, "yes") == 0)
        {
            if (remove("BANK.txt") == 0)
            {
                printf("\nFile deleted successfully.\n");
            }
            else
            {
                printf("\nCould not delete file.\n");
            }
        }
    }

    printf("\nDO YOU WANT TO APPEND i.e. ADD SOMETHING AT THE END?...(yes/no):\n");
    scanf("%s", w);
    while (getchar() != '\n');

    if (strcmp(w, "yes") == 0)
    {
    	printf("PLEASE PRESS ENTER NOW SO AS TO TAKE THE CURSOR TO THE NEXT LINE. OTHERWISE YOUR ENTRY MIGHT NOT GET PRINTED IN THE FILE !!");
        file = fopen("BANK.txt", "a+");
        if (file == NULL)
        {
            printf("Error opening file.\n");
            return 1;
        }

        printf("Write the text you want to add (type '--x--' to finish):\n");
        while (getchar() != '\n');

        while (fgets(append, sizeof(append), stdin))
        {
            append[strcspn(append, "\n")] = 0;
            if (strcmp(append, "--x--") == 0)
            {
                break;
            }
            fprintf(file, "%s\n", append);
        }
        fclose(file);
        printf("\nsuccessfully appended\n");
    }

    printf("\nDO YOU WANT TO SEE THE TEXT NOW?...(yes/no)\n");
    scanf("%s", a);
    while (getchar() != '\n');

    if (strcmp("yes", a) == 0)
    {
        file = fopen("BANK.txt", "r");
        if (file == NULL)
        {
            printf("\nCould not open file for reading.\n");
            return 1;
        }

        while (fgets(show, sizeof(show), file) != NULL)
        {
            printf("%s", show);
        }
        fclose(file);
    }

    return 0;
}
