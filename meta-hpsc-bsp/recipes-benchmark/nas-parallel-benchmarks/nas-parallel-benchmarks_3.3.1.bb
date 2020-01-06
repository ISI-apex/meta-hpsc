SUMMARY = "NAS Paralllel Benchmarks"
AUTHOR = "NAS Parallel Benchmarks Team"
HOMEPAGE = "http://www.nas.nasa.gov/Software/NPB/"

LICENSE = "NASA-1.3"
LIC_FILES_CHKSUM = "file://README;md5=656ce7c9e0a98558698ed4303179dfaa"

# Recipe requires FORTRAN support, which may require configuration in local.conf
# For example, by specificying the following without the leading '#':
# FORTRAN_forcevariable = ",fortran"

DEPENDS += "libgfortran"

SRC_URI = "https://www.nas.nasa.gov/assets/npb/NPB${PV}.tar.gz \
           file://0001-sys-suite.awk-get-non-zero-exit-codes.patch \
           file://0002-config-add-suite.def.patch \
           file://0003-config-add-make.def.patch \
           file://0004-sys-get-UCC-from-make.def.patch \
           "

SRC_URI[md5sum] = "8e5ec2c819480759725df67833619911"
SRC_URI[sha256sum] = "4a8ea679b1df69f583c544c47198b3c26a50ec2bb6f8f69aef66c04c9a747d2d"

PV = "3.3.1"

S = "${WORKDIR}/NPB${PV}"

# "-Werror=format-security" causes failure on some sources...
SECURITY_CFLAGS = ""

do_compile() {
        for model in SER OMP
        do
                cd "NPB3.3-${model}"
                # By running sys first, we can use parallel make for everything else
                oe_runmake -C sys
                oe_runmake suite
                cd ..
        done
}

do_install() {
        for model in SER OMP
        do
                cd "NPB3.3-${model}"
                mdir="${D}/opt/${PN}/NPB${PV}-${model}/bin"
                install -d $mdir
                install -m 755 bin/* $mdir/
                cd ..
        done
}

INSANE_SKIP_${PN} += "installed-vs-shipped"
INSANE_SKIP_${PN} += "ldflags"
FILES_${PN} += "/opt/${PN}/*"

RDEPENDS_${PN} += "libgomp"
