require recipes-kernel/linux/linux-yocto.inc

SRCREV_linux_hpsc ?= "7b917787042518ee64097989cf718f32672eb731"
SRCREV = "${SRCREV_linux_hpsc}"

SRC_URI = "git://github.com/ISI-apex/linux-hpsc.git;protocol=git;branch=hpsc"

LINUX_VERSION ?= "4.14.0"
LINUX_VERSION_EXTENSION ?= "-hpsc"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

KMETA = "hpsc-kmeta"
KCONF_BSP_AUDIT_LEVEL = "2"

KERNEL_DEVICETREE_qemuarm = "versatile-pb.dtb"

# COMPATIBLE_MACHINE = "qemuarm|qemuarm64|qemux86|qemuppc|qemumips|qemumips64|qemux86-64"
COMPATIBLE_MACHINE_hpsc-chiplet = "hpsc-chiplet"

# Force the use of the KBUILD_DEFCONFIG even if some other defconfig was generated in the ${WORKDIR}
do_kernel_metadata_prepend () {
	[ -n "${KBUILD_DEFCONFIG}" ] && [ -e ${WORKDIR}/defconfig ] && rm ${WORKDIR}/defconfig
}

KCONFIG_MODE ?= "alldefconfig"
KBUILD_DEFCONFIG_hpsc-chiplet = "hpsc_defconfig"
