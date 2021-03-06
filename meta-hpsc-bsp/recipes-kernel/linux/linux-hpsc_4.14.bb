inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://github.com/ISI-apex/linux-hpsc.git;protocol=git;branch=hpsc"
SRCREV = "3f0a594e339cb7114f1c1667379b3f21cebd9492"

LINUX_VERSION ?= "4.14.0"

# skip version sanity, because the version moves with AUTOREV
KERNEL_VERSION_SANITY_SKIP = "1"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE_hpsc-chiplet = "hpsc-chiplet"

KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG_hpsc-chiplet ?= "hpsc_defconfig"
