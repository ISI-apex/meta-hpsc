require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

SRCREV_u_boot ?= "61e400add9ce27c029cd7c2b8f7438bc92ee0980"
SRCREV = "${SRCREV_u_boot}"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc"

do_configure() {
	cp -r ${S}/* ${B}
	oe_runmake hpsc_hpps_config -C ${B}
}

do_compile() {
	oe_runmake -C ${B}
	cp ${B}/u-boot ${B}/HPPS-u-boot
}

XILINX_RELEASE_VERSION = "v2017.3"

PV = "v2017.01-xilinx-${XILINX_RELEASE_VERSION}+git${SRCPV}"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=37495cdcff8cd78890dc65d37099bc38"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		xilinx_zynqmp_zcu102_rev1_0_config \
		hpsc_multi_config \
		"
