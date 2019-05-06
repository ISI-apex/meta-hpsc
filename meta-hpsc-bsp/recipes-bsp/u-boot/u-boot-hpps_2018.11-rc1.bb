require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bc-native dtc-native flex-native bison-native"

PV = "2018.11-rc1-hpsc+git${SRCPV}"

SRC_URI = "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc"
SRCREV = "f6a8aca3049e4c2d7819b8e44a5e79241d3bde60"

S = "${WORKDIR}/git"
