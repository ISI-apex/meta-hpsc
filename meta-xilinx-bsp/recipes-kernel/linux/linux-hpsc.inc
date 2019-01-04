# This version extension should match CONFIG_LOCALVERSION in defconfig
XILINX_RELEASE_VERSION ?= ""
LINUX_VERSION_EXTENSION ?= "-xilinx-${XILINX_RELEASE_VERSION}"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac"

# KD- Added this flag to prevent possible problems when integrating our linux repo
KERNEL_VERSION_SANITY_SKIP="1"

FILESOVERRIDES_append = ":${XILINX_RELEASE_VERSION}"

SRCREV_machine ?= "${SRCREV}"

# don't export LDFLAGS
LDFLAGS[unexport] = "1"

require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "HPSC Kernel"

do_configure() {
        cp -r ${S}/* ${B}
        oe_runmake hpsc_defconfig ARCH=arm64 -C ${B}
}

do_compile() {
        oe_runmake ARCH=arm64 -C ${B}
}

# Force the use of the KBUILD_DEFCONFIG even if some other defconfig was generated in the ${WORKDIR}
do_kernel_metadata_prepend () {
	[ -n "${KBUILD_DEFCONFIG}" ] && [ -e ${WORKDIR}/defconfig ] && rm ${WORKDIR}/defconfig
}

# Use DEFCONFIGs for configuring linux-xlnx kernels
KCONFIG_MODE ?= "alldefconfig"
KBUILD_DEFCONFIG_hpsc-chiplet = "hpsc_defconfig"
