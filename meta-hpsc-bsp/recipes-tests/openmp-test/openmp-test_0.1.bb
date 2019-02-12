SUMMARY = "Simple code to test OpenMP"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

TEST_FILES_DIR = "${WORKDIR}/../../../../../../../src/meta-hpsc/meta-hpsc-bsp/lib/oeqa/files/"

do_compile() {
	     ${CC} -fopenmp ${TEST_FILES_DIR}/count_omp_threads.c -o ${TEST_FILES_DIR}/count_omp_threads
}

# do_package() and do_install() are empty since the compiled code doesn't go into the rootfs
do_package() {
}

do_install() {
}
