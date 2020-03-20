DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"

PROVIDES = "virtual/arm-trusted-firmware"

PV = "1.3-hpsc+git${SRCPV}"

SRC_URI = "git://github.com/ISI-apex/arm-trusted-firmware.git;protocol=git;branch=hpsc-w-snap"
SRCREV = "a7bb92cd4d8b62c75a9374451d8d2cdcd83f3a1e"

S = "${WORKDIR}/git"

inherit deploy

COMPATIBLE_MACHINE = "hpsc-chiplet"
PLATFORM_hpsc-chiplet = "hpsc_hpps"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

export CROSS_COMPILE="${TARGET_PREFIX}"
EXTRA_OEMAKE = "PLAT=${PLATFORM} bl31"

ATF_BASE_NAME ?= "${PN}-${PKGE}-${PKGV}-${PKGR}-${DATETIME}"
ATF_BASE_NAME[vardepsexclude] = "DATETIME"

OUTPUT_DIR = "${B}/build/${PLATFORM}/release"

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${OUTPUT_DIR}/bl31/bl31.elf ${DEPLOYDIR}/${ATF_BASE_NAME}.elf
	ln -sf ${ATF_BASE_NAME}.elf ${DEPLOYDIR}/${PN}.elf
	install -m 0644 ${OUTPUT_DIR}/bl31.bin ${DEPLOYDIR}/${ATF_BASE_NAME}.bin
	ln -sf ${ATF_BASE_NAME}.bin ${DEPLOYDIR}/${PN}.bin
}

addtask deploy before do_build after do_compile
