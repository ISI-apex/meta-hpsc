include u-boot-xlnx.inc
include u-boot-spl-zynq-init.inc

XILINX_RELEASE_VERSION = "v2017.3"

# SRCREV can be either a tag (e.g. 'hpsc-0.9'), a commit hash,
# or '${AUTOREV}' if the user wants the head of the hpsc branch
SRCREV = "${SRCREV_u_boot}"

PV = "v2017.01-xilinx-${XILINX_RELEASE_VERSION}+git${SRCPV}"

SRC_URI_append = " \
		file://arm64-zynqmp-Setup-partid-for-QEMU-to-match-silicon.patch \
		"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		xilinx_zynqmp_zcu102_rev1_0_config \
		hpsc_multi_config \
		"

