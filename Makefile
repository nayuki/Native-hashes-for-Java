# 
# Native hash functions for Java
# 
# Copyright (c) Project Nayuki
# https://www.nayuki.io/page/native-hash-functions-for-java
# 

# ---- Library Definitions -->

# Converts the architecture name as returned from uname -m to a format that
# the JVM understands. ARCH is part of the platform string that the JVM
# will look at when searching directories for a native library.
ARCH=$(shell uname -m | sed -e 's/x86_64/x86-64/g' \
                            -e 's/amd64/x86-64/g' \
                            -e 's/i386/x86/g' \
                            -e 's/i686/x86/g' \
                            -e 's/powerpc/ppc/g' \
                            -e 's/powerpc64/ppc64/g' \
                            -e 's/^arm.*/arm/g')

# Like architecture the JVM has set of predefined OS names, so we need to convert
# the value output from uname to something that the JVM understands.
OS=$(shell uname | sed -e 's/CYGWIN.*/win32/g' \
                       -e 's/MINGW32.*/win32/g' \
                       -e 's/SunOS.*/sunos/g' \
                       -e 's/NetBSD/netbsd/g' \
                       -e 's/GNU\/kFreeBSD/kfreebsd/g' \
                       -e 's/FreeBSD/freebsd/g' \
                       -e 's/OpenBSD/openbsd/g' \
                       -e 's/Darwin.*/darwin/g' \
                       -e 's/AIX.*/aix/g' \
                       -e 's/Linux.*/linux/g')

# The filename for the shared library that was created differs based on the
# platform, so we conditionally set it below.
ifeq ($(OS), darwin)
	NATIVE_HASH_LIB := ./libnayuki-native-hashes.dylib
	PLATFORM := $(OS)
else ifeq ($(OS)-$(ARCH), sunos-i86pc)
	# uname -m doesn't give us a coherent answer for Solaris platforms, so
	# we have to do an additional inspection
	ARCH := $(shell isainfo -b | sed -e 's/64/x86-64/g' -e 's/32/x86/g')
        NATIVE_HASH_LIB := ./libnayuki-native-hashes.so
        PLATFORM := $(OS)-$(ARCH)
else
	NATIVE_HASH_LIB := ./libnayuki-native-hashes.so
	PLATFORM := $(OS)-$(ARCH)
endif

# ---- Configuration ----

# Available modes: c, x86, x86-64
MODE = c

# C compiler
CC = gcc

# Find JAVA_HOME if it isn't explicitly defined
JAVA_HOME ?= $(shell jrunscript -e 'java.lang.System.out.println(new java.io.File(java.lang.System.getProperty("java.home")).getParent());')

# Where to find jni.h
JAVA_INCLUDE_PATH := $(JAVA_HOME)/include
JAVA_NATIVE_INCLUDE_PATH := $(JAVA_INCLUDE_PATH)/$(OS)

# ---- Source files ----

SRC_FILENAMES =  \
    md2-jni.c        \
    md4-jni.c        \
    md5-jni.c        \
    ripemd128-jni.c  \
    ripemd160-jni.c  \
    ripemd256-jni.c  \
    ripemd320-jni.c  \
    sha1-jni.c       \
    sha256-jni.c     \
    sha512-jni.c     \
    tiger-jni.c      \
    whirlpool-jni.c

ifeq "$(MODE)" "c"
    SRC_FILENAMES += \
        md4-compress.c        \
        md5-compress.c        \
        ripemd128-compress.c  \
        ripemd160-compress.c  \
        sha1-compress.c       \
        sha256-compress.c     \
        sha512-compress.c     \
        whirlpool-compress.c
else ifeq "$(MODE)" "x86"
    SRC_FILENAMES += \
        md4-compress-x86.S        \
        md5-compress-x86.S        \
        ripemd128-compress-x86.S  \
        ripemd160-compress-x86.S  \
        sha1-compress-x86.S       \
        sha256-compress-x86.S     \
        sha512-compress-x86.S     \
        whirlpool-compress-x86.S
else ifeq "$(MODE)" "x86-64"
    SRC_FILENAMES += \
        md4-compress-x8664.S        \
        md5-compress-x8664.S        \
        ripemd128-compress-x8664.S  \
        ripemd160-compress-x8664.S  \
        sha1-compress-x8664.S       \
        sha256-compress-x8664.S     \
        sha512-compress-x8664.S     \
        whirlpool-compress-x8664.S
else
    $(error Invalid mode "$(MODE)")
endif

SRC_FILENAMES += \
    md2-compress.c        \
    ripemd256-compress.c  \
    ripemd320-compress.c  \
    tiger-compress.c

SRC_FILES = $(foreach name, $(SRC_FILENAMES), native/$(name))


# ---- Rules ----

all: $(NATIVE_HASH_LIB)

$(NATIVE_HASH_LIB):
	mkdir -p out
	@echo "Using JAVA_HOME: $(JAVA_HOME)"
	$(CC) -Wall -I $(JAVA_INCLUDE_PATH) -I $(JAVA_NATIVE_INCLUDE_PATH) -shared -fPIC -O1 -o out/$@ $(SRC_FILES)

clean:
	rm -rf out

.PHONY: $(NATIVE_HASH_LIB) clean
