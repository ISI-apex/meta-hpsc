SUMMARY = "Xilinx's hardware device trees required for QEMU"
HOMEPAGE = "https://github.com/xilinx/qemu-devicetrees/"
LICENSE = "BSD"
DEPENDS += "dtc-native"

inherit deploy

LIC_FILES_CHKSUM = "file://Makefile;beginline=1;endline=27;md5=7348b6cbcae69912cb1dee68d6c68d99"

PV = "xilinx-${XILINX_RELEASE_VERSION}+git${SRCPV}"

# If SRCREV equals '${AUTOREV}', then specify 'branch=hpsc'
# in SRC_URI, else specify 'nobranch=1'
SRC_URI = "${@ "git://github.com/ISI-apex/qemu-devicetrees.git;protocol=git;branch=hpsc" if\
 (d.getVar('SRCREV') == '${AUTOREV}') else "git://github.com/ISI-apex/qemu-devicetrees.git;\
protocol=git;nobranch=1" }"

S = "${WORKDIR}/git"

# Don't need to do anything
do_install() {
	:
}

do_deploy() {
	# single-arch dtbs
	for DTS_FILE in ${S}/LATEST/SINGLE_ARCH/*.dtb; do
		install -Dm 0644 $DTS_FILE ${DEPLOYDIR}/qemu-hw-devicetrees/$(basename $DTS_FILE .dtb).dtb
	done
}

addtask deploy after do_install
