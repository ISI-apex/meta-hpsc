require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

# If SRCREV equals '${AUTOREV}', then specify 'branch=hpsc'
# in SRC_URI, else specify 'nobranch=1'
SRC_URI = "${@ "git://github.com/ISI-apex/u-boot.git;protocol=git;branch=hpsc" if (d.getVar('SRCREV') == '${AUTOREV}') else "git://github.com/ISI-apex/u-boot.git;protocol=git;nobranch=1" }"

S = "${WORKDIR}/git"

do_configure() {
#	oe_runmake hpsc_hpps_config
	cp -r ${S}/* ${S}/../build/
	cd ${S}/../build
	make CROSS_COMPILE=aarch64-poky-linux- CC="aarch64-poky-linux-gcc --sysroot=${S}/recipe-sysroot" V=1 HOSTCC="gcc -isystem${S}/recipe-sysroot-native/usr/include -O2 -pipe -L${S}/recipe-sysroot-native/usr/lib -L${S}/recipe-sysroot-native/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/lib -Wl,-rpath,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath,${S}/recipe-sysroot-native/lib -Wl,-O1 -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${S}/../../../../../sysroots-uninative/x86_64-linux/lib/ld-linux-x86-64.so.2" hpsc_hpps_defconfig
}

do_compile() {
#	oe_runmake
	cd ${S}/../build
	make CROSS_COMPILE=aarch64-poky-linux- CC="aarch64-poky-linux-gcc --sysroot=${S}/recipe-sysroot" V=1 HOSTCC="gcc -isystem${S}/recipe-sysroot-native/usr/include -O2 -pipe -L${S}/recipe-sysroot-native/usr/lib -L${S}/recipe-sysroot-native/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath-link,${S}/recipe-sysroot-native/lib -Wl,-rpath,${S}/recipe-sysroot-native/usr/lib -Wl,-rpath,${S}/recipe-sysroot-native/lib -Wl,-O1 -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${S}/../../../../../sysroots-uninative/x86_64-linux/lib/ld-linux-x86-64.so.2"
}
