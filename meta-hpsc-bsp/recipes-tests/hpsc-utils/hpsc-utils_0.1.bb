SUMMARY = "Runtime tests for HPSC Linux kernel drivers"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://README.md;md5=ec328c2895c886ed4b309e9ca669d7c5"

PV = "0.1+git${SRCPV}"

SRC_URI = "git://github.com/ISI-apex/hpsc-utils.git;protocol=git;branch=hpsc"
SRCREV = "5d259ecb960a4acf7dd950bfe1fb1ea423c86a73"

S = "${WORKDIR}/git"

TESTS += " \
    mboxtester \
    rtit-tester \
    rtit-tester.sh \
    shm-standalone-tester \
    shm-tester \
    wdtester \
    "

do_compile() {
    oe_runmake -C linux
}

do_install() {
    install -d ${D}/opt/${PN}
    for test in ${TESTS}
    do
        install -m 755 linux/${test} ${D}/opt/${PN}/
    done
}

FILES_${PN} += "/opt/${PN}/*"

RDEPENDS_${PN} += "bash"
