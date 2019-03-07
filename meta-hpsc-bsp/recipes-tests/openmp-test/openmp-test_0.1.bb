SUMMARY = "OpenMP test application"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

TEST_NAME = "count_omp_threads"

SRC_URI = "file://${TEST_NAME}.c"

do_compile() {
    ${CC} ${CFLAGS} -fopenmp ${WORKDIR}/${TEST_NAME}.c ${LDFLAGS} -o ${TEST_NAME}
}

do_install() {
    install -d ${D}/opt/${PN}
    install -m 755 ${TEST_NAME} ${D}/opt/${PN}/
}

FILES_${PN} = "/opt/${PN}/*"
