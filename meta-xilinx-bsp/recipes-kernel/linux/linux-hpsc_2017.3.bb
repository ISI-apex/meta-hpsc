LINUX_VERSION = "4.9"
XILINX_RELEASE_VERSION = "v2017.3"

SRCREV_linux_hpsc ?= "7b917787042518ee64097989cf718f32672eb731"
SRCREV = "${SRCREV_linux_hpsc}"

SRC_URI = "git://github.com/ISI-apex/linux-hpsc.git;protocol=git;branch=hpsc"

include linux-hpsc.inc
