from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oeid import OETestID

class OpenmpTest(OERuntimeTestCase):

    @OETestID(1001)
    @OETestDepends(['scp.ScpTest.test_scp_file'])
    def test_openmp(self):
        num_threads = [1, 2, 4, 8]
        for t in num_threads:
            (status, output) = self.target.run('export OMP_NUM_THREADS=' + str(t) + ';/opt/openmp-test/count_omp_threads')
            self.assertEqual(status, 0, msg="Error running OpenMP executable")
            self.assertEqual(output, "Number of threads = " + str(t),
                             msg="OMP_NUM_THREADS=" + str(t) +" setting not respected")
