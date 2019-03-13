SUMMARY = "MPI test application"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

# TODO: do we actually DEPENDS and RDEPENDS on mpich, or can we be more general?

DEPENDS += "mpich"

TEST_NAME = "count_mpi_ranks"

SRC_URI = "file://${TEST_NAME}.c"

do_compile() {
    ${CC} ${CFLAGS} ${WORKDIR}/${TEST_NAME}.c ${LDFLAGS} -lmpi -o ${TEST_NAME}
}

do_install() {
    install -d ${D}/opt/${PN}
    install -m 755 ${TEST_NAME} ${D}/opt/${PN}/
}

FILES_${PN} = "/opt/${PN}/*"

RDEPENDS_${PN} += "mpich"
