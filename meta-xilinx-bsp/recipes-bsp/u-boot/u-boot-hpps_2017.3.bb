include u-boot-xlnx.inc

XILINX_RELEASE_VERSION = "v2017.3"

# SRCREV can be either a tag (e.g. 'hpsc-0.9'), a commit hash,
# or '${AUTOREV}' if the user wants the head of the hpsc branch
SRCREV = "${SRCREV_u_boot}"

PV = "v2017.01-xilinx-${XILINX_RELEASE_VERSION}+git${SRCPV}"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=37495cdcff8cd78890dc65d37099bc38"

# u-boot-xlnx has support for these
HAS_PLATFORM_INIT ?= " \
		xilinx_zynqmp_zcu102_rev1_0_config \
		hpsc_multi_config \
		"

