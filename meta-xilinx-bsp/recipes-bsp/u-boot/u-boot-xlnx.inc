require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

# If SRCREV equals '${AUTOREV}', then specify 'branch=hpsc'
# in SRC_URI, else specify 'nobranch=1'
SRC_URI = "${@ "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc" if (d.getVar('SRCREV') == '${AUTOREV}') else "git://github.com/ISI-apex/u-boot.git;protocol=git;nobranch=1" }"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"
FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-xlnx:"
FILESEXTRAPATHS_prepend := "${@'${THISDIR}/u-boot-xlnx/${XILINX_RELEASE_VERSION}:' if d.getVar('XILINX_RELEASE_VERSION') else ''}"

do_deploy_append_zcu102-zynqmp () {
	# deploy u-boot-spl.bin for use by runqemu/QEMU
	install -Dm 0644 ${B}/spl/u-boot-spl.bin ${DEPLOYDIR}/u-boot-spl.bin
}
