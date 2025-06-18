#include <stdio.h>
#include <string.h>

#define MAX 500

int main() 
{
    FILE *file;
    char u[30], p[30], h[5], contents[MAX];

    // Open the file in write mode
    file = fopen("BANK.txt", "w");

    // Check if the file was created successfully
    if (file == NULL) 
    {
        printf("Error creating the file!\n");
        return 1;
    }

    printf("ENTER THE USERNAME:..\n");
    scanf("%19s", u); 
    
    password:
    printf("ENTER THE PASSWORD:....(please enter at least a special character for safety..)\n");
    scanf("%19s", p);
    if(strlen(p)<8)
    {
    	printf("\nTHE PASSWORD MUST BE ATLEAST 8 CHARACTERS LONG\n");
    	goto password;
	}

    // Write to the file with a newline to separate username/password from transaction details
    fprintf(file, "%s %s\n", u, p);

    printf("DO YOU WANT TO WRITE THE PAYMENT DETAILS...highlighting the reasons why you have made the transaction? yes/no: ");
    scanf("%4s", h); // Avoids buffer overflow

    if (strcasecmp("yes", h) == 0) 
    {
        printf("Write the contents of the file (type '--x--' to finish):\n");
        while (getchar() != '\n'); // Clears buffer properly

        while (fgets(contents, sizeof(contents), stdin)) 
        {
            contents[strcspn(contents, "\n")] = 0; // Remove newline
            if (strcmp(contents, "--x--") == 0) 
            {
                break;
            }
            fprintf(file, "%s\n", contents);
        }
    } 
    else 
    {
        printf("OK, bye.\n");
    }

    // Close the file
    fclose(file);

    printf("File created successfully!\n");

    return 0;
}
