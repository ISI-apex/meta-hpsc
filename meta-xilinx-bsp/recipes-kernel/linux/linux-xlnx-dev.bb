# This recipe tracks the 'bleeding edge' linux-xlnx repository.
# Since this tree is frequently updated, AUTOREV is used to track its contents.
#
# To enable this recipe, set PREFERRED_PROVIDER_virtual/kernel = "linux-xlnx-dev"

# SRCREV can be either a tag (e.g. 'hpsc-0.9'), a commit hash,
# or '${AUTOREV}' if the user wants the head of the hpsc branch
SRCREV = "${SRCREV_linux_hpsc}"

# skip version sanity, because the version moves with AUTOREV
KERNEL_VERSION_SANITY_SKIP = "1"

LINUX_VERSION ?= "4.9+"
LINUX_VERSION_EXTENSION ?= "-xilinx-dev"

include linux-xlnx.inc

