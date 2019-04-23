inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://github.com/ISI-apex/linux-hpsc.git;protocol=git;branch=hpsc"
SRCREV = "34d34e60a40e700412a508b4589f137cc92feb87"

LINUX_VERSION ?= "4.14.0"

# skip version sanity, because the version moves with AUTOREV
KERNEL_VERSION_SANITY_SKIP = "1"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE_hpsc-chiplet = "hpsc-chiplet"

KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG_hpsc-chiplet ?= "hpsc_defconfig"
