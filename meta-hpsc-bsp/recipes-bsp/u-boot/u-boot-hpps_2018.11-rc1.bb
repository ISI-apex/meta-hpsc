require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bc-native dtc-native flex-native bison-native"

PV = "2018.11-rc1-hpsc+git${SRCPV}"

SRC_URI = "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc"
SRCREV = "72d0ce6eee1012b5a3c59028b8af66e69c45f83e"

S = "${WORKDIR}/git"
