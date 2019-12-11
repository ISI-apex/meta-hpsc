SUMMARY = "Runtime tests for HPSC Linux kernel drivers"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=31263657a5fe16df5a0253ff7163b8b3"

PV = "0.1+git${SRCPV}"

SRC_URI = "git://github.com/ISI-apex/hpsc-utils.git;protocol=git;branch=hpsc"
SRCREV = "6ddb4ffe6950ac8987a3daffeff0936d1c2932e2"

S = "${WORKDIR}/git"

TESTS += " \
    dma-tester.sh \
    interrupt-affinity-tester.sh \
    mbox-multiple-core-tester \
    mboxtester \
    parallel-app-tester.sh \
    rtit-tester \
    rtit-tester.sh \
    shm-standalone-tester \
    shm-tester \
    sram-tester \
    wdtester \
    "

do_compile() {
    oe_runmake -C test/linux
}

do_install() {
    install -d ${D}/opt/${PN}
    for test in ${TESTS}
    do
        install -m 755 test/linux/${test} ${D}/opt/${PN}/
    done
}

FILES_${PN} += "/opt/${PN}/*"

RDEPENDS_${PN} += "bash"
