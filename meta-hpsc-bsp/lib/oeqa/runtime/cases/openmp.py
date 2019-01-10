import os

from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oeid import OETestID

class OpenmpTest(OERuntimeTestCase):

    @classmethod
    def setUpClass(cls):
        # use the path to this file as the starting point to reach the "count_omp_threads" executable
        # since I couldn't find a better path from the available OpenEmbedded features
        src = os.path.join(os.path.dirname(os.path.realpath(__file__)), '../../files/count_omp_threads')
        dst = '/tmp/'
        cls.tc.target.copyTo(src, dst)

    @classmethod
    def tearDownClass(cls):
        cls.tc.target.run('rm /tmp/count_omp_threads')

    @OETestID(1001)
    @OETestDepends(['scp.ScpTest.test_scp_file'])
    def test_openmp(self):
        num_threads = [1, 2, 4, 8]
        for t in num_threads:
            (status, output) = self.target.run('export OMP_NUM_THREADS=' + str(t) + ';/tmp/count_omp_threads')
            self.assertEqual(status, 0, msg="Error running OpenMP executable")
            self.assertEqual(output, "Number of threads = " + str(t), msg="OMP_NUM_THREADS=" + str(t) +" setting not respected")
