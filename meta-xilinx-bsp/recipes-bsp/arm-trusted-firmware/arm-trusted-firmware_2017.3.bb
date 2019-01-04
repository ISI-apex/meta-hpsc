DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"

PROVIDES = "virtual/arm-trusted-firmware"

inherit deploy

DEPENDS += "u-boot-mkimage-native"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

SRCREV_atf ?= "9c83e93aec5c6bb3b0fd061fbf6bdfe37f49d485"
SRCREV = "${SRCREV_atf}"

SRC_URI = "git://github.com/ISI-apex/arm-trusted-firmware.git;protocol=git;branch=hpsc"

ATF_BASE_NAME ?= "${PN}-${PKGE}-${PKGV}-${PKGR}-${DATETIME}"
ATF_BASE_NAME[vardepsexclude] = "DATETIME"

COMPATIBLE_MACHINE = "hpsc-chiplet"

# don't export LDFLAGS
LDFLAGS[unexport] = "1"

do_configure() {
	cp -r ${S}/* ${B}
}

do_compile() {
	export CROSS_COMPILE=aarch64-poky-linux-
	oe_runmake PLAT=hpsc bl31
}

OUTPUT_DIR = "${B}/build/hpsc/release"

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${OUTPUT_DIR}/bl31/bl31.elf ${DEPLOYDIR}/${ATF_BASE_NAME}.elf
	ln -sf ${ATF_BASE_NAME}.elf ${DEPLOYDIR}/${PN}.elf
	install -m 0644 ${OUTPUT_DIR}/bl31.bin ${DEPLOYDIR}/${ATF_BASE_NAME}.bin
	ln -sf ${ATF_BASE_NAME}.bin ${DEPLOYDIR}/${PN}.bin
}

addtask deploy before do_build after do_compile

XILINX_RELEASE_VERSION = "v2017.3"

PV = "1.3-xilinx-${XILINX_RELEASE_VERSION}+git${SRCPV}"
