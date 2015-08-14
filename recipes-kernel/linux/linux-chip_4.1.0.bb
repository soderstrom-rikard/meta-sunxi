SECTION = "kernel"
DESCRIPTION = "NextThingCo CHIP Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i)"

inherit kernel siteinfo

require recipes-kernel/linux/linux-dtb.inc

INC_PR = "r0"
LOCALVERSION ?= ""
PACKAGES =+ "kernel-headers"
FILES_kernel-headers = "${exec_prefix}/src/linux*"
COMPATIBLE_MACHINE = "(chip|sunxi)"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SRC_URI = "git://github.com/NextThingCo/CHIP-linux.git;protocol=git;branch=nextthing/chip"

SRCREV = "2f59cdfc706266a8ab7b444bf9c0eebc88f9dc3f"

S = "${WORKDIR}/git"

do_configure_prepend() {
  cp ${WORKDIR}/git/arch/arm/configs/sunxi_defconfig ${B}/.config
}
