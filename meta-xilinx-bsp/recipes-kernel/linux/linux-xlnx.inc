# This version extension should match CONFIG_LOCALVERSION in defconfig
XILINX_RELEASE_VERSION ?= ""
LINUX_VERSION_EXTENSION ?= "-xilinx-${XILINX_RELEASE_VERSION}"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

# KD- Added this flag to prevent possible problems when integrating our linux repo
KERNEL_VERSION_SANITY_SKIP="1"

FILESOVERRIDES_append = ":${XILINX_RELEASE_VERSION}"

# If SRCREV equals '${AUTOREV}', then specify 'branch=hpsc'
# in SRC_URI, else specify 'nobranch=1'
SRC_URI = "${@ "git://github.com/ISI-apex/linux-hpsc.git;protocol=git;branch=hpsc" if (d.getVar('SRCREV') == '${AUTOREV}') else "git://github.com/ISI-apex/linux-hpsc.git;protocol=git;nobranch=1" }"

SRCREV_machine ?= "${SRCREV}"

require recipes-kernel/linux/linux-yocto.inc
require linux-microblaze.inc

DESCRIPTION = "Xilinx Kernel"

# Force the use of the KBUILD_DEFCONFIG even if some other defconfig was generated in the ${WORKDIR}
do_kernel_metadata_prepend () {
	[ -n "${KBUILD_DEFCONFIG}" ] && [ -e ${WORKDIR}/defconfig ] && rm ${WORKDIR}/defconfig
}

# Default to be only compatible with specific machines or soc families
COMPATIBLE_MACHINE ?= "^$"
COMPATIBLE_MACHINE_zynq = ".*"
COMPATIBLE_MACHINE_zynqmp = ".*"
COMPATIBLE_MACHINE_kc705-microblazeel = ".*"

# Use DEFCONFIGs for configuring linux-xlnx kernels
KCONFIG_MODE ?= "alldefconfig"
KBUILD_DEFCONFIG_zynqmp = "xilinx_zynqmp_defconfig"
KBUILD_DEFCONFIG_zynq = "xilinx_zynq_defconfig"
KBUILD_DEFCONFIG_microblaze = "mmu_defconfig"

# Add meta-xilinx kmeta, used for MicroBlaze BSP fragments
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI_append = " file://xilinx-kmeta;type=kmeta;name=xilinx-kmeta;destsuffix=xilinx-kmeta"

# MicroBlaze BSP fragments
KERNEL_FEATURES_append_kc705-microblazeel = " bsp/kc705-microblazeel/kc705-microblazeel.scc"

