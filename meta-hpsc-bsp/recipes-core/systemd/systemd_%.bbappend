FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://systemd/network/wired.network \
	    file://systemd/system.conf.d/timeout.conf \
	    file://systemd/system/systemd-hwdb-update.service.d/timeout.conf"

do_install_append() {
	if [ "${@bb.utils.contains('IMAGE_CLASSES', 'qemuboot', 'True', 'False' ,d)}" != "True" ]; then
		# could 'find . -exec install' but need to cd; cp -R allowed by OpenEmbedded
		cp -R --no-dereference --preserve=mode,links -v \
			${WORKDIR}/systemd/ ${D}${sysconfdir}/
	fi
}
