#
# Native hash functions for Java
#
# Copyright (c) Project Nayuki. (MIT License)
# https://www.nayuki.io/page/native-hash-functions-for-java
#

# ---- Library Definitions -->

# Converts the architecture name as returned from uname -m to a format that
# the JVM understands. ARCH is part of the string that the Native Library
# loader will use to determine the path of the JNI library.
ARCH := $(shell uname -m | sed -e 's/x86_64/64/g' \
                            -e 's/amd64/64/g' \
                            -e 's/i386/32/g' \
                            -e 's/i686/32/g')

# Like architecture the JVM has set of predefined OS names, so we need to convert
# the value output from uname to something that the JVM understands. This value
# is used for identifying the OS specific directory on the JNI include path.
OS := $(shell uname | sed -e 's/CYGWIN.*/win32/g' \
                       -e 's/MINGW32.*/win32/g' \
                       -e 's/SunOS.*/sunos/g' \
                       -e 's/NetBSD/netbsd/g' \
                       -e 's/GNU\/kFreeBSD/kfreebsd/g' \
                       -e 's/FreeBSD/freebsd/g' \
                       -e 's/OpenBSD/openbsd/g' \
                       -e 's/Darwin.*/darwin/g' \
                       -e 's/AIX.*/aix/g' \
                       -e 's/Linux.*/linux/g')

PLATFORM_OS := $(shell echo "$(OS)" | sed -e 's/darwin/osx/g')

# The filename for the shared library that was created differs based on the
# platform, so we conditionally set it below.
ifeq ($(OS), darwin)
	LIBFILE := ./libnayuki-native-hashes.dylib
else ifeq ($(OS), sunos)
	# uname -m doesn't give us a coherent answer for Solaris platforms, so
	# we have to do an additional inspection to figure out if we are 32/64 bit
	ARCH := $(shell isainfo -b)
	LIBFILE := ./libnayuki-native-hashes.so
else
	LIBFILE := ./libnayuki-native-hashes.so
endif

$(info Detected OS: ${OS}-${ARCH})

# ---- Configuration ----

# Available modes: c, x86, x86-64
MODE = c

# C compiler
CC = gcc

# Find JAVA_HOME if it isn't explicitly defined
JAVA_HOME ?= $(shell jrunscript -e 'java.lang.System.out.println(new java.io.File(java.lang.System.getProperty("java.home")).getParent());')
$(info Using JAVA_HOME: ${JAVA_HOME})

# Where to find jni.h
JAVA_INCLUDE_PATH := $(JAVA_HOME)/include
# Where to find the OS specific headers
JAVA_NATIVE_INCLUDE_PATH := $(JAVA_INCLUDE_PATH)/$(OS)

CFLAGS += -I $(JAVA_INCLUDE_PATH)
CFLAGS += -I $(JAVA_NATIVE_INCLUDE_PATH)
CFLAGS += -Wall
CFLAGS += -O1
CFLAGS += -std=c99

# ---- Source files ----

# JNI functions
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

# Compression functions for various modes
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

# Compressions functions only available in C
SRC_FILENAMES += \
    md2-compress.c        \
    ripemd256-compress.c  \
    ripemd320-compress.c  \
    tiger-compress.c

SRC_FILES = $(foreach name, $(SRC_FILENAMES), native/$(name))


# ---- Rules ----

all: $(LIBFILE) install-lib java

$(LIBFILE): $(SRC_FILES)
	mkdir -p out
	$(CC) $(CFLAGS) -shared -fPIC -o out/$@ $(SRC_FILES)

clean:
	rm -rf out
	JAVA_HOME=$(JAVA_HOME) mvn -q clean

# Installs the native library into the Java project so that it can be embedded
install-lib:
	mkdir -p native-hashes/src/main/resources/META-INF/lib/$(PLATFORM_OS)_$(ARCH)
	cp -v out/$(LIBFILE) native-hashes/src/main/resources/META-INF/lib/$(PLATFORM_OS)_$(ARCH)/

# Builds the Java library
java:
	JAVA_HOME=$(JAVA_HOME) mvn -q package

.PHONY: $(LIBFILE) clean
