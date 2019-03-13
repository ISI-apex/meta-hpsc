# This bbappend is because /etc/hosts is not being updated with the hostname,
# which causes some commands like `hostname -f` or MPI programs to fail.
#
# Note that this append will need to be removed after moving away from 'thud'.
# Upstream poky is now in netbase 5.6, and proper management of /etc/hosts is
# moved to the base-files recipe.
#
# Here we append /etc/hosts to use $hostname for 127.0.0.1
#

RDEPENDS_${PN} += "base-files"

pkg_postinst_${PN} () {
	if [ -s $D/${sysconfdir}/hostname ]; then
		hostname=$(cat $D/${sysconfdir}/hostname)
		if ! grep -q "[[:space:]]$hostname[[:space:]]*" $D/${sysconfdir}/hosts; then
			echo "127.0.0.1 $hostname" >> $D/${sysconfdir}/hosts
		fi
	fi
}
