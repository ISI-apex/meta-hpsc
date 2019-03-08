from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends

class ShmStandaloneTest(OERuntimeTestCase):
    TEST_BINARY = '/opt/hpsc-utils/shm-standalone-tester'
    # this region must exist, be unreserved, and be at least 2 pages in size
    SHM_FILE = '/dev/hpsc_shmem/region2'
    SIZE = 32
    WRITE_BYTE = '0xff'
    OFFSET = 4096 # 1 page (must be a multiple of page size)

    def write(self, offset=0):
        # the test binary also verifies internally that write is successful
        cmd = ('%s -f %s -s %d -o %d -w %s' %
               (self.TEST_BINARY, self.SHM_FILE, self.SIZE, offset, self.WRITE_BYTE))
        (status, output) = self.target.run(cmd)
        msg = ('Failed to write shared memory region\ncmd: %s\nstatus: %s\noutput: %s'
               % (cmd, status, output))
        self.assertEqual(status, 0, msg=msg)

    def read(self, offset=0):
        # The -p flag verifies internally, but we also check the output from -r
        cmd = ('%s -f %s -s %d -o %d -p %s -r' %
               (self.TEST_BINARY, self.SHM_FILE, self.SIZE, offset, self.WRITE_BYTE))
        (status, output) = self.target.run(cmd)
        msg = ('Failed to verify shared memory region\ncmd: %s\nstatus: %s\noutput: %s'
               % (cmd, status, output))
        self.assertEqual(status, 0, msg=msg)
        exp_last = "Start: %s" % ((self.WRITE_BYTE + ' ') * self.SIZE).rstrip()
        self.assertEqual(output.split('\n')[-1], exp_last, msg=msg)

    def write_read(self, **kwargs):
        self.write(**kwargs)
        self.read(**kwargs)

    def test_simple(self):
        self.write_read()

    @OETestDepends(['shm_standalone.ShmStandaloneTest.test_simple'])
    def test_offset(self):
        self.write_read(offset=self.OFFSET)
