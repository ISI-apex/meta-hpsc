/******************************************************************************
 * FILE: count_mpi_ranks.c
 ******************************************************************************/
#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) 
{
    int size;
    int rank;
    int ret;

    ret = MPI_Init(NULL, NULL);
    if (ret != MPI_SUCCESS)
        return ret; // MPI_ERR_OTHER

    ret = MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    if (ret != MPI_SUCCESS)
        return ret; // MPI_ERR_COMM

    if (rank == 0) {
        ret = MPI_Comm_size(MPI_COMM_WORLD, &size);
        if (ret != MPI_SUCCESS)
            return ret; // MPI_ERR_COMM or MPI_ERR_ARG
        printf("Number of ranks = %d\n", size);
    }

    return MPI_Finalize();
}
