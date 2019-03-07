from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oeid import OETestID

class PthreadsTest(OERuntimeTestCase):

    @OETestID(1002)
    @OETestDepends(['scp.ScpTest.test_scp_file'])
    def test_pthreads(self):
        num_threads = [1, 2, 4, 8]
        for t in num_threads:
            (status, output) = self.target.run('/opt/pthreads-test/count_pthreads ' + str(t))
            self.assertEqual(status, 0, msg="Error running Pthreads executable")
            self.assertEqual(output, "Created and destroyed " + str(t) + " threads using pthread_create() and pthread_exit()",
                             msg="Pthreads executable output for " + str(t) + " threads is not correct.")
