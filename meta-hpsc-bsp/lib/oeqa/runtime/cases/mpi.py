from oeqa.runtime.case import OERuntimeTestCase

class MpiTest(OERuntimeTestCase):

    def test_mpi(self):
        processors = [1, 2, 4, 8]
        # mpirun binary is in the form "aarch64-poky-linux-mpirun"
        prefix = self.tc.td["TARGET_PREFIX"]
        for p in processors:
            cmd = (prefix + 'mpirun -np ' + str(p) +
                   ' /opt/mpi-test/count_mpi_ranks')
            (status, output) = self.target.run(cmd)
            msg = ('Failed to test MPI\ncmd: %s\nstatus: %s\noutput: %s'
                   % (cmd, status, output))
            self.assertEqual(status, 0, msg=msg)
            expected = "Number of ranks = " + str(p)
            self.assertEqual(output, expected, msg=msg)
