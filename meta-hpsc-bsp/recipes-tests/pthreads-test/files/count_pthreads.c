/******************************************************************************
 * FILE: count_pthreads.c
 ******************************************************************************/
#include <errno.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

void *Exit(void *threadid)
{
    pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
    int num_threads;
    long t;
    int rc;
    pthread_t *threads;
    if (argc < 2) {
        fprintf(stderr, "Usage: %s NTHREADS\n", argv[0]);
        return EINVAL;
    }
    num_threads = atoi(argv[1]);
    if (num_threads <= 0) {
        fprintf(stderr, "ERROR: NTHREADS must be > 0\n");
        return EINVAL;
    }
    threads = malloc(num_threads * sizeof(pthread_t));
    if (!threads) {
        perror("malloc");
        return errno;
    }
    for (t = 0; t < num_threads; t++) {
        rc = pthread_create(&threads[t], NULL, Exit, (void *)t);
        if (rc) {
            fprintf(stderr, "ERROR: return code from pthread_create() is %d\n",
                    rc);
            free(threads);
            return rc;
        }
    }
    printf("Created and destroyed %d threads using pthread_create() and pthread_exit()\n",
           num_threads);

    free(threads);
    return 0;
}
