include u-boot-spl-zynq-init.inc

# u-boot 2016.11 has support for these
HAS_PLATFORM_INIT ??= " \
		xilinx_zynqmp_zcu102_rev1_0_config \
		hpsc_multi_config \
		"

