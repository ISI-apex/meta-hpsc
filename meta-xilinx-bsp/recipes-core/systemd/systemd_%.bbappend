FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

NETWORKING_SCRIPTS ?= "file://wired.network"
SRC_URI += "${NETWORKING_SCRIPTS}"

do_install_append() {
	if [ "${@bb.utils.contains('IMAGE_CLASSES', 'qemuboot', 'True', 'False' ,d)}" != "True" ]; then
		install -d ${D}${sysconfdir}/systemd/network/
		install -m 0644 ${WORKDIR}/*.network ${D}${sysconfdir}/systemd/network/
	fi
}

FILES_${PN} += "{sysconfdir}/systemd/network/*"
