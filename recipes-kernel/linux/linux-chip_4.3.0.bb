SECTION = "kernel"
DESCRIPTION = "NextThingCo CHIP Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(chip)"

inherit kernel siteinfo

require recipes-kernel/linux/linux-dtb.inc

INC_PR = "r0"
LOCALVERSION ?= ""
PACKAGES =+ "kernel-headers"
FILES_kernel-headers = "${exec_prefix}/src/linux*"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SRC_URI = "git://github.com/NextThingCo/CHIP-linux.git;protocol=git;branch=nextthing/4.3/chip"

SRCREV = "713c5d272aa0db19ba6440597bcb32b68cb2ab2f"

S = "${WORKDIR}/git"
