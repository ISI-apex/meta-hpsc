require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native flex-native bison-native"

S = "${WORKDIR}/git"

SRCREV = "5da3da6c33ef910060a1ecfa3789836ebef2e133"
SRC_URI = "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc"

do_configure() {
	cp -r ${S}/* ${B}
	oe_runmake hpsc_hpps_config -C ${B}
}

do_compile() {
	oe_runmake -C ${B}
	cp ${B}/u-boot ${B}/HPPS-u-boot
}

PV = "2018.11-rc1-hpsc+git${SRCPV}"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=37495cdcff8cd78890dc65d37099bc38"

HAS_PLATFORM_INIT ?= " \
		hpsc_multi_config \
		"
