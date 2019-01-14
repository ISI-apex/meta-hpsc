import os

from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oeid import OETestID

class PthreadsTest(OERuntimeTestCase):

    @classmethod
    def setUpClass(cls):
        # use the path to this file as the starting point to reach the "count_pthreads" executable
        # since I couldn't find a better path from the available OpenEmbedded features
        src = os.path.join(os.path.dirname(os.path.realpath(__file__)), '../../files/count_pthreads')
        dst = '/tmp/'
        cls.tc.target.copyTo(src, dst)

    @classmethod
    def tearDownClass(cls):
        cls.tc.target.run('rm /tmp/count_pthreads')

    @OETestID(1002)
    @OETestDepends(['scp.ScpTest.test_scp_file'])
    def test_pthreads(self):
        num_threads = [1, 2, 4, 8]
        for t in num_threads:
            (status, output) = self.target.run('./count_pthreads ' + str(t))
            self.assertEqual(status, 0, msg="Error running Pthreads executable")
            self.assertEqual(output, "Created and destroyed " + str(t) + " threads using pthread_create() and pthread_exit()", msg="Pthreads executable output for " + str(t) + " threads is not correct.");
