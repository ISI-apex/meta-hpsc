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
                        mtd-utils \
                        mtd-utils-ubifs \
                        mtd-utils-misc \
                        openssh openssh-sftp-server \
                        perf \
                        python-core python-numpy \
                        util-linux \
                        watchdog \
                        "

IMAGE_INSTALL += "${HPSC_IMAGE_INSTALL}"
