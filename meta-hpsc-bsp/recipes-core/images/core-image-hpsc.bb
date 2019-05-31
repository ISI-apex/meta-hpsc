require recipes-core/images/core-image-minimal.bb

SUMMARY = "HPSC Chiplet image."

DESCRIPTION = "HPSC Chiplet image based on core-image-minimal. \
Primarily this defines extra packages to install."

# Please list alphabetically and group items together appropriately
# This variable may be overridden, hence kept separate from IMAGE_INSTALL
HPSC_IMAGE_INSTALL ?= " gdb gdbserver \
                        libgfortran \
                        libgomp \
                        libstdc++ \
                        mpich \
                        mtd-utils mtd-utils-misc mtd-utils-ubifs \
                        nas-parallel-benchmarks \
                        openssh openssh-sftp-server \
                        perf \
                        python3-core python3-numpy \
                        qemu \
                        util-linux \
                        watchdog \
                        "

IMAGE_INSTALL += "${HPSC_IMAGE_INSTALL}"
