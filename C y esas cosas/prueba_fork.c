#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void) {
	int contador = 0;
	printf("La ejecución comenzó\n");
	
	pid_t idHijo;
	
	idPadre = getpid();
	
	printf("Antes de bifurcar\n");
	contador++;
	idHijo = fork();
	printf("Despues de bifurcar\n");

	if (idHijo == 0) {
		printf("ID. hijo: %ld Id.Padre: %ld Contador: %d \n", (long)getpid(), (long)idPadre, contador);	
	} else {
		printf("ID. padre: %ld Id.hijo: %ld Contador: %d \n", (long)getpid(), (long)idPadre, contador);
	}
	return 0;
}
