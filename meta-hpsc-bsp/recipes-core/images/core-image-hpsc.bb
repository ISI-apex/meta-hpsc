require recipes-core/images/core-image-minimal.bb

SUMMARY = "HPSC Chiplet image."

DESCRIPTION = "HPSC Chiplet image based on core-image-minimal. \
Primarily this defines extra packages to install."

# tools-debug: gdb, gdbserver, strace
IMAGE_FEATURES += "tools-debug"
# ssh-server-openssh: openssh (core-image may override and install dropbear instead)
IMAGE_FEATURES += "ssh-server-openssh"
# eclipse-debug: gdbserver, tcf-agent, openssh-sftp-server
IMAGE_FEATURES += "eclipse-debug"

# Please list alphabetically and group items together appropriately
# This variable may be overridden, hence kept separate from IMAGE_INSTALL
HPSC_IMAGE_INSTALL ?= " \
                        libgfortran \
                        libgomp libstdc++ \
                        mpich \
                        mtd-utils mtd-utils-misc mtd-utils-ubifs \
                        nas-parallel-benchmarks \
                        perf \
                        python3-core python3-numpy \
                        qemu \
                        util-linux \
                        watchdog \
                        "

IMAGE_INSTALL += "${HPSC_IMAGE_INSTALL}"
