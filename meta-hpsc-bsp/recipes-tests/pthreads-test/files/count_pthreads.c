/******************************************************************************
 * FILE: count_pthreads.c
 ******************************************************************************/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

void *Exit(void *threadid)
{
  pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
  int num_threads = atoi(argv[1]);
  pthread_t threads[num_threads];
  int rc;
  long t;
  for(t=0;t<num_threads;t++){
    rc = pthread_create(&threads[t], NULL, Exit, (void *)t);
    if (rc){
      printf("ERROR; return code from pthread_create() is %d\n", rc);
      exit(-1);
    }
  }
  printf("Created and destroyed %d threads using pthread_create() and pthread_exit()\n", num_threads);

  /* Last thing that main() should do */
  pthread_exit(NULL);
}
