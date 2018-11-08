require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

# If SRCREV equals '${AUTOREV}', then specify 'branch=hpsc'
# in SRC_URI, else specify 'nobranch=1'
SRC_URI = "${@ "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc" if (d.getVar('SRCREV') == '${AUTOREV}') else "git://github.com/ISI-apex/u-boot.git;protocol=git;nobranch=1" }"

S = "${WORKDIR}/git"

#FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"
#FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-xlnx:"
#FILESEXTRAPATHS_prepend := "${@'${THISDIR}/u-boot-xlnx/${XILINX_RELEASE_VERSION}:' if d.getVar('XILINX_RELEASE_VERSION') else ''}"

#do_deploy_append_zcu102-zynqmp () {
#	# deploy u-boot-spl.bin for use by runqemu/QEMU
#	install -Dm 0644 ${B}/spl/u-boot-spl.bin ${DEPLOYDIR}/u-boot-spl.bin
#}

do_configure() {
#	oe_runmake hpsc_hpps_config
	cp -r ${S}/* ${S}/../build/
	cd ${S}/../build
	make CROSS_COMPILE=aarch64-poky-linux- CC="aarch64-poky-linux-gcc --sysroot=${S}/recipe-sysroot" V=1 HOSTCC="gcc -isystem${S}/recipe-sysroot-native/usr/include -O2 -pipe -L${S}/recipe-sysroot-native/usr/lib -L${S}/recipe-sysroot-native/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/lib -Wl,-rpath,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath,${S}/recipe-sysroot-native/lib -Wl,-O1 -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${S}/../../../../../sysroots-uninative/x86_64-linux/lib/ld-linux-x86-64.so.2" hpsc_hpps_defconfig
#	oe_runmake hpsc_hpps_defconfig
#	sh ${S}/chiplet_config_HPPS.sh
#	cd ${S}
#	make hpsc_hpps_config
#	make hpsc_hpps_defconfig
#	:
}

do_compile() {
#	oe_runmake
	cd ${S}/../build
	make CROSS_COMPILE=aarch64-poky-linux- CC="aarch64-poky-linux-gcc --sysroot=${S}/recipe-sysroot" V=1 HOSTCC="gcc -isystem${S}/recipe-sysroot-native/usr/include -O2 -pipe -L${S}/recipe-sysroot-native/usr/lib -L${S}/recipe-sysroot-native/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/lib -Wl,-rpath,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath,${S}/recipe-sysroot-native/lib -Wl,-O1 -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${S}/../../../../../sysroots-uninative/x86_64-linux/lib/ld-linux-x86-64.so.2"
}
