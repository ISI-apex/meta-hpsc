require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bc-native dtc-native flex-native bison-native"

PV = "2018.11-rc1-hpsc+git${SRCPV}"

SRC_URI = "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc"
SRCREV = "79df69589d9049872485d68c194fc7c0baeabd6d"

S = "${WORKDIR}/git"

# Default value for deployment filenames.
UBOOT_DTB_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.dtb"
UBOOT_DTB_BINARY ?= "u-boot.dtb"
UBOOT_DTB_SYMLINK ?= "u-boot-${MACHINE}.dtb"
UBOOT_NODTB_IMAGE ?= "u-boot-nodtb-${MACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_NODTB_BINARY ?= "u-boot-nodtb.${UBOOT_SUFFIX}"
UBOOT_NODTB_SYMLINK ?= "u-boot-nodtb-${MACHINE}.${UBOOT_SUFFIX}"

do_deploy_dtb () {
	mkdir -p ${DEPLOYDIR}
	cd ${DEPLOYDIR}

	if [ -f ${B}/${UBOOT_DTB_BINARY} ]; then
		install ${B}/${UBOOT_DTB_BINARY} ${DEPLOYDIR}/${UBOOT_DTB_IMAGE}
		rm -f ${UBOOT_DTB_BINARY} ${UBOOT_DTB_SYMLINK}
		ln -sf ${UBOOT_DTB_IMAGE} ${UBOOT_DTB_SYMLINK}
		ln -sf ${UBOOT_DTB_IMAGE} ${UBOOT_DTB_BINARY}
	fi
	if [ -f ${B}/${UBOOT_NODTB_BINARY} ]; then
		install ${B}/${UBOOT_NODTB_BINARY} ${DEPLOYDIR}/${UBOOT_NODTB_IMAGE}
		rm -f ${UBOOT_NODTB_BINARY} ${UBOOT_NODTB_SYMLINK}
		ln -sf ${UBOOT_NODTB_IMAGE} ${UBOOT_NODTB_SYMLINK}
		ln -sf ${UBOOT_NODTB_IMAGE} ${UBOOT_NODTB_BINARY}
	fi
}

python () {
	# u-boot.dtb and u-boot-nodtb.bin are deployed _before_ do_deploy
	# Thus, do_deploy_setscene will also populate them in DEPLOY_IMAGE_DIR
	bb.build.addtask('do_deploy_dtb', 'do_deploy', 'do_compile', d)
}
