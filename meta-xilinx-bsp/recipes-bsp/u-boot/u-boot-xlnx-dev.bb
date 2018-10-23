# This recipe allows for a 'bleeding edge' u-boot-xlnx build.
# Since this tree is frequently updated, AUTOREV is used to track its contents.
#
# To enable this recipe, set the following in your machine or local.conf
#   PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-xlnx-dev"

include u-boot-xlnx.inc
include u-boot-spl-zynq-init.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

# SRCREV can be either a tag (e.g. 'hpsc-0.9'), a commit hash,
# or '${AUTOREV}' if the user wants the head of the hpsc branch
SRCREV = "${SRCREV_u_boot}"

PV = "${UBRANCH}-xilinx-dev+git${SRCPV}"

# Newer versions of u-boot have support for these
HAS_PLATFORM_INIT ?= " \
		xilinx_zynqmp_zcu102_rev1_0_config \
		hpsc_multi_config \
		"

