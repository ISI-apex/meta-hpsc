require qemu-devicetrees.inc

XILINX_RELEASE_VERSION = "v2017.3"

# SRCREV can be either a tag (e.g. 'hpsc-0.9'), a commit hash,
# or '${AUTOREV}' if the user wants the head of the hpsc branch
SRCREV = "${SRCREV_qemu_devicetrees}"
