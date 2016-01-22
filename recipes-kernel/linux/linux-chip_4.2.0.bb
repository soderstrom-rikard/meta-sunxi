SECTION = "kernel"
DESCRIPTION = "NextThingCo CHIP Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7 \
    "
COMPATIBLE_MACHINE = "(chip)"

inherit kernel siteinfo

require recipes-kernel/linux/linux-dtb.inc

INC_PR = "r1"
LOCALVERSION ?= ""
PACKAGES =+ "kernel-headers"
FILES_kernel-headers = "${exec_prefix}/src/linux*"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SRC_URI = " \
    git://github.com/nextthingco/chip-linux.git;protocol=git;branch=chip/stable \
    file://defconfig \
    "

SRCREV = "fd2ad2582c7fb4a5fedff5ac19ca37d138df3963"

S = "${WORKDIR}/git"

do_install_append() {
    #workaround for bug in fido, ocurrs when building out-of-tree modules
    #ref: https://lists.yoctoproject.org/pipermail/yocto/2015-May/024738.html
    cp -f ${KBUILD_OUTPUT}/Module.symvers ${STAGING_KERNEL_BUILDDIR}
}
